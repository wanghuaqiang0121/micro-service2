package org.web.module.organization.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.Organization;
import org.web.module.organization.domain.OrganizationType;
import org.web.module.organization.global.BaseGlobalEnum;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.OrganizationService;
import org.web.module.organization.service.OrganizationTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 机构
 */
@RestController
public class OrganizationController {

	@Resource
	private OrganizationService organizationService;
	@Resource
	private OrganizationTypeService organizationTypeService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organization
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 添加机构
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization:insert" })
	@PostMapping(value = { "/organization" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody Organization organization,BindingResult result) {
		/*判断机构类型是否存在*/ 
		OrganizationType	 organizationType = new OrganizationType();
		organizationType.setId(organization.getOrganizationTypeId());
		Map<String, Object> organizationTypeResultMap = organizationTypeService.getOne(organizationType);
		if (MapUtils.isEmpty(organizationTypeResultMap)) {
			return new JsonApi(ApiCodeEnum.FAIL, Prompt.bundle("organizationType.is.not.exists"));
		}
		/*判断要添加的数据是否存在*/ 
		Map<String, Object> organizationRepeatResultMap = organizationService.getRepeat(organization);
		if (!MapUtils.isEmpty(organizationRepeatResultMap)) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.code.or.name.is.conflict"));
		}
		/*添加数据*/ 
		organization.setStatus(BaseGlobalEnum.Organization.ENABLE.getValue());
		organization.setCreateDate(new Date());
		if (organizationService.insert(organization) > 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", organization.getId());
			return new JsonApi(ApiCodeEnum.OK,map);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organization
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  修改机构数据
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization:update" })
	@PutMapping(value = { "/organization/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,
			@Validated({ BaseEntity.Update.class }) @RequestBody Organization organization, BindingResult result) {
		organization.setId(id);
		/*数据是否存在*/ 
		Map<String, Object> organizationOneMap = organizationService.getOne(organization);
		if (MapUtils.isEmpty(organizationOneMap)) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 数据是否重复*/
		Map<String, Object> organizationRepeatMap = organizationService.getRepeat(organization);
		if (MapUtils.isNotEmpty(organizationRepeatMap)) {
			int organizationRepeatId = (Integer) organizationRepeatMap.get("id");
			if (id.intValue() != organizationRepeatId) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.code.or.name.is.conflict"));
			}
		}
		/*修改数据*/ 
		if (organizationService.update(organization) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organization
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 查询机构详情
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization:get-detail" })
	@GetMapping(value = { "/organization/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id,
			@Validated({ BaseEntity.SelectOne.class }) Organization organization, BindingResult result) {
		organization.setId(id);
		Map<String, Object> organizationOne = organizationService.getOne(organization);
		if (MapUtils.isNotEmpty(organizationOne)) {
			return new JsonApi(ApiCodeEnum.OK, organizationOne);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organization
	 * @return  {@link JsonApi}
	 * @description: 查询机构列表
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization:get-list" })
	@GetMapping(value = { "/organization" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) Organization organization) {
		Page<?> page = PageHelper.startPage(organization.getPage(), organization.getPageSize());
		List<Map<String, Object>> organizationList = organizationService.getList(organization);
		if (organizationList != null && !organizationList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organization
	 * @param result
	 * @return
	 * @description: 新增机构默认新增管理员
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization:manager:insert" })
	@RequestMapping(value = { "/organization/manager" }, method = RequestMethod.POST)
	@Transactional
	public JsonApi organizationManagerInsert(@RequestBody Organization organization,BindingResult result) {/*
		// 判断机构规模是否存在
		OrganizationScale organizationScale = new OrganizationScale();
		organizationScale.setId(organization.getOrganizationScaleId());
		Map<String, Object> organizationScaleResultMap = organizationScaleService.getOne(organizationScale);
		if (MapUtils.isEmpty(organizationScaleResultMap)) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg( Prompt.bundle("organizationScale.is.not.exists"));
		}
		// 判断机构类型是否存在
		OrganizationType organizationType = new OrganizationType();
		organizationType.setId(organization.getOrganizationTypeId());
		Map<String, Object> organizationTypeResultMap = organizationTypeService.getOne(organizationType);
		if (MapUtils.isEmpty(organizationTypeResultMap)) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg( Prompt.bundle("organizationType.is.not.exists"));
		}
		// 判断机构是否存在
		Map<String, Object> organizationRepeatResultMap = organizationService.getRepeat(organization);
		if (!MapUtils.isEmpty(organizationRepeatResultMap)) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.code.is.conflict"));
		}
		 设置默认值 
		OrganizationUser organizationUser = organization.getOrganizationUser();
		String phone = organizationUser.getPhone();
		String password = phone.substring(phone.length() - 6);
		organizationUser.setPassword(MD5Util.getInstance().getMD5Code(password));
		organizationUser.setCreateDate(new Date());
		organizationUser.setInitPassword(true);
		organizationUser.setPhoneStatus(OrganizationUserStatusCode.OrganizationUser.PHONESTATUSUNAUTH.getValue());
		organizationUser.setStatus(OrganizationUserStatusCode.OrganizationUser.ENABLE.getValue());
		 检查机构用户数据是否重复 
		检查数据是否重复
		// 电话号码不重复
		OrganizationUser  organizationUserByPhone = new OrganizationUser();
		organizationUserByPhone.setPhone(organizationUser.getPhone());
		Map<String, Object> organizationUserMap=organizationUserService.getRepeat(organizationUserByPhone);
		if (organizationUserMap!=null &&organizationUserMap.size()>0) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.phone.is.exists"));
		}
		// 账号不重复
		OrganizationUser  organizationUserByAccount = new OrganizationUser();
		organizationUserByAccount.setAccount(organizationUser.getAccount());
		Map<String, Object> organizationUserByAccountMap=organizationUserService.getRepeat(organizationUserByAccount);
		if (organizationUserByAccountMap!=null &&organizationUserByAccountMap.size()>0) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.account.is.exists"));
		}
		// 添加数据
		organization.setStatus(OrganizationStatusCode.Organization.ENABLE.getValue());
		organization.setCreateDate(new Date());
		// 获取传入的机构用户证件列表
		List<OrganizationUserCertificate> organizationUserCertificates = organization.getOrganizationUserCertificates();
		// 添加证件
		// 指定机构用户id
		for (OrganizationUserCertificate organizationUserCertificate : organizationUserCertificates) {
			// 判断证件是否重复
			if (MapUtils.isNotEmpty(organizationUserCertificateService.getRepeat(organizationUserCertificate))) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organizationUser.certificates.is.exists"));
			}
			organizationUserCertificate.setCreateDate(new Date());
			if (String.valueOf(OrganizationUserCertificateCode.CertificateTypeCode.IDCARD.getValue()).equals(organizationUserCertificate.getCertificateType())) {
				organizationUser.setIdCard(organizationUserCertificate.getCertificateNumber());
			}
		}
		DoctorInfo doctorInfo = organization.getDoctorInfo();
		if (null != doctorInfo) {
			 检查医生信息是否重复 
			String certification = doctorInfo.getCertification();
			if (null != certification && !"".equals(certification)) {
				Map<String, Object> doctorInfoMap=doctorInfoService.getRepeat(doctorInfo);
				if (doctorInfoMap!=null &&doctorInfoMap.size()>0) {
					return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("doctorInfo.certification.is.not.exists"));
				}
			}
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("doctorInfo.certification.is.not.null"));
		}
		 新增机构 
		if (organizationService.insert(organization) > 0) {
			
			 新增机构用户 
			if (organizationUserService.insert(organizationUser) > 0) {
				for (OrganizationUserCertificate organizationUserCertificate : organizationUserCertificates) {
					organizationUserCertificate.setOrganizationUserId(organizationUser.getId());
				}
				// 批量添加机构用户证件
				if (organizationUserCertificateService.batchInsert(organizationUserCertificates) == organizationUserCertificates.size()) {
					 设置关联表信息 
					DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = new DoctorOrganizationDepartmentDuty();
					doctorOrganizationDepartmentDuty.setOrganizationUserId(organizationUser.getId());
					doctorOrganizationDepartmentDuty.setOrganizationId(organization.getId());
					doctorOrganizationDepartmentDuty.setIsManager(true);
					 新增关联表信息 
					if (doctorOrganizationDepartmentDutyService.insert(doctorOrganizationDepartmentDuty) > 0) {
					 医生信息是否重复 
				  	if (null != doctorInfo) {
						doctorInfo.setCreateDate(new Date());
						 设置机构用户id 
						doctorInfo.setOrganizationUserId(organizationUser.getId());
						 新增医生表 
						if (doctorInfoService.insert(doctorInfo) > 0) {
								return new JsonApi(ApiCodeEnum.OK);
							}
							throw new RuntimeException();
						}
						return new JsonApi(ApiCodeEnum.OK);
						}
						throw new RuntimeException();
					}
					throw new RuntimeException();
				}
				throw new RuntimeException();
			}
		return new JsonApi(ApiCodeEnum.FAIL);
	*/
		return new JsonApi(ApiCodeEnum.FAIL);
		}
}
