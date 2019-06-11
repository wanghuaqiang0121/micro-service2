package org.web.module.organization.user.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.user.domain.DoctorOrganizationDepartmentDuty;
import org.web.module.organization.user.domain.OrganizationUserModule;
import org.web.module.organization.user.global.BaseGlobal;
import org.web.module.organization.user.global.BaseGlobalEnum;
import org.web.module.organization.user.sevice.DoctorOrganizationDepartmentDutyService;
import org.web.module.organization.user.sevice.OrganizationUserModuleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description:  机构用户模块关联
 */
@RestController
public class OrganizationUserModuleRelationController {

	@Resource
	private OrganizationUserModuleService organizationUserModuleService;
	@Resource
	private DoctorOrganizationDepartmentDutyService doctorOrganizationDepartmentDutyService;
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserModule
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 添加机构用户模块关联
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-module:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/user/module" })
	public JsonApi insert(
			@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationUserModule organizationUserModule, BindingResult result) {
		
		Map<String, Object> organizationUserModuleMap = organizationUserModuleService.getRepeat(organizationUserModule);
		if (organizationUserModuleMap!=null && !organizationUserModuleMap.isEmpty()) {
			OrganizationUserModule organizationUserModuleNew = new OrganizationUserModule();
			organizationUserModuleNew.setId((Integer)organizationUserModuleMap.get("id"));
			organizationUserModuleNew.setStatus(BaseGlobalEnum.organizationUserModule.ENABLE.getValue());
			if (organizationUserModuleService.update(organizationUserModuleNew)>0) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("id", organizationUserModuleMap.get("id"));
				return new JsonApi(ApiCodeEnum.OK,resultMap);
			}
		}else {
			organizationUserModule.setStatus(BaseGlobalEnum.organizationUserModule.ENABLE.getValue());
			if (organizationUserModuleService.insert(organizationUserModule)>0) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("id", organizationUserModule.getId());
				return new JsonApi(ApiCodeEnum.OK,resultMap);
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
		
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserModule
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 查询机构用户和模块关联列表
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-module:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/user/modules" })
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class }) OrganizationUserModule organizationUserModule, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		/*设置状态为启用*/ 
		organizationUserModule.setStatus(BaseGlobalEnum.organizationUserModule.ENABLE.getValue());
		/*设置机构id*/ 
		organizationUserModule.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationUserModule.getPage(), organizationUserModule.getPageSize());
		List<Map<String, Object>> organizationUserModuleList = organizationUserModuleService.getList(organizationUserModule);
		if (organizationUserModuleList !=null && !organizationUserModuleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),organizationUserModuleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserModule
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 机构用户在该机构下拥有和未拥有的模块列表（判断是否是超级管理员）
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-module:get-have-not-have-modules" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/user/haveAndNotHaveListmodules" })
	public JsonApi getHaveNotHaveModules(
			@Validated({ BaseEntity.SelectAll.class }) OrganizationUserModule organizationUserModule, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		/*设置状态为启用*/ 
		organizationUserModule.setStatus(BaseGlobalEnum.organizationUserModule.ENABLE.getValue());
		/*设置机构id*/ 
		organizationUserModule.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationUserModule.getPage(), organizationUserModule.getPageSize());
		/*机构用户在该机构下拥有和未拥有的模块列表*/ 
		List<Map<String, Object>> organizationSiteList = organizationUserModuleService.haveAndNotHaveModules(organizationUserModule);
		if (organizationSiteList !=null && !organizationSiteList.isEmpty() ) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),organizationSiteList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserModule
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 机构拥有的模块列表
	 */
	@RequiresAuthentication(value = { "web-module-organization-user:organization-user-module:get-organization-have-modules" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/user/haveModules" })
	public JsonApi getOrganizationHaveModules(
			@Validated({ BaseEntity.SelectAll.class }) OrganizationUserModule organizationUserModule, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		
		DoctorOrganizationDepartmentDuty  doctorOrganizationDepartmentDuty = new DoctorOrganizationDepartmentDuty();
		doctorOrganizationDepartmentDuty.setOrganizationUserId(organizationUserModule.getOrganizationUserId());
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		Map<String, Object> doctorOrganizationDepartmentDutyOneMap = doctorOrganizationDepartmentDutyService.getOne(doctorOrganizationDepartmentDuty);
		if (doctorOrganizationDepartmentDutyOneMap!=null && !doctorOrganizationDepartmentDutyOneMap.isEmpty()) {
			boolean isManager = (boolean) doctorOrganizationDepartmentDutyOneMap.get("isManager");
			/*如果是管理员*/ 
			if (isManager) {
				/* 查询当前机构的模块*/
				/*设置机构id*/ 
				organizationUserModule.setOrganizationId(organizationId);
				/*设置状态为启用*/ 
				organizationUserModule.setStatus(BaseGlobalEnum.organizationUserModule.ENABLE.getValue());
				Page<?> page = PageHelper.startPage(organizationUserModule.getPage(), organizationUserModule.getPageSize());
				/*机构拥有的模块列表*/ 
				List<Map<String, Object>> organizationSiteList = organizationUserModuleService.organizationHaveModules(organizationUserModule);
				if (organizationSiteList !=null && !organizationSiteList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),organizationSiteList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}else if (!isManager) {
				/*设置状态为启用*/ 
				organizationUserModule.setStatus(BaseGlobalEnum.organizationUserModule.ENABLE.getValue());
				/*设置机构id*/ 
				organizationUserModule.setOrganizationId(organizationId);
				Page<?> page = PageHelper.startPage(organizationUserModule.getPage(), organizationUserModule.getPageSize());
				/*机构用户在该机构下拥有的模块列表*/ 
				List<Map<String, Object>> organizationSiteList = organizationUserModuleService.haveModules(organizationUserModule);
				if (organizationSiteList !=null && !organizationSiteList.isEmpty() ) {
					return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),organizationSiteList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationUserModule
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  删除机构用户模块关联（假删除）
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-module:delete" },level=Level.OPERATION)
	@DeleteMapping(value = { "/organization/user/module/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id,
			@Validated({ BaseEntity.Delete.class }) OrganizationUserModule organizationUserModule, BindingResult result) {
		/*设置id*/ 
		organizationUserModule.setId(id);
		/*设置状态为禁用*/ 
		organizationUserModule.setStatus(BaseGlobalEnum.organizationUserModule.DISABLE.getValue());
		/* 删除机构用户模块关联（假删除）*/
		if (organizationUserModuleService.update(organizationUserModule) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	
}
