package org.web.module.base.controller.permission.team;

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
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.permission.team.OrganizationTeamRolePermission;
import org.web.module.base.service.permission.team.OrganizationTeamRolePermissionService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 团队角色权限关联表
 */
@RestController
public class OrganizationTeamRolePermissionRelationController {

	@Resource
	private OrganizationTeamRolePermissionService organizationTeamRolePermissionService;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationTeamRolePermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 添加团队角色权限关联
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/organization/team/role/permission" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationTeamRolePermission organizationTeamRolePermission, BindingResult result) {
		/* 判断数据是否重复 */
		Map<String, Object> organizationTeamRolePermissionRepeatMap = organizationTeamRolePermissionService.getRepeat(organizationTeamRolePermission);
		if (organizationTeamRolePermissionRepeatMap != null && !organizationTeamRolePermissionRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		/* 新增团队权限操作关联 */
		if (organizationTeamRolePermissionService.insert(organizationTeamRolePermission) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationTeamRoleId
	 * @param organizationTeamPermissionId
	 * @param organizationTeamRolePermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除团队角色关联
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/organization/team/{organizationTeamRoleId}/role/permission/{organizationTeamPermissionId}" })
	public JsonApi delete(@PathVariable("organizationTeamRoleId") Integer organizationTeamRoleId, @PathVariable("organizationTeamPermissionId") Integer organizationTeamPermissionId,
			@Validated({ BaseEntity.Delete.class }) OrganizationTeamRolePermission organizationTeamRolePermission, BindingResult result) {
		organizationTeamRolePermission.setOrganizationTeamRoleId(organizationTeamRoleId);
		organizationTeamRolePermission.setOrganizationTeamPermissionId(organizationTeamPermissionId);
		/* 删除团队权限操作关联 */
		if (organizationTeamRolePermissionService.delete(organizationTeamRolePermission) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param roleId
	 * @param organizationTeamRolePermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 团队角色（拥有未拥有）的权限
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/role/permissions/{roleId}" })
	public JsonApi getListByRole(@PathVariable("roleId") Integer roleId,
			@Validated({ OrganizationTeamRolePermission.HaveAndNotHavePermission.class }) OrganizationTeamRolePermission organizationTeamRolePermission, BindingResult result) {
		organizationTeamRolePermission.setOrganizationTeamRoleId(roleId);
		Page<?> page = PageHelper.startPage(organizationTeamRolePermission.getPage(), organizationTeamRolePermission.getPageSize());
		/* 团队权限（拥有未拥有）的权限 */
		List<Map<String, Object>> organizationTeamRolePermissionList = organizationTeamRolePermissionService.getTeamRoleHaveAndNotHavePermissions(organizationTeamRolePermission);
		if (organizationTeamRolePermissionList != null && !organizationTeamRolePermissionList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationTeamRolePermissionList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
