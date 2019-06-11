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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.HospitalDepartment;
import org.web.module.organization.domain.Organization;
import org.web.module.organization.global.BaseGlobal;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.HospitalDepartmentService;
import org.web.module.organization.service.OrganizationService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年10月11日
 * @description: 科室
 */
@RestController
public class HospitalDepartmentController {

	@Resource
	private HospitalDepartmentService hospitalDepartmentService;

	@Resource
	private OrganizationService organizationService;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationId
	 * @param hospitalDepartment
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 新增
	 */
	@RequiresAuthentication(value = { "web-module-organization:hospital-department:insert" }, level = Level.OPERATION)
	@PostMapping(value = { "/hospital/department" })
	public JsonApi insert(@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@RequestBody @Validated({ BaseEntity.Insert.class }) HospitalDepartment hospitalDepartment, BindingResult result) {

		/* 根据机构id查询医疗机构id */
		Organization organization = new Organization();
		organization.setId(organizationId);
		/* 设置医疗机构id */
		Map<String, Object> organizationMap = organizationService.getOne(organization);
		if (organizationMap == null || organizationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organizationInfo.is.null"));
		}
		Integer medicalOrganizationInfoId = (Integer) organizationMap.get("medicalOrganizationInfoId");
		if (medicalOrganizationInfoId == null) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("medicalOrganizationInfo.is.null"));
		}
		hospitalDepartment.setMedicalOrganizationInfoId(medicalOrganizationInfoId);
		/* 判断重复 */
		Map<String, Object> hospitalDepartmentMap = hospitalDepartmentService.getRepeat(hospitalDepartment);
		if (MapUtils.isNotEmpty(hospitalDepartmentMap)) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("hospitalDepartment.name.is.conflict"));
		}
		/* 设置时间 */
		hospitalDepartment.setCreateDate(new Date());
		if (hospitalDepartmentService.insert(hospitalDepartment) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param hospitalDepartment
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 修改
	 */
	@RequiresAuthentication(value = { "web-module-organization:hospital-department:update" }, level = Level.OPERATION)
	@PutMapping(value = { "/hospital/department/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @RequestBody @Validated({ BaseEntity.Update.class }) HospitalDepartment hospitalDepartment, BindingResult result) {
		/* 判断数据是否存在 */
		hospitalDepartment.setId(id);
		Map<String, Object> hospitalDepartmentMap = hospitalDepartmentService.getOne(hospitalDepartment);
		if (hospitalDepartmentMap == null || hospitalDepartmentMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}

		if (hospitalDepartmentService.update(hospitalDepartment) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param hospitalDepartment
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 详情
	 */
	@RequiresAuthentication(value = { "web-module-organization:hospital-department:get-detail" }, level = Level.OPERATION)
	@GetMapping(value = { "/hospital/department/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) HospitalDepartment hospitalDepartment, BindingResult result) {
		/* 设置主键 */
		hospitalDepartment.setId(id);
		Map<String, Object> hospitalDepartmentMap = hospitalDepartmentService.getOne(hospitalDepartment);
		if (hospitalDepartmentMap != null && !hospitalDepartmentMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, hospitalDepartmentMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);

	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationId
	 * @param hospitalDepartment
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 科室列表
	 */
	@RequiresAuthentication(value = { "web-module-organization:hospital-department:get-list" }, level = Level.OPERATION)
	@GetMapping(value = { "/hospital/departments" })
	public JsonApi getList(@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId, @Validated({ BaseEntity.SelectAll.class }) HospitalDepartment hospitalDepartment,
			BindingResult result) {
		hospitalDepartment.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(hospitalDepartment.getPage(), hospitalDepartment.getPageSize());
		// hospitalDepartment.setStatus(Department.ENABLE.getValue());
		List<Map<String, Object>> departmentList = hospitalDepartmentService.getList(hospitalDepartment);
		if (departmentList != null && !departmentList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), departmentList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);

	}


	/** 
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param hospitalDepartment
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 删除
	 */
	@RequiresAuthentication(value = { "web-module-organization:hospital-department:delete" }, level = Level.OPERATION)
	@DeleteMapping(value = { "/hospital/department/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id, @Validated({ BaseEntity.Delete.class }) HospitalDepartment hospitalDepartment, BindingResult result) {
		/* 判断数据是否存在 */
		hospitalDepartment.setId(id);
		Map<String, Object> hospitalDepartmentMap = hospitalDepartmentService.getOne(hospitalDepartment);
		if (hospitalDepartmentMap == null || hospitalDepartmentMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 删除科室 */
		if (hospitalDepartmentService.delete(hospitalDepartment) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年11月14日
	 * @param organizationId
	 * @param hospitalDepartment
	 * @param result
	 * @return
	 * @description: 医院科室判断重复
	 * TODO 待添加到新项目
	 */
	@RequiresAuthentication(value = { "organization:configure:hospitalDepartment:validRepeat" },level=Level.OPERATION)
	@GetMapping(value = { "/hospital/department/valid/repeat" })
	public JsonApi validRepeat(
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@Validated({ HospitalDepartment.Repeat.class }) HospitalDepartment  hospitalDepartment,BindingResult result){
		
		// 根据机构id查询医疗机构id
		Organization organization =new  Organization();
		organization.setId(organizationId);
		// 设置医疗机构id
		Map<String, Object> organizationMap = organizationService.getOne(organization);
		if (organizationMap == null || organizationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("organizationInfo.is.null"));
		}
		Integer medicalOrganizationInfoId = (Integer)organizationMap.get("medicalOrganizationInfoId");
		if (medicalOrganizationInfoId == null) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("medicalOrganizationInfo.is.null"));
		}
		hospitalDepartment.setMedicalOrganizationInfoId(medicalOrganizationInfoId);
		// 判断重复
		Map<String, Object> resultMap = new HashMap<>(); 
		Map<String, Object> hospitalDepartmentMap = hospitalDepartmentService.getRepeat(hospitalDepartment);
		if (MapUtils.isNotEmpty(hospitalDepartmentMap)) {
			resultMap.put("isRepeat",true);
			return new JsonApi(ApiCodeEnum.OK,resultMap).setMsg(Prompt.bundle("hospitalDepartment.name.is.conflict"));
		}
		resultMap.put("isRepeat",false);
		return new JsonApi(ApiCodeEnum.OK,resultMap);
		
	}
}
