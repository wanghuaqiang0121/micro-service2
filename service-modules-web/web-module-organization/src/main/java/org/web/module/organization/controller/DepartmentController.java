package org.web.module.organization.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import org.web.module.organization.domain.Department;
import org.web.module.organization.domain.user.DoctorOrganizationDepartmentDuty;
import org.web.module.organization.global.BaseGlobal;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.DepartmentService;
import org.web.module.organization.service.user.DoctorOrganizationDepartmentDutyService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年10月11日
 * @description: 部门
 */
@RestController
public class DepartmentController {

	@Resource
	private DepartmentService departmentService;
	@Resource
	private DoctorOrganizationDepartmentDutyService doctorOrganizationDepartmentDutyService;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param department
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 新增
	 */
	@RequiresAuthentication(value = { "web-module-organization:department:insert" }, level = Level.OPERATION)
	@PostMapping(value = { "/department" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) Department department, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		/* 设置时间 */
		department.setCreateDate(new Date());
		department.setOrganizationId(organizationId);
		/* 判断部门是否重复 */
		Map<String, Object> departmentMap = departmentService.getRepeat(department);
		if (departmentMap != null && !departmentMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("department.name.is.conflict"));
		}
		if (departmentService.insert(department) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}

		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param department
	 * @param result
	 * @param organizationId
	 * @return  {@link JsonApi}
	 * @description: 修改
	 */
	@RequiresAuthentication(value = { "web-module-organization:department:update" }, level = Level.OPERATION)
	@PutMapping(value = { "/department/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @RequestBody @Validated({ BaseEntity.Update.class }) Department department, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		/* 判断数据是否存在 */
		department.setId(id);
		Map<String, Object> departmentMap = departmentService.getOne(department);
		if (departmentMap == null || departmentMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 判断部门是否重复 名字不能重复 */
		department.setOrganizationId(organizationId);
		Map<String, Object> departmentRepeatMap = departmentService.getRepeat(department);
		if (departmentRepeatMap != null && !departmentRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("department.name.is.conflict"));
		}
		if (departmentService.update(department) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param department
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 查询详情
	 */
	@RequiresAuthentication(value = { "web-module-organization:department:get-detail" }, level = Level.OPERATION)
	@GetMapping(value = { "/department/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) Department department, BindingResult result) {
		/* 设置主键 */
		department.setId(id);
		Map<String, Object> departmentMap = departmentService.getOne(department);
		if (departmentMap != null && !departmentMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, departmentMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param department
	 * @param result
	 * @param organizationId
	 * @return  {@link JsonApi}
	 * @description: 查询列表
	 */
	@RequiresAuthentication(value = { "web-module-organization:department:get-list" }, level = Level.OPERATION)
	@GetMapping(value = { "/departments" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) Department department, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		department.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(department.getPage(), department.getPageSize());
		List<Map<String, Object>> departmentList = departmentService.getList(department);
		if (departmentList != null && !departmentList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), departmentList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);

	}


	/** 
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param id
	 * @param department
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 删除
	 */
	@RequiresAuthentication(value = { "web-module-organization:department:delete" }, level = Level.OPERATION)
	@DeleteMapping(value = { "/department/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id, @Validated({ BaseEntity.Delete.class }) Department department, BindingResult result) {
		/* 判断数据是否存在 */
		department.setId(id);
		Map<String, Object> departmentMap = departmentService.getOne(department);
		if (departmentMap == null || departmentMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 判断机构是否已被使用 */
		DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = new DoctorOrganizationDepartmentDuty();
		doctorOrganizationDepartmentDuty.setDepartmentId(id);
		List<Map<String, Object>> doctorOrganizationDepartmentDutyList = doctorOrganizationDepartmentDutyService.getList(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyList != null && !doctorOrganizationDepartmentDutyList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("department.is.used"));
		}
		/* 删除部门 */
		if (departmentService.delete(department) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
