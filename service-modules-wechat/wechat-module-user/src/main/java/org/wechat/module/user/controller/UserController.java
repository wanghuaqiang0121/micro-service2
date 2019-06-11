package org.wechat.module.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.terminal.TerminalEnum;
import org.service.redis.token.RedisSession;
import org.service.redis.token.RedisToken;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.user.domain.User;
import org.wechat.module.user.domain.UserCertificate;
import org.wechat.module.user.domain.UserGroup;
import org.wechat.module.user.domain.UserSign;
import org.wechat.module.user.domain.UserUserGroup;
import org.wechat.module.user.global.BaseGlobal;
import org.wechat.module.user.global.UserStatusCode;
import org.wechat.module.user.message.Prompt;
import org.wechat.module.user.service.UserCertificateService;
import org.wechat.module.user.service.UserGroupService;
import org.wechat.module.user.service.UserService;
import org.wechat.module.user.service.UserSignService;
import org.wechat.module.user.service.UserUserGroupService;


/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 用户基本信息表
 */
@RestController
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private UserGroupService userGroupService;
	@Resource
	private UserUserGroupService userUserGroupService;
	@Resource
	private UserSignService userSignService;
	@Resource
	private UserCertificateService userCertificateService;
	@Resource
	private RedisCacheManager cacheManager;

	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @param result
	 * @param token
	 * @return  {@link JsonApi}
	 * @description: 添加家庭组成员
	 */
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/user/user/family/member" })
	@Transactional
	public JsonApi insertMember(@Validated({ User.insertMember.class }) @RequestBody User user,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*从缓存中获取用户*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		/*登录用户的id*/ 
		Integer operationUserId = (Integer) session.get(Map.class).get("id");
		/*判断电话是否被使用*/
		user.setIdCard(null);
		String phone = user.getPhone();
		if (null != phone && !"".equals(phone)) {
			User userNew = new User();
			userNew.setPhone(phone);
			Map<String, Object> UserRepeatMap = userService.getRepeat(userNew);
			if (UserRepeatMap!=null && !UserRepeatMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.phone.repeat"));
			}
		}
		/* 被添加联系人ID*/
		Integer userMemberId = null;
		/*判断当前用户有没有用户组*/ 
		UserGroup userGroup = new UserGroup();
		userGroup.setUserId(operationUserId);
		Map<String, Object> userGroupRepeatMap = userGroupService.getRepeat(userGroup);
		Integer userGroupId = null;
		/*用户组不存在创建用户组*/ 
		if (userGroupRepeatMap==null || userGroupRepeatMap.isEmpty()) {
			userGroup.setUserId(operationUserId);
			userGroup.setCreateDate(new Date());
			Integer groupId = userGroupService.insert(userGroup);
			if (groupId <= 0) {
				return new JsonApi(ApiCodeEnum.FAIL);
			}
			userGroupId = userGroup.getId();
		} else {/*用户组存在*/ 
			Integer groupId = (Integer) userGroupRepeatMap.get("id");
			/*判断用户组成员个数 不能超过10个*/ 
			UserUserGroup baseUserUserGroup = new UserUserGroup();
			baseUserUserGroup.setUserGroupId(groupId);
			List<Map<String, Object>> usersByGroupList = userUserGroupService.getList(baseUserUserGroup);
			if (usersByGroupList.size() < 10) {
				userGroupId = groupId;
			} else {/*用户组成员数是否大于10*/ 
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.group.user.greater.than"));
			}
		}
		UserCertificate userCertificate = user.getUserCertificate();
		if ( BaseGlobal.USER_ID_CARD_TYPE.equals(userCertificate.getCertificateType())) {
			user.setIdCard(userCertificate.getCertificateNumber());
		}
		/*判断证件是否被使用*/ 
		Map<String, Object> userCertificateRepeatMap = userCertificateService.getRepeat(userCertificate);
		/*未被使用-用户不存在*/ 
		if (userCertificateRepeatMap==null || userCertificateRepeatMap.isEmpty()) {
			/*拼接证件字符串*/ 
			List<String> imagesList = userCertificate.getImagesList();
			if (imagesList==null || imagesList.isEmpty()) {
				String images = "";
				for (String string : imagesList) {
					images = images + string + ",";
				}
				userCertificate.setImages(images.substring(0, images.length() - 1));
			}
			/* 判断添加成员是否为本人*/
			String relation = user.getRelation();
			/*为本人*/ 
			if (BaseGlobal.USER_GROUP_RELATION.equals(relation)) {
				UserUserGroup userUserGroup = new UserUserGroup();
				userUserGroup.setUserId(operationUserId);
				userUserGroup.setUserGroupId(userGroupId);
				Map<String, Object> userUserGroupRepeat = userUserGroupService.getRepeat(userUserGroup);
				if (userUserGroupRepeat!=null && !userUserGroupRepeat.isEmpty()) {
					return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.group.is.user.exist"));
				}
				/*判断本人是否输入重复的证件类型*/ 
				userCertificate.setUserId(operationUserId);
				Map<String, Object> repeatByType = userCertificateService.getRepeatByType(userCertificate);
				if (repeatByType!=null && !repeatByType.isEmpty()) {
					return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.certificate.type.is.used"));
				}
				/*修改基本信息*/ 
				user.setId(operationUserId);
				if (userService.update(user) <= 0) {
					return new JsonApi(ApiCodeEnum.FAIL);
				}
				/* 新增证件信息*/
				userCertificate.setCreateDate(new Date());
				if (userCertificateService.insert(userCertificate) <= 0) {
					return new JsonApi(ApiCodeEnum.FAIL);
				}
				userMemberId = operationUserId;
			} else {/*不是本人*/ 
				/*新增用户*/ 
				user.setCreateDate(new Date());
				user.setUpdateDate(new Date());
				user.setIsBindWechat(false);
				if (userService.insert(user) <= 0) {
					return new JsonApi(ApiCodeEnum.FAIL);
				}
				userCertificate.setUserId(user.getId());
				userCertificate.setCreateDate(new Date());
				/* 新增证件信息*/
				if (userCertificateService.insert(userCertificate) <= 0) {
					return new JsonApi(ApiCodeEnum.FAIL);
				}
				userMemberId = user.getId();
			}
			
		} else {/*证件被使用-用户存在*/ 
			/* 如果证件存在，并且关系为本人，提示用户证件号码已经被使用*/
			if (BaseGlobal.USER_GROUP_RELATION.equals(user.getRelation())) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.certificate.is.used"));
			}
			Integer userId = (Integer) userCertificateRepeatMap.get("userId");
			UserUserGroup userUserGroup = new UserUserGroup();
			userUserGroup.setUserId(userId);
			userUserGroup.setUserGroupId(userGroupId);
			Map<String, Object> userUserGroupRepeatMap = userUserGroupService.getRepeat(userUserGroup);
			if (userUserGroupRepeatMap!=null && !userUserGroupRepeatMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.group.is.user.exist"));
			}
			/*查询是否签约*/ 
			UserSign userSign = new UserSign();
			userSign.setUserId(userId);
			List<Map<String, Object>> userSignList = userSignService.getList(userSign);
			if (userSignList.size() > 0) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.sign.repeat.error"));
			}
			userMemberId = userId;
		}
		/*将成员添加到用户组*/ 
		UserUserGroup userUserGroup = new UserUserGroup();
		userUserGroup.setUserId(userMemberId);
		userUserGroup.setUserGroupId(userGroupId);
		userUserGroup.setRelation(user.getRelation());
		userUserGroup.setTrust(UserStatusCode.UserUserGroup.TRUSTEESHIP.getValue());
		userUserGroup.setCreateDatetime(new Date());
		if (userUserGroupService.insert(userUserGroup) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 注册并绑定用户
	 */
	@RequiresAuthentication(ignore = true)
	@PostMapping(value = { "/user/register" })
	@Transactional
	public JsonApi registerBaseUser( @Validated({ User.register.class }) @RequestBody User user, BindingResult result) {
		/*拿到验证码*/ 
		String validCode = user.getValidCode();
		ValueWrapper valueWrapper = cacheManager.getCache(BaseGlobal.CACHE_CODE).get(user.getPhone());
		if (null == valueWrapper) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("valid.code.error"));
		}
		String code = (String) valueWrapper.get();
		if (!validCode.equals(code)) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("valid.code.error"));
		}
		/*清除缓存*/ 
		cacheManager.getCache(BaseGlobal.CACHE_CODE).evict(user.getPhone());
		/*查询微信是否已经存在*/ 
		Map<String, Object> userWechatRepeatMap = userService.getWechatRepeat(user);
		if (userWechatRepeatMap!=null && !userWechatRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.wechat.is.exist"));
		}
		Integer userId;
		user.setIsBindWechat(true);
		/*通过电话查询用户是否已经存在*/ 
		Map<String, Object> userByPhoneMap = userService.getRepeat(user);
		/*用户不存在*/ 
		if (userByPhoneMap==null || userByPhoneMap.isEmpty()) {
			/*用户不存在，直接注册*/ 
			user.setPhoneStatus(UserStatusCode.User.PHONE_STATUS_IS_VALIDED.getValue());
			user.setCreateDate(new Date());
			user.setUpdateDate(new Date());
			user.setTerminalSource(UserStatusCode.TerminalSource.WECHAT.getValue());
			Integer insertUserId = userService.insert(user);
			if (insertUserId <= 0) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.crreate.error"));
			}
			userId = user.getId();
		} else {
			/* 用户存在，直接绑定微信*/
			userId = (Integer) userByPhoneMap.get("id");
		} 
		user.getWechat().setUserId(userId);
		user.getWechat().setCreateDate(new Date());
		if (userService.bindWechat(user) > 0) {
			User userNew = new User();
			userNew.setId(userId);
			userNew.setIsBindWechat(true);
			if (userService.update(userNew) > 0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.wechat.build.error"));
		}
		return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.wechat.build.error"));
		
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param id
	 * @param user
	 * @param result
	 * @return  {@link JsonApi}
	 * @description:  用户详情
	 */
	@RequiresAuthentication(authc = true)
	@RequestMapping(value = { "/user/detail/{id}" }, method = RequestMethod.GET)
	public JsonApi getUserDetails(@PathVariable("id") Integer id,
			@Validated({ User.SelectOne.class }) User user, BindingResult result) {
		user.setId(id);
		Map<String, Object> userDetailsMap = userService.getOne(user);
		if (userDetailsMap!=null && !userDetailsMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, userDetailsMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @param result
	 * @return  {@link JsonApi}
	 * @description:  注册用户
	 */
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/user" })
	public JsonApi addUser(@RequestBody @Validated({ BaseEntity.Insert.class }) User user, BindingResult result) {
		/*查询用户手机号是否被注册*/ 
		User userRepeat =new User();
		userRepeat.setPhone(user.getPhone());
		Map<String, Object> userRepeatMap = userService.getRepeat(userRepeat);
		if (userRepeatMap != null && !userRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.phone.repeat"));
		}
		/*设置用户的默认属性*/ 
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		user.setPhoneStatus(UserStatusCode.User.PHONE_STATUS_IS_VALIDED.getValue());
		user.setStatus(UserStatusCode.UserStatus.USE.getValue());
		user.setTerminalSource(UserStatusCode.TerminalSource.WECHAT.getValue());
		user.setIsBindWechat(false);
		if (userService.insert(user) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 绑定用户微信
	 */
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/user/wechat" })
	public JsonApi bindUserWechat(@RequestBody @Validated({ User.BindWechat.class }) User user, BindingResult result) {
		/*查询用户微信号是否已被绑定*/ 
		Map<String, Object> wechatMap = userService.getWechatRepeat(user);
		if (wechatMap != null && !wechatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.openid.repeat"));
		}
		user.setIsBindWechat(true);
		/* 查询用户是否存在*/
		Map<String, Object> userMap = userService.getRepeat(user);
		if (userMap == null || userMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.phone.empty"));
		}
		/* 设置用户ID*/
		user.getWechat().setUserId(Integer.parseInt(userMap.get("id").toString()));
		/*设置日期*/ 
		user.getWechat().setCreateDate(new Date());
		if (userService.bindWechat(user) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param user
	 * @param result
	 * @return  {@link JsonApi}
	 * @description:  微信登录
	 */
	@RequiresAuthentication( ignore = true)
	@GetMapping(value = { "/user/login/wechat" })
	public JsonApi userLoginWechat(@Validated({ User.WechatLogin.class }) User user, BindingResult result) {
		// 根据微信标识查询用户信息
		Map<String, Object> userMap = userService.getUserByWechat(user);
		if (userMap != null && !userMap.isEmpty()) {
			/*登录成功  存入缓存*/ 
			String token =RedisToken.createToken(TerminalEnum.WEB, userMap.get("id"));
			/*放入缓存 已实现自动踢出*/ 
			cacheManager.putSession(BaseGlobal.CACHE_USER,token, userMap);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("info", userMap);
			resultMap.put("token", token);
			/*修改登录时间*/ 
			user.getWechat().setId(Integer.parseInt(userMap.get("wechatId").toString()));
			user.getWechat().setLoginDate(new Date());
			if (userService.updateUserWechat(user) > 0) {
				return new JsonApi(ApiCodeEnum.OK, resultMap);
			}
			/* 返回用户信息*/
			return new JsonApi(ApiCodeEnum.OK, resultMap);
		}
		return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.login.wechat.error"));
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param token
	 * @return  {@link JsonApi}
	 * @description:  获取用户登录信息
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/user/{token}" })
	public JsonApi getUserCache(@PathVariable("token") String token) {
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		if (session != null) {
			return new JsonApi(ApiCodeEnum.OK, session);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
