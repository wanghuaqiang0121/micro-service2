package org.web.module.organization.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import org.web.module.organization.domain.ModuleOperationalRole;
import org.web.module.organization.domain.OrganizationRole;
import org.web.module.organization.global.BaseGlobalEnum;
import org.web.module.organization.service.OrganizationRoleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 机构角色
 */
@RestController
public class OrganizationRoleController {
	
	
	@Resource
	private OrganizationRoleService organizationRoleService;
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationRole
	 * @return {@link JsonApi}
	 * @description: 新增
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-role:insert" })
	@PostMapping(value = { "/organization/role" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationRole organizationRole){
		/*判断数据是否重复*/
		Map<String, Object> organizationRoleMap = organizationRoleService.getRepeat(organizationRole);
		if (organizationRoleMap!=null && !organizationRoleMap.isEmpty()) {
			OrganizationRole organizationRoleNew = new OrganizationRole();
			organizationRoleNew.setId((Integer)organizationRoleMap.get("id"));
			organizationRoleNew.setStatus(BaseGlobalEnum.Organization.ENABLE.getValue());
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("id", (Integer)organizationRoleMap.get("id"));
			if (organizationRoleService.update(organizationRoleNew)>0) {
				return new JsonApi(ApiCodeEnum.OK,resultMap);
			}
		}else {
			organizationRole.setStatus(BaseGlobalEnum.Organization.ENABLE.getValue());
			if (organizationRoleService.insert(organizationRole)>0) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("id", organizationRole.getId());
				return new JsonApi(ApiCodeEnum.OK,resultMap);
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
		
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 批量新增
	 */
	@Transactional
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-role:batch-insert" })
	@PostMapping(value = { "/organization/role/batch" })
	public JsonApi batchInsert(
			@Validated({ OrganizationRole.batchInsert.class }) @RequestBody OrganizationRole organizationRole,
			BindingResult result){
		List<Integer> operationalRoleList = organizationRole.getOperationalRoles();
		operationalRoleList.removeAll(Collections.singleton(null));
		HashSet<Integer> hashSet = new HashSet<>(operationalRoleList);
		if (hashSet.size() != operationalRoleList.size()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		List<OrganizationRole> organizationRolesList = new  ArrayList<OrganizationRole>();
		for (Integer operationalRoleId : operationalRoleList) {
			OrganizationRole organizationRoleNew = new OrganizationRole();
			organizationRoleNew.setOrganizationId(organizationRole.getOrganizationId());
			organizationRoleNew.setSystemModuleId(organizationRole.getSystemModuleId());
			organizationRoleNew.setOperationalRoleId(operationalRoleId);
			organizationRoleNew.setStatus(BaseGlobalEnum.Organization.ENABLE.getValue());
			organizationRolesList.add(organizationRoleNew);
		}
		List<Map<String, Object>> organizationRoleList = organizationRoleService.getList(organizationRole);
		if (operationalRoleList == null || operationalRoleList.isEmpty()) {
			if (organizationRoleList == null || organizationRoleList.isEmpty()) {
				return new JsonApi(ApiCodeEnum.OK);
			}
			if (organizationRoleService.batchDelete(organizationRole) >0 ) {
				return new JsonApi(ApiCodeEnum.OK);
			}
		}else {
			if (organizationRoleList == null || organizationRoleList.isEmpty()) {
				if (organizationRoleService.batchInsert(organizationRolesList) == organizationRolesList.size()) {
					return new JsonApi(ApiCodeEnum.OK);
				}
			}
			if (organizationRoleService.batchDelete(organizationRole) >0 ) {
				
				if (organizationRoleService.batchInsert(organizationRolesList) == organizationRolesList.size()) {
					return new JsonApi(ApiCodeEnum.OK);
				}
				throw new RuntimeException();
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param moduleOperationalRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构在该模块下拥有和未拥有的角色列表
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:module-operational-role:get-module-have-not-have-role-list" })
	@RequestMapping(value = { "/organization/role/isChoose" }, method = RequestMethod.GET)
	public JsonApi getModuleHaveNotHaveRoleList(@Validated({ModuleOperationalRole.ModuleOperationalRoleList.class})ModuleOperationalRole moduleOperationalRole,
			BindingResult result) {
		Page<?> page = PageHelper.startPage(moduleOperationalRole.getPage(), moduleOperationalRole.getPageSize());
		List<Map<String, Object>> operationalRoleList = organizationRoleService.getModuleOperationalRoleList(moduleOperationalRole);
		if (operationalRoleList != null && !operationalRoleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), operationalRoleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询列表
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-role:get-list" })
	@GetMapping(value = { "/organization/module/role" })
	public JsonApi getList(@Validated({BaseEntity.SelectAll.class})OrganizationRole organizationRole,
			BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationRole.getPage(), organizationRole.getPageSize());
		List<Map<String, Object>> roleList = organizationRoleService.getList(organizationRole);
		if (roleList != null && !roleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), roleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除(修改状态)
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-role:update" })
	@PutMapping(value = { "/organization/role/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,
			@Validated({BaseEntity.Update.class})OrganizationRole organizationRole,
			BindingResult result) {
		organizationRole.setId(id);
		organizationRole.setStatus(BaseGlobalEnum.Organization.DISABLE.getValue());
		/*修改*/
		if (organizationRoleService.update(organizationRole) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
