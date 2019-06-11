package org.web.module.organization.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.MedicalOrganizationInfo;
import org.web.module.organization.service.MedicalOrganizationInfoService;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 医疗机构扩展
 */
@RestController
public class MedicalOrganizationInfoController {
	@Resource
	private MedicalOrganizationInfoService medicalOrganizationInfoService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param medicalOrganizationInfo
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 新增医疗机构扩展
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-organization:medical-organization-info:insert" })
	@PostMapping(value = { "/medical/organization/info" })
	public JsonApi insert(
			@Validated({ BaseEntity.Insert.class }) @RequestBody MedicalOrganizationInfo medicalOrganizationInfo,
			BindingResult result) {
		medicalOrganizationInfo.setCreateDate(new Date());
		Map<String, Object> medicalOrganizationInfoRepeatMap = medicalOrganizationInfoService
				.getRepeat(medicalOrganizationInfo);
		if (MapUtils.isNotEmpty(medicalOrganizationInfoRepeatMap)) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		if (medicalOrganizationInfoService.insert(medicalOrganizationInfo) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param medicalOrganizationInfo
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 修改医疗机构扩展
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-organization:medical-organization-info:update" })
	@PutMapping(value = { "/medical/organization/info/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,
			@Validated({ BaseEntity.Update.class }) @RequestBody MedicalOrganizationInfo medicalOrganizationInfo,
			BindingResult result) {
		medicalOrganizationInfo.setId(id);
		Map<String, Object> medicalOrganizationInfoDetailMap = medicalOrganizationInfoService
				.getDetail(medicalOrganizationInfo);
		if (MapUtils.isEmpty(medicalOrganizationInfoDetailMap)) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if (medicalOrganizationInfoService.update(medicalOrganizationInfo) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param medicalOrganizationInfo
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 查询医疗机构扩展详情
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = {"web-module-organization:medical-organization-info:get-detail" })
	@GetMapping(value = { "/medical/organization/info" })
	public JsonApi getDetail(@Validated({ BaseEntity.SelectOne.class }) MedicalOrganizationInfo medicalOrganizationInfo,
			BindingResult result) {
		Map<String, Object> medicalOrganizationInfoDetail = medicalOrganizationInfoService
				.getDetail(medicalOrganizationInfo);
		if (MapUtils.isNotEmpty(medicalOrganizationInfoDetail)) {
			return new JsonApi(ApiCodeEnum.OK, medicalOrganizationInfoDetail);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

}