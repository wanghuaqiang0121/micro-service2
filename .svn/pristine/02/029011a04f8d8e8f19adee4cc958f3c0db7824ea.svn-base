package org.web.module.organization.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.service.core.entity.BaseEntity.SelectAll;
import org.service.core.entity.BaseEntity.Update;
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.terminal.TerminalEnum;
import org.service.redis.token.RedisSession;
import org.service.redis.token.RedisToken;
import org.service.tools.md5.MD5Util;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.user.domain.DoctorOrganizationDepartmentDuty;
import org.web.module.organization.user.domain.OrganizationUser;
import org.web.module.organization.user.domain.OrganizationUserCertificate;
import org.web.module.organization.user.global.BaseGlobal;
import org.web.module.organization.user.global.BaseGlobalEnum;
import org.web.module.organization.user.message.Prompt;
import org.web.module.organization.user.sevice.DoctorOrganizationDepartmentDutyService;
import org.web.module.organization.user.sevice.OrganizationUserCertificateService;
import org.web.module.organization.user.sevice.OrganizationUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月30日
 * @description: 机构用户
 */
@RestController
public class OrganizationUserController {

	@Resource
	private OrganizationUserService organizationUserService;
	@Resource
	private DoctorOrganizationDepartmentDutyService doctorOrganizationDepartmentDutyService;
	@Resource
	private OrganizationUserCertificateService organizationUserCertificateService;
	@Resource
	private RedisCacheManager cacheManager;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param user
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构用户登录
	 */
	@RequiresAuthentication(ignore = true, value = { "web-module-organization-user:organization-user:login" })
	@GetMapping(value = { "/organizationUser/login" })
	public JsonApi login(@Validated({ OrganizationUser.Login.class }) OrganizationUser user,
			BindingResult result) {
		String password = user.getPassword();
		user.setPassword(MD5Util.getInstance().getMD5Code(password));
		/*查询账号是否存在*/ 
		Map<String, Object> userMap = organizationUserService.getUserByAccount(user);
		/*账号不存在*/ 
		if (userMap == null || userMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.user.account.is.not.exists"));
		}
		/*账号存在*/
		else if (userMap != null && !userMap.isEmpty()) { 
			Map<String, Object> userByAccountAndPasswordMap = organizationUserService.getUserByAccountAndPassword(user);
			if (userByAccountAndPasswordMap == null || userByAccountAndPasswordMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.user.password.is.not.exists"));
			}
			/*判断机构状态是否启用*/ 
			if ((Integer)userByAccountAndPasswordMap.get("organizationStatus") == BaseGlobalEnum.OrganizationStatus.DISABLE.getValue()) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.status.is.disabled"));
			}
			// 判断用户是否启用
			if ((Integer)userByAccountAndPasswordMap.get("status") == BaseGlobalEnum.OrganizationUser.DISABLE.getValue()) {
				return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.user.status.is.disabled"));
				}
			/*登录成功*/ 
			/*存入缓存*/ 
			String token =RedisToken.createToken(TerminalEnum.WEB, userByAccountAndPasswordMap.get("id"));
			/*放入缓存 已实现自动踢出*/ 
			cacheManager.putSession(BaseGlobal.CACHE_ORGANIZATION_USER,token, userByAccountAndPasswordMap);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("info", userByAccountAndPasswordMap);
			resultMap.put("token", token);
			/*返回用户信息*/ 
			return new JsonApi(ApiCodeEnum.OK, resultMap);
		}
		return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.user.login.account.error"));
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 新增用户
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user:insert" }, level = Level.OPERATION)
	@PostMapping(value = { "/organization/user" })
	@Transactional
	public JsonApi insert(@RequestBody @Validated({ OrganizationUser.InsertOrganizationUser.class }) OrganizationUser organizationUser, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		/* 设置默认值 */
		String phone = organizationUser.getPhone();
		String password = phone.substring(phone.length() - 6);
		password = MD5Util.getInstance().getMD5Code(password);
		organizationUser.setPassword(password);
		organizationUser.setCreateDate(new Date());
		organizationUser.setInitPassword(true);
		organizationUser.setPhoneStatus(BaseGlobalEnum.OrganizationUser.PHONESTATUSUNAUTH.getValue());
		organizationUser.setStatus(BaseGlobalEnum.OrganizationUser.ENABLE.getValue());

		/* 检查数据是否重复 */
		/* 电话号码不重复 */
		OrganizationUser organizationUserByPhone = new OrganizationUser();
		organizationUserByPhone.setPhone(organizationUser.getPhone());
		Map<String, Object> organizationUserMap = organizationUserService.getRepeat(organizationUserByPhone);
		if (organizationUserMap != null && !organizationUserMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.phone.is.exists"));
		}
		/* 账号不重复 */
		OrganizationUser organizationUserByAccount = new OrganizationUser();
		organizationUserByAccount.setAccount(organizationUser.getAccount());
		Map<String, Object> organizationUserByAccountMap = organizationUserService.getRepeat(organizationUserByAccount);
		if (organizationUserByAccountMap != null && !organizationUserByAccountMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.account.is.exists"));
		}

		/* 获取传入的机构用户证件列表 */
		List<OrganizationUserCertificate> organizationUserCertificates = organizationUser.getOrganizationUserCertificates();
		/* 指定机构用户id */
		for (OrganizationUserCertificate organizationUserCertificate : organizationUserCertificates) {
			if (MapUtils.isNotEmpty(organizationUserCertificateService.getRepeat(organizationUserCertificate))) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.certificates.is.exists"));
			}
			organizationUserCertificate.setCreateDate(new Date());
			if (organizationUserCertificate.getCertificateType().equals(String.valueOf(BaseGlobalEnum.CertificateTypeCode.IDCARD.getValue()))) {

			}
		}
		/* 新增机构用户 */
		if (organizationUserService.insert(organizationUser) > 0) {
			/* 设置关联表信息 */
			DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = new DoctorOrganizationDepartmentDuty();
			doctorOrganizationDepartmentDuty.setOrganizationUserId(organizationUser.getId());
			doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
			doctorOrganizationDepartmentDuty.setIsManager(false);
			doctorOrganizationDepartmentDuty.setIsLocal(true);
			/* 新增关联表信息 */
			if (doctorOrganizationDepartmentDutyService.insert(doctorOrganizationDepartmentDuty) > 0) {
				for (OrganizationUserCertificate organizationUserCertificate : organizationUserCertificates) {
					organizationUserCertificate.setOrganizationUserId(organizationUser.getId());
				}
				/* 添加证件 */
				if (organizationUserCertificateService.batchInsert(organizationUserCertificates) == organizationUserCertificates.size()) {
					Map<String, Integer> userMap = new HashMap<>();
					userMap.put("id", organizationUser.getId());
					return new JsonApi(ApiCodeEnum.OK, userMap);
				}
				throw new RuntimeException();
			}
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @param result
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 修改密码
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-organization-user:organization-user:change-password" })
	@PutMapping(value = { "/organizationUser/change/password" })
	public JsonApi changePassword(
			@Validated({ OrganizationUser.updatePassword.class })@RequestBody OrganizationUser organizationUser,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取缓存信息*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		Integer organizationUserId = Integer.parseInt(session.get(Map.class).get("id").toString());
		organizationUser.setId(organizationUserId);
		/* 机构用户详情*/
		Map<String, Object> organizationUserOneMap = organizationUserService.getOne(organizationUser);
		String oldPassword = (String) organizationUserOneMap.get("password");
		if (!oldPassword.equals(MD5Util.getInstance().getMD5Code(organizationUser.getPassword()))) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organization.user.old.password.error"));
		}
		OrganizationUser organizationUserNew = new OrganizationUser();
		organizationUserNew.setId(organizationUserId);
		organizationUserNew.setPassword(MD5Util.getInstance().getMD5Code(organizationUser.getNewPassword()));
		organizationUserNew.setIsInitPassword(false);
		if (organizationUserService.update(organizationUserNew) >0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationUser
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 重置密码
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user:update-password" }, level = Level.OPERATION)
	@PutMapping(value = { "/organization/user/password/{id}" })
	public JsonApi updatePassword(@PathVariable("id") Integer id, @RequestBody @Validated({ BaseEntity.Update.class }) OrganizationUser organizationUser, BindingResult result) {
		/* 判断数据是否存在 */
		organizationUser.setId(id);
		Map<String, Object> dorganizationUserMap = organizationUserService.getOne(organizationUser);
		if (dorganizationUserMap == null || dorganizationUserMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		String phone = dorganizationUserMap.get("phone").toString();
		String newPassword = phone.substring(phone.length() - 6);
		organizationUser.setPassword(MD5Util.getInstance().getMD5Code(newPassword));
		if (organizationUserService.update(organizationUser) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @param result
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 通过token查用户
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-organization-user:organization-user:get-token-detail" })
	@GetMapping(value = { "/organizationUser/token/user" })
	public JsonApi getTokenDetail(
			@Validated({ BaseEntity.SelectOne.class }) OrganizationUser organizationUser, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取缓存信息*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		@SuppressWarnings("unchecked")
		Map<String, Object> organizationUserMap = session.get(Map.class);
		if (MapUtils.isEmpty(organizationUserMap)) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		return new JsonApi(ApiCodeEnum.OK, organizationUserMap);
	}


	

	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @param result
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 修改用户信息
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-organization-user:organization-user:update" })
	@PutMapping(value = { "/organizationUser/update" })
	public JsonApi update(@Validated({ Update.class }) @RequestBody OrganizationUser organizationUser,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 获取缓存信息*/
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationUser.setId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		if (organizationUserService.update(organizationUser) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 修改用户
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user:update" }, level = Level.OPERATION)
	@PutMapping(value = { "/organization/user/{id}" })
	@Transactional
	public JsonApi update(@PathVariable("id") Integer id, @RequestBody @Validated({ BaseEntity.Update.class }) OrganizationUser organizationUser, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = new DoctorOrganizationDepartmentDuty();
		doctorOrganizationDepartmentDuty.setOrganizationUserId(id);
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		Map<String, Object> doctorOrganizationDepartmentDutyMap = doctorOrganizationDepartmentDutyService.getRepeat(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyMap == null || doctorOrganizationDepartmentDutyMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("doctor.organization.department.duty.not.found"));
		}
		doctorOrganizationDepartmentDuty.setId((Integer) doctorOrganizationDepartmentDutyMap.get("id"));
		doctorOrganizationDepartmentDuty.setDepartmentId(organizationUser.getDepartmentId());
		doctorOrganizationDepartmentDuty.setPost(organizationUser.getPost());
		/* 判断数据是否存在 */
		organizationUser.setId(id);
		/*
		 * organizationUser.setIdCard(null); organizationUser.setAccount(null);
		 */
		Map<String, Object> organizationUserMap = organizationUserService.getOne(organizationUser);
		if (organizationUserMap == null || organizationUserMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("organization.user.one.not.found"));
		}
		/* 如果修改电话号码 需要判断重复 */
		if (StringUtils.isNotBlank(organizationUser.getPhone())) {
			/* 电话号码不重复 */
			OrganizationUser organizationUserByPhone = new OrganizationUser();
			organizationUserByPhone.setPhone(organizationUser.getPhone());
			Map<String, Object> organizationUserByPhoneMap = organizationUserService.getRepeat(organizationUserByPhone);
			if (organizationUserByPhoneMap != null && !organizationUserByPhoneMap.isEmpty()) {
				if (!organizationUserMap.get("id").toString().equals(organizationUserByPhoneMap.get("id").toString())) {
					return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.phone.is.exists"));
				}
			}
		}
		/* 如果修改账号 需要判断重复 */
		if (StringUtils.isNotBlank(organizationUser.getAccount())) {
			/* 账号不重复 */
			OrganizationUser organizationUserByAccount = new OrganizationUser();
			organizationUserByAccount.setAccount(organizationUser.getAccount());
			Map<String, Object> organizationUserByAccountMap = organizationUserService.getRepeat(organizationUserByAccount);
			if (organizationUserByAccountMap != null && !organizationUserByAccountMap.isEmpty()) {
				if (!organizationUserMap.get("id").toString().equals(organizationUserByAccountMap.get("id").toString())) {
					return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.account.is.exists"));
				}
			}
		}
		/* 修改数据 */
		if (doctorOrganizationDepartmentDutyService.update(doctorOrganizationDepartmentDuty) > 0) {
			if (organizationUserService.update(organizationUser) > 0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @param result
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 查询机构用户的机构列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-organization-user:organization-user:get-organization-list" })
	@GetMapping(value = { "/organizationUser/organizations" })
	public JsonApi getOrganzationList(
			@Validated({ SelectAll.class }) OrganizationUser organizationUser,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {		
		/* 获取缓存信息*/
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationUser.setId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
		List<Map<String, Object>> organizationList = organizationUserService.getOrganzationUserOrganzationList(organizationUser);
		if (organizationList != null && !organizationList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 获取机构用户详情
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user:get-detail" }, level = Level.OPERATION)
	@GetMapping(value = { "/organization/user/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) OrganizationUser organizationUser, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		/* 设置主键 */
		organizationUser.setId(id);
		organizationUser.setOrganizationId(organizationId);
		Map<String, Object> organizationUserMap = organizationUserService.getOne(organizationUser);
		if (organizationUserMap != null && !organizationUserMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, organizationUserMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年11月8日
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 机构用户电话号码判断重复
	 * TODO 待添加到新项目
	 */
	@RequiresAuthentication( value = { "organization:configure:organizationUser:validPhoneRepeat" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/user/valid/phone" })
	public JsonApi validPhoneRepeat(@Validated({ OrganizationUser.PhoneRepeat.class }) OrganizationUser  organizationUser,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId ){
		Map<String, Object> resultMap = new HashMap<>(); 
		/*检查电话号码是否重复*/
		OrganizationUser organizationUserByPhone = new OrganizationUser();
		organizationUserByPhone.setPhone(organizationUser.getPhone());
		Map<String, Object> organizationUserMap=organizationUserService.getRepeat(organizationUserByPhone);
		if (organizationUserMap!=null &&!organizationUserMap.isEmpty()) {
			resultMap.put("isRepeat",true);
			return new JsonApi(ApiCodeEnum.OK,resultMap).setMsg(Prompt.bundle("organizationUser.phone.is.exists"));
		}
		resultMap.put("isRepeat",false);
		return new JsonApi(ApiCodeEnum.OK,resultMap);
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年11月8日
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 验证账号是否重复 
	 * TODO 待添加到新项目
	 */
	@RequiresAuthentication( value = { "organization:configure:organizationUser:validAccountRepeat" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/user/valid/account" })
	public JsonApi validAccountRepeat(@Validated({ OrganizationUser.AccountRepeat.class }) OrganizationUser  organizationUser,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId ){
		/*账号是否重复*/
		OrganizationUser  organizationUserByAccount = new OrganizationUser();
		organizationUserByAccount.setAccount(organizationUser.getAccount());
		Map<String, Object> resultMap = new HashMap<>(); 
		Map<String, Object> organizationUserByAccountMap=organizationUserService.getRepeat(organizationUserByAccount);
		if (organizationUserByAccountMap!=null &&!organizationUserByAccountMap.isEmpty()) {
			resultMap.put("isRepeat",true);
			return new JsonApi(ApiCodeEnum.OK,resultMap).setMsg(Prompt.bundle("organizationUser.account.is.exists"));
		}
		resultMap.put("isRepeat",false);
		return new JsonApi(ApiCodeEnum.OK,resultMap);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年11月8日
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 验证身份证是否重复 
	 *  TODO 待添加到新项目
	 */
	@RequiresAuthentication( value = { "organization:configure:organizationUser:validCertificateRepeat" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/user/valid/certificate" })
	public JsonApi validCertificateRepeat(@Validated({ OrganizationUser.validCertificateRepeat.class }) OrganizationUser  organizationUser,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId ){
		// 获取传入的机构用户证件列表
		List<OrganizationUserCertificate> organizationUserCertificates = organizationUser.getOrganizationUserCertificates();
		Map<String, Object> resultMap = new HashMap<>(); 
		// 指定机构用户id
		for (OrganizationUserCertificate organizationUserCertificate : organizationUserCertificates) {
			if (MapUtils.isNotEmpty(organizationUserCertificateService.getRepeat(organizationUserCertificate))) {
				resultMap.put("isRepeat",true);
				return new JsonApi(ApiCodeEnum.OK,resultMap).setMsg(Prompt.bundle("organizationUser.certificates.is.exists"));
			}
		}
		resultMap.put("isRepeat",false);
		return new JsonApi(ApiCodeEnum.OK,resultMap);
	}
}
