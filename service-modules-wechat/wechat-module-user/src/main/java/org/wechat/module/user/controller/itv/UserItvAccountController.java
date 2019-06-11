package org.wechat.module.user.controller.itv;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.terminal.TerminalEnum;
import org.service.redis.token.RedisSession;
import org.service.redis.token.RedisToken;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.user.domain.User;
import org.wechat.module.user.domain.itv.UserItvAccount;
import org.wechat.module.user.global.BaseGlobal;
import org.wechat.module.user.global.UserStatusCode;
import org.wechat.module.user.message.Prompt;
import org.wechat.module.user.service.UserService;
import org.wechat.module.user.service.itv.UserItvAccountService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: itv用户
 */
@RestController
public class UserItvAccountController {
	
	@Resource
	private UserItvAccountService  userItvAccountService;
	
	@Resource
	private UserService  userService;
	
	@Resource
	private RedisCacheManager cacheManager;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userItvAccount
	 * @param result
	 * @return
	 * @description: 绑定用户
	 */
	@RequiresAuthentication(ignore = true)
	@PostMapping(value = { "/user/itv/account/binding" })
	public JsonApi bindingUser(
			@Validated(BaseEntity.Insert.class)@RequestBody UserItvAccount userItvAccount, BindingResult result) {
		/*根据电话号码查询用户*/ 
		User user = new User();
		user.setPhone(userItvAccount.getPhone());
		Map<String, Object> userResultMap = userService.getUserByPhone(user);
		if (userResultMap==null || userResultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("user.is.not.exists"));
		}
		/*拿到验证码*/ 
		String validCode = userItvAccount.getValidCode();
		ValueWrapper valueWrapper = cacheManager.getCache(BaseGlobal.CACHE_CODE).get(userItvAccount.getPhone());
		if (null == valueWrapper) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("valid.code.error"));
		}
		String code = (String) valueWrapper.get();
		if (!validCode.equals(code)) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("valid.code.error"));
		}
		/* 清除缓存*/
		cacheManager.getCache(BaseGlobal.CACHE_CODE).evict(userItvAccount.getPhone());
		
		userItvAccount.setStatus(UserStatusCode.ItvAccountStatus.ENABLE.getValue());
		userItvAccount.setUserId(Integer.parseInt(userResultMap.get("id").toString()));
		/*判断是否重复*/ 
		Map<String, Object> repeatResultMap = userItvAccountService.getRepeat(userItvAccount);
		if (repeatResultMap!=null && !repeatResultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		userItvAccount.setCreateDate(new Date());
		/*绑定用户*/ 
		if (userItvAccountService.insert(userItvAccount) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param id
	 * @param userItvAccount
	 * @param result
	 * @return
	 * @description: 解除用户绑定
	 */
	@RequiresAuthentication(ignore = true)
	@DeleteMapping(value = { "/user/itv/account/unbind/{id}" })
	public JsonApi unbindUser(
			@PathVariable("id") Integer id,
			@Validated(UserItvAccount.Unbind.class) UserItvAccount userItvAccount, BindingResult result) {
		/*设置用户id*/ 
		userItvAccount.setId(id);
		/*删除用户*/ 
		if (userItvAccountService.delete(userItvAccount) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userItvAccount
	 * @param result
	 * @return
	 * @description:  itv绑定用户列表
	 */
	@RequiresAuthentication(ignore = true)
	@GetMapping(value = { "/user/itv/account/users" })
	public JsonApi getItvUsers(
			@Validated(UserItvAccount.ItvUsers.class) UserItvAccount userItvAccount, BindingResult result) {
		Page<?> page = PageHelper.startPage(userItvAccount.getPage(), userItvAccount.getPageSize());
		/* itv绑定用户列表*/
		List<Map<String, Object>> usersResultList = userItvAccountService.getList(userItvAccount);
		if (usersResultList!=null && !usersResultList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), usersResultList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userItvAccount
	 * @param result
	 * @return
	 * @description:  itv用户登录 获取token
	 */
	@RequiresAuthentication(ignore = true)
	@GetMapping(value = { "/user/itv/account/login" })
	public JsonApi login(
			@Validated(UserItvAccount.Login.class) UserItvAccount userItvAccount, BindingResult result) {
		/*根据itv账号和用户id查询用户信息*/ 
		userItvAccount.setStatus(UserStatusCode.ItvAccountStatus.ENABLE.getValue());
		Map<String, Object> itvUserMap = userItvAccountService.getOne(userItvAccount);
		if (itvUserMap != null && itvUserMap.size() > 0) {
			/*查看缓存是否存在*/ 
			String userId = itvUserMap.get("userId").toString();
			RedisSession redisSession	=	cacheManager.getCache(BaseGlobal.CACHE_ORGANIZATION_USER).get(userId, RedisSession.class);
			if (redisSession != null) {
				Map<String, Object> resultMap = new HashMap<>();
				@SuppressWarnings("unchecked")
				Map<String,Object> userMapCache = redisSession.get(Map.class);
				String tokenCache = redisSession.getIdentify();
				resultMap.put("info", userMapCache);
				resultMap.put("token", tokenCache);
				return new JsonApi(ApiCodeEnum.OK, resultMap);
			}else {
				/*查询用户详情*/ 
				User user=new User();
				user.setId(Integer.parseInt(itvUserMap.get("userId").toString()));
				Map<String, Object> userMap = userService.getLoginMsg(user);
				if (userMap==null || userMap.isEmpty()) {
					return new JsonApi(ApiCodeEnum.NOT_FOUND);
				}
				String token =RedisToken.createToken(TerminalEnum.WEB, itvUserMap.get("userId"));
				/*放入缓存 已实现自动踢出*/ 
				cacheManager.putSession(BaseGlobal.CACHE_USER,token, userMap);
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("info", userMap);
				resultMap.put("token", token);
				/* 返回用户信息*/
				return new JsonApi(ApiCodeEnum.OK, resultMap);
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param token
	 * @param userItvAccount
	 * @param result
	 * @return
	 * @description:  验证密码
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/user/itv/account/valid/password" })
	public JsonApi validPassword(@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@Validated(UserItvAccount.ValidPassword.class) UserItvAccount userItvAccount, BindingResult result) {
		/*获取缓存信息*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		/*用户ID*/ 
		userItvAccount.setUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		/*查询详情*/ 
		userItvAccount.setId(null);
		userItvAccount.setStatus(UserStatusCode.ItvAccountStatus.ENABLE.getValue());
		Map<String, Object> validResultMap = userItvAccountService.getOne(userItvAccount);
		if (validResultMap!=null && !validResultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
}
