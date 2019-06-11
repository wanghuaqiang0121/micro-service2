package org.web.module.base.controller.permission.team;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.permission.team.OrganizationTeamPermission;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.permission.team.OrganizationTeamPermissionService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 团队权限
 */
@RestController
public class OrganizationTeamPermissionController {

	@Resource
	private OrganizationTeamPermissionService organizationTeamPermissionService;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamPermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增团队权限
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/organization/team/permission" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationTeamPermission organizationTeamPermission, BindingResult result) {
		/* 判断数据是否重复 */
		Map<String, Object> teamPermissionRepeatMap = organizationTeamPermissionService.getRepeat(organizationTeamPermission);
		if (teamPermissionRepeatMap != null && !teamPermissionRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("team.permission.code.is.conflict"));
		}
		organizationTeamPermission.setCreateDate(new Date());
		organizationTeamPermission.setIsUsed(true);
		/* 新增团队权限 */
		if (organizationTeamPermissionService.insert(organizationTeamPermission) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationTeamPermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查看团队权限详情
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/permission/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) OrganizationTeamPermission organizationTeamPermission, BindingResult result) {
		organizationTeamPermission.setId(id);
		/* 查看团队权限详情 */
		Map<String, Object> organizationTeamPermissionMap = organizationTeamPermissionService.getOne(organizationTeamPermission);
		if (organizationTeamPermissionMap != null && !organizationTeamPermissionMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, organizationTeamPermissionMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationTeamPermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除团队权限
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/organization/team/permission/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id, @Validated({ BaseEntity.Delete.class }) OrganizationTeamPermission organizationTeamPermission, BindingResult result) {
		organizationTeamPermission.setId(id);
		/* 删除团队权限 */
		if (organizationTeamPermissionService.delete(organizationTeamPermission) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamPermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查看团队权限列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/permissions" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OrganizationTeamPermission organizationTeamPermission, BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationTeamPermission.getPage(), organizationTeamPermission.getPageSize());
		List<Map<String, Object>> organizationTeamPermissionList = organizationTeamPermissionService.getList(organizationTeamPermission);
		if (organizationTeamPermissionList != null && !organizationTeamPermissionList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationTeamPermissionList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationTeamPermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改团队权限
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PutMapping(value = { "/organization/team/permission/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody OrganizationTeamPermission organizationTeamPermission, BindingResult result) {
		/* 修改的数据是否存在 */
		organizationTeamPermission.setId(id);
		Map<String, Object> organizationTeamPermissionMap = organizationTeamPermissionService.getOne(organizationTeamPermission);
		if (organizationTeamPermissionMap == null || organizationTeamPermissionMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 如果修改code 唯一标识 */
		if (StringUtils.isNotBlank(organizationTeamPermission.getCode())) {
			Map<String, Object> teamPermissionRepeatMap = organizationTeamPermissionService.getRepeat(organizationTeamPermission);
			if (teamPermissionRepeatMap != null && !teamPermissionRepeatMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("team.permission.code.is.conflict"));
			}
		}
		/* 修改团队权限 */
		if (organizationTeamPermissionService.update(organizationTeamPermission) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
