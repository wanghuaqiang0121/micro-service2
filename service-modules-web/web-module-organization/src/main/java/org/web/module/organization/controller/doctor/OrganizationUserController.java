package org.web.module.organization.controller.doctor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.service.core.entity.BaseEntity.Update;
import org.service.redis.cache.RedisCacheManager;
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
import org.web.module.organization.domain.OrganizationPersonType;
import org.web.module.organization.domain.user.DoctorOrganizationDepartmentDuty;
import org.web.module.organization.domain.user.OrganizationUser;
import org.web.module.organization.domain.user.OrganizationUserCertificate;
import org.web.module.organization.global.BaseGlobal;
import org.web.module.organization.global.BaseGlobalEnum;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.OrganizationPersonTypeService;
import org.web.module.organization.service.user.DoctorOrganizationDepartmentDutyService;
import org.web.module.organization.service.user.OrganizationUserCertificateService;
import org.web.module.organization.service.user.OrganizationUserService;

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
	@Resource
	private OrganizationPersonTypeService organizationPersonTypeService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年11月8日
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 机构用户电话号码判断重复
	 * TODO 待添加到新项目
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-user:valid-phone-repeat" },level=Level.OPERATION)
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
	 * @author: ChenYan
	 * @date: 2018年11月8日
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 验证账号是否重复 
	 * TODO 待添加到新项目
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-user:valid-account-repeat" },level=Level.OPERATION)
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
	 * @author: ChenYan
	 * @date: 2018年11月8日
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 验证身份证是否重复 
	 *  TODO 待添加到新项目
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-user:valid-certificate-repeat" },level=Level.OPERATION)
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
	
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 新增用户
	 */
	@RequiresAuthentication(value = { "web-module-organization:organization-user:insert" }, level = Level.OPERATION)
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
			if (organizationUserCertificateService.getRepeat(organizationUserCertificate)!=null && !organizationUserCertificateService.getRepeat(organizationUserCertificate).isEmpty()) {
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
			doctorOrganizationDepartmentDuty.setStatus(BaseGlobalEnum.OrganizationUser.ENABLE.getValue());
			if (null !=organizationUser.getWorkNumber()) {
				doctorOrganizationDepartmentDuty.setWorkNumber(organizationUser.getWorkNumber());
			}
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
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationUser
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 重置密码
	 */
	@RequiresAuthentication(value = { "web-module-organization:organization-user:update-password" }, level = Level.OPERATION)
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
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 修改用户
	 */
	@RequiresAuthentication(value = { "web-module-organization:organization-user:update" }, level = Level.OPERATION)
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
		if (null !=organizationUser.getWorkNumber()) {
			doctorOrganizationDepartmentDuty.setWorkNumber(organizationUser.getWorkNumber());	
		}
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
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationUser
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 获取机构用户详情
	 */
	@RequiresAuthentication(value = { "web-module-organization:organization-user:get-detail" }, level = Level.OPERATION)
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
	 * @author: ChenYan
	 * @date: 2018年12月6日
	 * @param organizationUser
	 * @param result
	 * @return
	 * @description: 新增机构管理员
	 */
	@RequiresAuthentication(value = { "web-module-organization:organization-user:insert-manage" },level = Level.OPERATION)
	@PostMapping(value = { "/organization/manage/user" })
	@Transactional
	public JsonApi insertManage(@RequestBody @Validated({ OrganizationUser.InsertOrganizationUser.class }) OrganizationUser  organizationUser,BindingResult result){
		/*设置默认值*/
		String phone = organizationUser.getPhone();
		String password = phone.substring(phone.length() - 6);
		password = MD5Util.getInstance().getMD5Code(password);
		organizationUser.setPassword(password);
		organizationUser.setCreateDate(new Date());
		organizationUser.setInitPassword(true);
		organizationUser.setPhoneStatus(BaseGlobalEnum.OrganizationUser.PHONESTATUSUNAUTH.getValue());
		organizationUser.setStatus(BaseGlobalEnum.OrganizationUser.ENABLE.getValue());
		
		/*检查数据是否重复*/
		// 电话号码不重复
		OrganizationUser  organizationUserByPhone = new OrganizationUser();
		organizationUserByPhone.setPhone(organizationUser.getPhone());
		Map<String, Object> organizationUserMap=organizationUserService.getRepeat(organizationUserByPhone);
		if (organizationUserMap!=null && !organizationUserMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.phone.is.exists"));
		}
		// 账号不重复
		OrganizationUser  organizationUserByAccount = new OrganizationUser();
		organizationUserByAccount.setAccount(organizationUser.getAccount());
		Map<String, Object> organizationUserByAccountMap=organizationUserService.getRepeat(organizationUserByAccount);
		if (organizationUserByAccountMap!=null && !organizationUserByAccountMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.account.is.exists"));
		}
		
		// 获取传入的机构用户证件列表
		List<OrganizationUserCertificate> organizationUserCertificates = organizationUser.getOrganizationUserCertificates();
		// 指定机构用户id
		for (OrganizationUserCertificate organizationUserCertificate : organizationUserCertificates) {
			if (MapUtils.isNotEmpty(organizationUserCertificateService.getRepeat(organizationUserCertificate))) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.certificates.is.exists"));
			}
			organizationUserCertificate.setCreateDate(new Date());
			if (organizationUserCertificate.getCertificateType().equals(String.valueOf(BaseGlobalEnum.CertificateTypeCode.IDCARD.getValue()))) {
				
			}
		}
		/*设置机构用户类型:管理员*/
		OrganizationPersonType organizationPersonType = new OrganizationPersonType();
		organizationPersonType.setCode(BaseGlobal.MANAGER);
		Map<String, Object> organizationPersonTypeOneResultMap = organizationPersonTypeService.getOne(organizationPersonType);
		if (MapUtils.isNotEmpty(organizationPersonTypeOneResultMap)) {
			organizationUser.setOrganizationPersonTypeId(Integer.parseInt(organizationPersonTypeOneResultMap.get("id").toString()));
		}
		/*新增机构用户*/
		if (organizationUserService.insert(organizationUser)>0) {
			for (OrganizationUserCertificate organizationUserCertificate : organizationUserCertificates) {
				organizationUserCertificate.setOrganizationUserId(organizationUser.getId());
			}
			// 添加证件
			if (organizationUserCertificateService.batchInsert(organizationUserCertificates) == organizationUserCertificates.size()) {
				/*设置关联表信息*/
				DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = organizationUser.getDoctorOrganizationDepartmentDuty();
				doctorOrganizationDepartmentDuty.setOrganizationUserId(organizationUser.getId());
				/*doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);*/
				doctorOrganizationDepartmentDuty.setIsManager(true);
				doctorOrganizationDepartmentDuty.setStatus(BaseGlobalEnum.Organization.ENABLE.getValue());
				// 判断关联表是否有数据
				Map<String, Object> doctorOrganizationDepartmentDutyMap = doctorOrganizationDepartmentDutyService.getRepeat(doctorOrganizationDepartmentDuty);
				if(doctorOrganizationDepartmentDutyMap!=null && !doctorOrganizationDepartmentDutyMap.isEmpty()){
					return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.manager.is.exists"));
				}
				/*新增关联表信息*/
				if (doctorOrganizationDepartmentDutyService.insert(doctorOrganizationDepartmentDuty)>0) {
					Map<String, Integer> userMap = new HashMap<>(); 
					userMap.put("id", organizationUser.getId());
					return new JsonApi(ApiCodeEnum.OK,userMap);
				}
				throw new RuntimeException();
			}
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	@RequiresAuthentication(authc = true, value = { "platform:configure:organization:user:update" })
	@PutMapping(value = { "/organizationUser/update/{id}" })
	@Transactional
	public JsonApi updateManager(@PathVariable("id")Integer id,
			@Validated({ Update.class }) @RequestBody OrganizationUser organizationUser, BindingResult result) {
		// 查询机构用户是否存在
		organizationUser.setId(id);
		Map<String, Object> organizationUserMap = organizationUserService.getOne(organizationUser);
		if (organizationUserMap!=null && !organizationUserMap.isEmpty()) {
			if (organizationUserService.update(organizationUser) > 0) {
				// 修改机构用户关联表 （授权书）
				DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = organizationUser.getDoctorOrganizationDepartmentDuty();
				if (doctorOrganizationDepartmentDuty!=null) {
					doctorOrganizationDepartmentDuty.setIsManager(true);
					doctorOrganizationDepartmentDuty.setOrganizationUserId(id);
					if (doctorOrganizationDepartmentDutyService.updateAuthorizeAptitude(doctorOrganizationDepartmentDuty) > 0) {
						return new JsonApi(ApiCodeEnum.OK);
					}
					throw new RuntimeException();
				}
				return new JsonApi(ApiCodeEnum.OK);
			}
			return new JsonApi(ApiCodeEnum.FAIL);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
}
