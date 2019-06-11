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
import org.wechat.module.user.domain.itv.OrganizationUser;
import org.wechat.module.user.domain.itv.OrganizationUserItvAccount;
import org.wechat.module.user.global.BaseGlobal;
import org.wechat.module.user.global.UserStatusCode;
import org.wechat.module.user.message.Prompt;
import org.wechat.module.user.service.itv.OrganizationUserItvAccountService;
import org.wechat.module.user.service.itv.OrganizationUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description:  itv医生
 */
@RestController
public class OrganizationUserItvAccountController {

	@Resource
	private OrganizationUserItvAccountService organizationUserItvAccountService;
	
	@Resource
	private OrganizationUserService organizationUserService;
	
	@Resource
	private RedisCacheManager cacheManager;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationUserItvAccount
	 * @param result
	 * @return
	 * @description:  itv绑定医生
	 */
	@RequiresAuthentication(ignore = true)
	@PostMapping(value = { "/itv/doctor/account/binding" })
	public JsonApi bindingDoctor(
			@Validated(BaseEntity.Insert.class)@RequestBody OrganizationUserItvAccount organizationUserItvAccount, BindingResult result) {
		/*根据电话号码查询医生*/ 
		OrganizationUser organizationUser = new OrganizationUser();
		organizationUser.setPhone(organizationUserItvAccount.getPhone());
		Map<String, Object> organizationUserResultMap = organizationUserService.getDoctorByPhone(organizationUser);
		if (organizationUserResultMap==null || organizationUserResultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("organization.user.is.not.exists"));
		}
		/*拿到验证码*/ 
		String validCode = organizationUserItvAccount.getValidCode();
		ValueWrapper valueWrapper = cacheManager.getCache(BaseGlobal.CACHE_CODE).get(organizationUserItvAccount.getPhone());
		if (null == valueWrapper) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("valid.code.error"));
		}
		String code = (String) valueWrapper.get();
		if (!validCode.equals(code)) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("valid.code.error"));
		}
		/*清除缓存*/ 
		cacheManager.getCache(BaseGlobal.CACHE_CODE).evict(organizationUserItvAccount.getPhone());
		
		organizationUserItvAccount.setStatus(UserStatusCode.ItvAccountStatus.ENABLE.getValue());
		organizationUserItvAccount.setOrganizationUserId(Integer.parseInt(organizationUserResultMap.get("id").toString()));
		/*判断是否重复*/ 
		Map<String, Object> repeatResultMap = organizationUserItvAccountService.getRepeat(organizationUserItvAccount);
		if (repeatResultMap!=null && !repeatResultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		organizationUserItvAccount.setCreateDate(new Date());
		/* itv绑定医生*/
		if (organizationUserItvAccountService.insert(organizationUserItvAccount) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param token
	 * @param id
	 * @param organizationUserItvAccount
	 * @param result
	 * @return
	 * @description: 解除医生绑定
	 */
	@RequiresAuthentication(ignore = true )
	@DeleteMapping(value = { "/itv/doctor/account/unbind/{id}" } )
	public JsonApi unbindDoctor(@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@PathVariable("id") Integer id,
			@Validated(OrganizationUserItvAccount.Unbind.class) OrganizationUserItvAccount organizationUserItvAccount, BindingResult result) {
		/*设置用户id*/ 
		organizationUserItvAccount.setId(id);
		/* 删除*/
		if (organizationUserItvAccountService.delete(organizationUserItvAccount) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationUserItvAccount
	 * @param result
	 * @return
	 * @description: itv绑定医生列表
	 */
	@RequiresAuthentication(ignore = true )
	@GetMapping(value = { "/itv/doctor/account/doctors" } )
	public JsonApi getItvDoctors(
			@Validated(OrganizationUserItvAccount.ItvOrganizationUsers.class) OrganizationUserItvAccount organizationUserItvAccount, BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationUserItvAccount.getPage(), organizationUserItvAccount.getPageSize());
		/* itv绑定医生列表*/
		List<Map<String, Object>> doctorsResultList = organizationUserItvAccountService.getList(organizationUserItvAccount);
		if (doctorsResultList!=null && !doctorsResultList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), doctorsResultList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationUserItvAccount
	 * @param result
	 * @return
	 * @description:  itv医生登录 获取token
	 */
	@RequiresAuthentication(ignore = true)
	@GetMapping(value = { "/itv/doctor/account/login" })
	public JsonApi login(
			@Validated(OrganizationUserItvAccount.Login.class) OrganizationUserItvAccount organizationUserItvAccount, BindingResult result) {
		/* 根据itv账号和用户id查询用户信息*/
		organizationUserItvAccount.setStatus(UserStatusCode.ItvAccountStatus.ENABLE.getValue());
		Map<String, Object> itvDoctorMap = organizationUserItvAccountService.getOne(organizationUserItvAccount);
		if (itvDoctorMap != null && itvDoctorMap.size() > 0) {
			/*查看缓存是否存在*/ 
			String doctorId = itvDoctorMap.get("organizationUserId").toString();
			
			RedisSession redisSession	=	cacheManager.getCache(BaseGlobal.CACHE_ORGANIZATION_USER).get(doctorId, RedisSession.class);
			
			
			if (redisSession != null) {
				/*查询医生所在机构对应的团队*/ 
				OrganizationUser organizationUser=new OrganizationUser();
				organizationUser.setId(Integer.parseInt(doctorId));
				List<Map<String, Object>> organizationAndTeamsMap =organizationUserService.getOrganizationAndTeams(organizationUser);
				Map<String, Object> resultMap = new HashMap<>();
				@SuppressWarnings("unchecked")
				Map<String,Object> doctorMapCache = redisSession.get(Map.class);
				String tokenCache = redisSession.getIdentify();
				resultMap.put("info", doctorMapCache);
				resultMap.put("token", tokenCache);
				resultMap.put("organizations", organizationAndTeamsMap);
				return new JsonApi(ApiCodeEnum.OK, resultMap);
			}else {
				/* 查询机构用户详情*/
				OrganizationUser organizationUser=new OrganizationUser();
				organizationUser.setId(Integer.parseInt(itvDoctorMap.get("organizationUserId").toString()));
				Map<String, Object> doctorMap = organizationUserService.getLoginMsg(organizationUser);
				if (doctorMap==null || doctorMap.isEmpty()) {
					return new JsonApi(ApiCodeEnum.NOT_FOUND);
				}
				/*查询医生所在机构对应的团队*/ 
				List<Map<String, Object>> organizationAndTeamsList =organizationUserService.getOrganizationAndTeams(organizationUser);
				String token =RedisToken.createToken(TerminalEnum.WEB, doctorMap.get("id"));
				/*放入缓存 已实现自动踢出*/ 
				cacheManager.putSession(BaseGlobal.CACHE_ORGANIZATION_USER,token, doctorMap);
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("info", doctorMap);
				resultMap.put("token", token);
				resultMap.put("organizations", organizationAndTeamsList);
				/*返回用户信息*/ 
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
 * @param organizationUserItvAccount
 * @param result
 * @return
 * @description: 医生验证密码
 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/itv/doctor/account/valid/password" })
	public JsonApi validDoctorPassword(@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@Validated(OrganizationUserItvAccount.validDoctorPassword.class) OrganizationUserItvAccount organizationUserItvAccount, BindingResult result) {
		organizationUserItvAccount.setId(null);
		organizationUserItvAccount.setStatus(UserStatusCode.ItvAccountStatus.ENABLE.getValue());
		/*查询详情*/ 
		Map<String, Object> validDoctorResultMap = organizationUserItvAccountService.getOne(organizationUserItvAccount);
		if (validDoctorResultMap!=null && !validDoctorResultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
}
