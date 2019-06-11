package org.web.module.organization.user.controller;

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
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.token.RedisSession;
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
import org.web.module.organization.user.domain.OrganizationUser;
import org.web.module.organization.user.domain.OrganizationUserRole;
import org.web.module.organization.user.global.BaseGlobal;
import org.web.module.organization.user.global.BaseGlobalEnum;
import org.web.module.organization.user.sevice.DoctorOrganizationDepartmentDutyService;
import org.web.module.organization.user.sevice.OrganizationUserRoleService;
import org.web.module.organization.user.sevice.OrganizationUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构用户权限关联
 */
@RestController
public class OrganizationUserRoleRelationController {

	@Resource
	private OrganizationUserRoleService organizationUserRoleService;
	@Resource
	private RedisCacheManager cacheManager;
	@Resource
	private DoctorOrganizationDepartmentDutyService doctorOrganizationDepartmentDutyService;
	@Resource
	private OrganizationUserService organizationUserService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增机构用户角色
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-role:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/user/role" })
	public JsonApi insert(
			@RequestBody @Validated({ BaseEntity.Insert.class }) OrganizationUserRole organizationUserRole,BindingResult result){
		/*判断是否重复*/
		Map<String, Object> organizationUserRoleMap = organizationUserRoleService.getRepeat(organizationUserRole);
		if (organizationUserRoleMap!=null && !organizationUserRoleMap.isEmpty()) {
			OrganizationUserRole organizationUserRoleNew = new OrganizationUserRole();
			organizationUserRoleNew.setId((Integer)organizationUserRoleMap.get("id"));
			organizationUserRoleNew.setStatus(BaseGlobalEnum.organizationUserRole.ENABLE.getValue());
			if (organizationUserRoleService.update(organizationUserRoleNew)>0) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("id", organizationUserRoleNew.getId());
				return new JsonApi(ApiCodeEnum.OK,resultMap);
			}
		}else {
			organizationUserRole.setStatus(BaseGlobalEnum.organizationUserRole.ENABLE.getValue());
			if (organizationUserRoleService.insert(organizationUserRole)>0) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("id", organizationUserRole.getId());
				return new JsonApi(ApiCodeEnum.OK,resultMap);
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUserRole
	 * @param result
	 * @param token
	 * @return {@link JsonApi}
	 * @description: 查询用户机构列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-organization-user:organization-user-role:get-list" })
	@GetMapping(value = { "/organizationUser/role/organizations" })
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class }) OrganizationUserRole organizationUserRole, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/* 获取缓存信息*/
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationUserRole.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		Page<?> page = PageHelper.startPage(organizationUserRole.getPage(), organizationUserRole.getPageSize());
		List<Map<String, Object>> organizationUserRoleOrganiztionsList = organizationUserRoleService.getList(organizationUserRole);
		if (organizationUserRoleOrganiztionsList != null && !organizationUserRoleOrganiztionsList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserRoleOrganiztionsList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserRole
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 机构用户在该机构下拥有和未拥有的角色列表
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-role:get-organiztion-user-have-not-have-roles" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/user/have/nothave/role" })
	public JsonApi getOrganiztionUserHaveNotHaveRoles(
			@Validated({ OrganizationUserRole.haveAndNotHave.class }) OrganizationUserRole organizationUserRole,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId){
		organizationUserRole.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationUserRole.getPage(), organizationUserRole.getPageSize());
		List<Map<String, Object>>  organizationUserRoleList=organizationUserRoleService.getHaveAndNotHave(organizationUserRole);
		if (organizationUserRoleList!=null && !organizationUserRoleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserRoleList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationUserRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除机构用户和角色关联
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-role:delete" },level=Level.OPERATION)
	@DeleteMapping(value = { "/organization/user/role/{id}" })
	public JsonApi delete(
			@PathVariable("id")Integer id,
			@Validated({ BaseEntity.Delete.class }) OrganizationUserRole organizationUserRole,BindingResult result){
		organizationUserRole.setId(id);
		organizationUserRole.setStatus(BaseGlobalEnum.organizationUserRole.DISABLE.getValue());
		if (organizationUserRoleService.update(organizationUserRole)>0) {
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
	 * @param organizationId
	 * @param moduleId
	 * @return {@link JsonApi}
	 * @description:  查询机构用户模块菜单列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-organization-user:organization-user:get-organization-user-module-menu-list" })
	@GetMapping(value = { "/organizationUser/module/menus" })
	public JsonApi getOrganizationUserModuleMenuList(
			@Validated({ OrganizationUser.ModuleMenuList.class }) OrganizationUser organizationUser,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@RequestHeader(required = true, value = BaseGlobal.MODULE_ID) Integer moduleId) {
		organizationUser.setModuleId(moduleId);
		organizationUser.setOrganizationId(organizationId);
		/*获取缓存信息*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationUser.setId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		/* 判断是否是超级管理员 */
		DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = new DoctorOrganizationDepartmentDuty();
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		doctorOrganizationDepartmentDuty.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		Map<String, Object> repeatDoctorOrganizationMap = doctorOrganizationDepartmentDutyService.getRepeat(doctorOrganizationDepartmentDuty);
		if (MapUtils.isNotEmpty(repeatDoctorOrganizationMap)) {
			boolean isManager = (boolean) repeatDoctorOrganizationMap.get("isManager");
			/* 是超级管理员 */
			if (isManager) {
				Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
				/*查询该机构该模块下所有菜单*/ 
				List<Map<String, Object>> ManagerModuleMenuList = organizationUserService.getManagerModuleMenuList(organizationUser);
				if (ManagerModuleMenuList != null && !ManagerModuleMenuList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), ManagerModuleMenuList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			} else {
				/* 不是超级管理员 */
				Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
				/*查询该机构该用户该模块下所有菜单*/ 
				List<Map<String, Object>> organizationUserMenuList = organizationUserService.getOrganizationUserModuleMenuList(organizationUser);
				if (organizationUserMenuList != null && !organizationUserMenuList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserMenuList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
		
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @param result
	 * @param token
	 * @param organizationId
	 * @param moduleId
	 * @return{@link JsonApi}
	 * @description: 查询用户模块角色列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-organization-user:organization-user:get-organization-user-module-role-list" })
	@GetMapping(value = { "/organizationUser/module/roles" })
	public JsonApi getOrganizationUserModuleRoleList(
			@Validated({ OrganizationUser.ModuleRoleList.class }) OrganizationUser organizationUser,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@RequestHeader(required = true, value = BaseGlobal.MODULE_ID) Integer moduleId) {
		organizationUser.setModuleId(moduleId);
		organizationUser.setOrganizationId(organizationId);
		/* 获取缓存信息*/
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationUser.setId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		/* 判断是否是超级管理员 */
		DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = new DoctorOrganizationDepartmentDuty();
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		doctorOrganizationDepartmentDuty.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		Map<String, Object> repeatDoctorOrganizationMap = doctorOrganizationDepartmentDutyService.getRepeat(doctorOrganizationDepartmentDuty);
		if (MapUtils.isNotEmpty(repeatDoctorOrganizationMap)) {
			boolean isManager = (boolean) repeatDoctorOrganizationMap.get("isManager");
			/* 是超级管理员 */
			if (isManager) {
				Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
				/* 查询机构角色列表*/
				List<Map<String, Object>> ManagerRoleList = organizationUserService.getManagerRoleList(organizationUser);
				if (ManagerRoleList != null && !ManagerRoleList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), ManagerRoleList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			} else {
				/* 不是超级管理员 */
				Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
				List<Map<String, Object>> organizationUserRoleList = organizationUserService.getOrganizationUserModuleRoleList(organizationUser);
				if (organizationUserRoleList != null && !organizationUserRoleList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserRoleList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @param result
	 * @param token
	 * @param organizationId
	 * @param moduleId
	 * @return {@link JsonApi}
	 * @description:  查询用户模块权限列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-organization-user:organization-user:get-organization-user-module-permission-list" })
	@GetMapping(value = { "/organizationUser/module/permissions" })
	public JsonApi getOrganizationUserModulePermissionList(
			@Validated({ OrganizationUser.ModulePermissionList.class }) OrganizationUser organizationUser,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@RequestHeader(required = true, value = BaseGlobal.MODULE_ID) Integer moduleId) {
		organizationUser.setModuleId(moduleId);
		organizationUser.setOrganizationId(organizationId);
		/* 获取缓存信息*/
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationUser.setId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		/* 判断是否是超级管理员 */
		DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = new DoctorOrganizationDepartmentDuty();
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		doctorOrganizationDepartmentDuty.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		Map<String, Object> repeatDoctorOrganizationMap = doctorOrganizationDepartmentDutyService.getRepeat(doctorOrganizationDepartmentDuty);
		if (MapUtils.isNotEmpty(repeatDoctorOrganizationMap)) {
			boolean isManager = (boolean) repeatDoctorOrganizationMap.get("isManager");
			/* 是超级管理员 */
			if (isManager) {
				Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
				List<Map<String, Object>> ManagerPermissionList = organizationUserService.getManagerPermissionList(organizationUser);
				if (ManagerPermissionList != null && !ManagerPermissionList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), ManagerPermissionList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			} else {
				/* 不是超级管理员 */
				Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
				List<Map<String, Object>> organizationUserPermissionList = organizationUserService.getOrganizationUserModulePermissionList(organizationUser);
				if (organizationUserPermissionList != null && !organizationUserPermissionList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserPermissionList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationUser
	 * @param result
	 * @param token
	 * @param organizationId
	 * @param moduleId
	 * @return {@link JsonApi}
	 * @description: 查询用户模块操作列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-organization-user:organization-user:get-organization-user-module-operation-list" })
	@GetMapping(value = { "/organizationUser/module/operations" })
	public JsonApi getOrganizationUserModuleOperationList(
			@Validated({ OrganizationUser.ModuleOperationList.class }) OrganizationUser organizationUser,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId,
			@RequestHeader(required = true, value = BaseGlobal.MODULE_ID) Integer moduleId) {
		organizationUser.setModuleId(moduleId);
		organizationUser.setOrganizationId(organizationId);
		/* 获取缓存信息*/
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationUser.setId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		/* 判断是否是超级管理员 */
		DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty = new DoctorOrganizationDepartmentDuty();
		doctorOrganizationDepartmentDuty.setOrganizationId(organizationId);
		doctorOrganizationDepartmentDuty.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		Map<String, Object> repeatDoctorOrganizationMap = doctorOrganizationDepartmentDutyService.getRepeat(doctorOrganizationDepartmentDuty);
		if (MapUtils.isNotEmpty(repeatDoctorOrganizationMap)) {
			boolean isManager = (boolean) repeatDoctorOrganizationMap.get("isManager");
			/* 是超级管理员 */
			if (isManager) {
				Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
				List<Map<String, Object>> ManagerOperationList = organizationUserService.getManagerOperationList(organizationUser);
				if (ManagerOperationList != null && !ManagerOperationList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), ManagerOperationList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			} else {
				/* 不是超级管理员 */
				Page<?> page = PageHelper.startPage(organizationUser.getPage(), organizationUser.getPageSize());
				List<Map<String, Object>> organizationUserOperationList = organizationUserService.getOrganizationUserModuleOperationList(organizationUser);
				if (organizationUserOperationList != null && !organizationUserOperationList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationUserOperationList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}

}
