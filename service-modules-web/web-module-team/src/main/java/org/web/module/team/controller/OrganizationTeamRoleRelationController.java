package org.web.module.team.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.token.RedisSession;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.team.domain.OrganizationOrganizationTeamRole;
import org.web.module.team.domain.OrganizationUserTeam;
import org.web.module.team.global.BaseGlobal;
import org.web.module.team.service.OrganizationOrganizationTeamRoleService;
import org.web.module.team.service.OrganizationUserTeamService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构团队角色关联表
 */
@RestController
public class OrganizationTeamRoleRelationController {

	@Resource
	private OrganizationOrganizationTeamRoleService organizationOrganizationTeamRoleService;
	@Resource
	private OrganizationUserTeamService organizationUserTeamService;
	@Resource
	private RedisCacheManager cacheManager;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @param result
	 * @param token
	 * @return  {@link JsonApi}
	 * @description: 查询用户团队列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-team:organization-organization-team-role:get-list" })
	@GetMapping(value = { "/organizationTeam/organization/teams" })
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class }) OrganizationOrganizationTeamRole organizationOrganizationTeamRole,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取缓存信息*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		/*设置参数*/ 
		organizationOrganizationTeamRole.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		Page<?> page = PageHelper.startPage(organizationOrganizationTeamRole.getPage(),organizationOrganizationTeamRole.getPageSize());
		/*用户团队列表*/ 
		List<Map<String, Object>> teamList = organizationOrganizationTeamRoleService.getList(organizationOrganizationTeamRole);
		if (teamList != null && !teamList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @param result
	 * @param token
	 * @param organizationTeamId
	 * @return  {@link JsonApi}
	 * @description: 查询机构用户团队角色列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-team:organization-organization-team-role:get-organization-user-team-roles" })
	@GetMapping(value = { "/organizationTeam/organization/team/roles" })
	public JsonApi getOrganizationUserTeamRoles(
			@Validated({OrganizationOrganizationTeamRole.TeamRoles.class }) OrganizationOrganizationTeamRole organizationOrganizationTeamRole,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
		organizationOrganizationTeamRole.setOrganizationTeamId(organizationTeamId);
		/*获取缓存信息*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationOrganizationTeamRole.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		/*查询用户在团队是否团队长*/ 
		OrganizationUserTeam organizationUserTeam = new OrganizationUserTeam();
		organizationUserTeam.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		organizationUserTeam.setOrganizationTeamId(organizationTeamId);
		/* 医生医生团队详情*/
		Map<String, Object> doctorDoctorTeamOneMap = organizationUserTeamService.getOne(organizationUserTeam);
		if (doctorDoctorTeamOneMap!=null && !doctorDoctorTeamOneMap.isEmpty()) {
			boolean isManager = (boolean) doctorDoctorTeamOneMap.get("isManager");
			/*用户在团队是团队长*/ 
			if (isManager) {
				Page<?> page = PageHelper.startPage(organizationOrganizationTeamRole.getPage(),organizationOrganizationTeamRole.getPageSize());
				/*查询团队长团队角色=所有团队角色*/ 
				List<Map<String, Object>> teamRoleList = organizationOrganizationTeamRoleService.getOrganizationOrganizationTeamManagerRoles(organizationOrganizationTeamRole);
				if (teamRoleList != null && !teamRoleList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamRoleList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			} else {/* 用户在团队不是团队长*/
				Page<?> page = PageHelper.startPage(organizationOrganizationTeamRole.getPage(),organizationOrganizationTeamRole.getPageSize());
				/*查询用户在团队拥有的角色*/ 
				List<Map<String, Object>> teamRoleList = organizationOrganizationTeamRoleService.getOrganizationOrganizationTeamRoles(organizationOrganizationTeamRole);
				if (teamRoleList != null && !teamRoleList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamRoleList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @param result
	 * @param token
	 * @param organizationTeamId
	 * @return {@link JsonApi}
	 * @description: 查询机构用户团队权限列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-team:organization-organization-team-role:get-organization-user-team-permissions" })
	@GetMapping(value = { "/organizationTeam/organization/team/permissions" })
	public JsonApi getOrganizationOrganizationTeamPermissions(
			@Validated({OrganizationOrganizationTeamRole.TeamPermissions.class }) OrganizationOrganizationTeamRole organizationOrganizationTeamRole,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
		organizationOrganizationTeamRole.setOrganizationTeamId(organizationTeamId);
		/*获取缓存信息*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationOrganizationTeamRole.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		/*查询用户在团队是否团队长*/ 
		OrganizationUserTeam doctorDoctorTeam = new OrganizationUserTeam();
		doctorDoctorTeam.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		doctorDoctorTeam.setOrganizationTeamId(organizationTeamId);
		Map<String, Object> doctorDoctorTeamOneMap = organizationUserTeamService.getOne(doctorDoctorTeam);
		if (doctorDoctorTeamOneMap!=null && !doctorDoctorTeamOneMap.isEmpty()) {
			boolean isManager = (boolean) doctorDoctorTeamOneMap.get("isManager");
			/*用户在团队是团队长*/ 
			if (isManager) {
				Page<?> page = PageHelper.startPage(organizationOrganizationTeamRole.getPage(),organizationOrganizationTeamRole.getPageSize());
				/*查询团队长团队权限=团队所有权限*/ 
				List<Map<String, Object>> teamPermissionList = organizationOrganizationTeamRoleService.getOrganizationOrganizationTeamManagerPermissions(organizationOrganizationTeamRole);
				if (teamPermissionList != null && !teamPermissionList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamPermissionList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			} else {/*用户在团队不是团队长*/ 
				Page<?> page = PageHelper.startPage(organizationOrganizationTeamRole.getPage(),organizationOrganizationTeamRole.getPageSize());
				/*用户在团队拥有的权限*/ 
				List<Map<String, Object>> teamPermissionList = organizationOrganizationTeamRoleService.getOrganizationOrganizationTeamPermissions(organizationOrganizationTeamRole);
				if (teamPermissionList != null && !teamPermissionList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamPermissionList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationOrganizationTeamRole
	 * @param result
	 * @param token
	 * @param organizationTeamId
	 * @return
	 * @description: 查询机构用户团队菜单列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-team:organization-organization-team-role:get-organization-user-team-menus" })
	@GetMapping(value = { "/organizationTeam/organization/team/menus" })
	public JsonApi getOrganizationUserTeamMenus(
			@Validated({OrganizationOrganizationTeamRole.TeamMenus.class }) OrganizationOrganizationTeamRole organizationOrganizationTeamRole,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
		organizationOrganizationTeamRole.setOrganizationTeamId(organizationTeamId);
		/*获取缓存信息*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationOrganizationTeamRole.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		/*查询用户在团队是否团队长*/ 
		OrganizationUserTeam doctorDoctorTeam = new OrganizationUserTeam();
		doctorDoctorTeam.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		doctorDoctorTeam.setOrganizationTeamId(organizationTeamId);
		Map<String, Object> doctorDoctorTeamOneMap = organizationUserTeamService.getOne(doctorDoctorTeam);
		if (doctorDoctorTeamOneMap!=null && !doctorDoctorTeamOneMap.isEmpty()) {
			boolean isManager = (boolean) doctorDoctorTeamOneMap.get("isManager");
			/*用户在团队是团队长*/ 
			if (isManager) {
				Page<?> page = PageHelper.startPage(organizationOrganizationTeamRole.getPage(),organizationOrganizationTeamRole.getPageSize());
				/*团队所有菜单*/ 
				List<Map<String, Object>> teamMenuList = organizationOrganizationTeamRoleService.getOrganizationOrganizationTeamManagerMenus(organizationOrganizationTeamRole);
				if (teamMenuList != null && !teamMenuList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamMenuList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			} else {/*用户在团队不是团队长*/ 
				Page<?> page = PageHelper.startPage(organizationOrganizationTeamRole.getPage(),organizationOrganizationTeamRole.getPageSize());
				/* 查询用户在团队拥有的菜单*/
				List<Map<String, Object>> teamMenuList = organizationOrganizationTeamRoleService.getOrganizationOrganizationTeamMenus(organizationOrganizationTeamRole);
				if (teamMenuList != null && !teamMenuList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamMenuList));
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
	 * @param organizationOrganizationTeamRole
	 * @param result
	 * @param token
	 * @param organizationTeamId
	 * @return
	 * @description: 查询机构用户团队操作列表
	 */
	@RequiresAuthentication(authc = true, value = { "web-module-team:organization-organization-team-role:get-organization-user-team-operations" })
	@GetMapping(value = { "/organizationTeam/organization/team/operations" })
	public JsonApi getOrganizationOrganizationTeamOperations(
			@Validated({
					OrganizationOrganizationTeamRole.TeamOperations.class }) OrganizationOrganizationTeamRole organizationOrganizationTeamRole,
			BindingResult result, @RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_TEAM_ID) Integer organizationTeamId) {
		organizationOrganizationTeamRole.setOrganizationTeamId(organizationTeamId);
		/*获取缓存信息*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_ORGANIZATION_USER, token);
		organizationOrganizationTeamRole.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		/*查询用户在团队是否团队长*/ 
		OrganizationUserTeam doctorDoctorTeam = new OrganizationUserTeam();
		doctorDoctorTeam.setOrganizationUserId(Integer.parseInt(session.get(Map.class).get("id").toString()));
		doctorDoctorTeam.setOrganizationTeamId(organizationTeamId);
		Map<String, Object> doctorDoctorTeamOneMap = organizationUserTeamService.getOne(doctorDoctorTeam);
		if (MapUtils.isNotEmpty(doctorDoctorTeamOneMap)) {
			boolean isManager = (boolean) doctorDoctorTeamOneMap.get("isManager");
			/* 是团队长*/
			if (isManager) {
				Page<?> page = PageHelper.startPage(organizationOrganizationTeamRole.getPage(),organizationOrganizationTeamRole.getPageSize());
				/*查询团队所有操作*/ 
				List<Map<String, Object>> teamOperationList = organizationOrganizationTeamRoleService.getOrganizationOrganizationTeamManagerOperation(organizationOrganizationTeamRole);
				if (teamOperationList != null && !teamOperationList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamOperationList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			} else {/*不是团队长*/ 
				Page<?> page = PageHelper.startPage(organizationOrganizationTeamRole.getPage(),organizationOrganizationTeamRole.getPageSize());
				/*查询用户在团队的操作*/ 
				List<Map<String, Object>> teamOperationList = organizationOrganizationTeamRoleService.getOrganizationOrganizationTeamOperation(organizationOrganizationTeamRole);
				if (teamOperationList != null && !teamOperationList.isEmpty()) {
					return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamOperationList));
				}
				return new JsonApi(ApiCodeEnum.NOT_FOUND);
			}
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
