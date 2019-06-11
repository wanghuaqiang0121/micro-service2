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
import org.web.module.base.domain.permission.team.OrganizationTeamPermissionOperation;
import org.web.module.base.service.permission.team.OrganizationTeamPermissionOperationService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 团队权限操作关联
 */
@RestController
public class OrganizationTeamPermissionOperationRelationController {

	@Resource
	private OrganizationTeamPermissionOperationService organizationTeamPermissionOperationService;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamPermissionOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增团队权限操作关联
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/organization/team/permission/operation" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationTeamPermissionOperation organizationTeamPermissionOperation, BindingResult result) {
		/* 判断数据是否重复 */
		Map<String, Object> teamPermissionOperationRepeatMap = organizationTeamPermissionOperationService.getRepeat(organizationTeamPermissionOperation);
		if (teamPermissionOperationRepeatMap != null && !teamPermissionOperationRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		/* 新增团队权限操作关联 */
		if (organizationTeamPermissionOperationService.insert(organizationTeamPermissionOperation) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamOperationId
	 * @param organizationTeamPermissionId
	 * @param organizationTeamPermissionOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除团队权限操作关联
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/organization/team/{organizationTeamPermissionId}/permission/operation/{organizationTeamOperationId}" })
	public JsonApi delete(@PathVariable("organizationTeamOperationId") Integer organizationTeamOperationId, @PathVariable("organizationTeamPermissionId") Integer organizationTeamPermissionId,
			@Validated({ BaseEntity.Delete.class }) OrganizationTeamPermissionOperation organizationTeamPermissionOperation, BindingResult result) {
		organizationTeamPermissionOperation.setOrganizationTeamOperationId(organizationTeamOperationId);
		organizationTeamPermissionOperation.setOrganizationTeamPermissionId(organizationTeamPermissionId);
		/* 删除团队权限操作关联 */
		if (organizationTeamPermissionOperationService.delete(organizationTeamPermissionOperation) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param permissionId
	 * @param organizationTeamPermissionOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 团队权限（拥有未拥有）的操作
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/permission/operations/{permissionId}" })
	public JsonApi getListByPermission(@PathVariable("permissionId") Integer permissionId,
			@Validated({ OrganizationTeamPermissionOperation.HaveAndNotHaveOperation.class }) OrganizationTeamPermissionOperation organizationTeamPermissionOperation, BindingResult result) {
		organizationTeamPermissionOperation.setOrganizationTeamPermissionId(permissionId);
		Page<?> page = PageHelper.startPage(organizationTeamPermissionOperation.getPage(), organizationTeamPermissionOperation.getPageSize());
		/* 团队权限（拥有未拥有）的操作 */
		List<Map<String, Object>> organizationTeamPermissionOperationList = organizationTeamPermissionOperationService.getTeamPermissionHaveAndNotHaveOperations(organizationTeamPermissionOperation);
		if (organizationTeamPermissionOperationList != null && !organizationTeamPermissionOperationList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationTeamPermissionOperationList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
