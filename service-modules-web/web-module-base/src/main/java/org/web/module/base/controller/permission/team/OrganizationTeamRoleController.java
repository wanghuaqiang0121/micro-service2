package org.web.module.base.controller.permission.team;

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
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.permission.team.OrganizationTeamRole;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.permission.team.OrganizationTeamRoleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 团队角色
 */
@RestController
public class OrganizationTeamRoleController {

	@Resource
	private OrganizationTeamRoleService organizationTeamRoleService;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationTeamRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增团队角色
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/organization/team/role" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationTeamRole organizationTeamRole, BindingResult result) {
		/* 判断数据是否重复 */
		Map<String, Object> teamRoleRepeatMap = organizationTeamRoleService.getRepeat(organizationTeamRole);
		if (teamRoleRepeatMap != null && !teamRoleRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("team.role.code.is.conflict"));
		}
		organizationTeamRole.setCreateDate(new Date());
		organizationTeamRole.setIsUsed(true);
		/* 新增团队角色 */
		if (organizationTeamRoleService.insert(organizationTeamRole) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param id
	 * @param organizationTeamRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查看团队角色详情
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/role/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) OrganizationTeamRole organizationTeamRole, BindingResult result) {
		organizationTeamRole.setId(id);
		/* 查看团队角色详情 */
		Map<String, Object> organizationTeamRoleMap = organizationTeamRoleService.getOne(organizationTeamRole);
		if (organizationTeamRoleMap != null && !organizationTeamRoleMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, organizationTeamRoleMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param id
	 * @param organizationTeamRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除团队角色
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/organization/team/role/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id, @Validated({ BaseEntity.Delete.class }) OrganizationTeamRole organizationTeamRole, BindingResult result) {
		organizationTeamRole.setId(id);
		/* 删除团队角色 */
		if (organizationTeamRoleService.delete(organizationTeamRole) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organizationTeamRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查看团队角色列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/roles" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OrganizationTeamRole organizationTeamRole, BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationTeamRole.getPage(), organizationTeamRole.getPageSize());
		/* 查看团队角色列表 */
		List<Map<String, Object>> teamRolesResultMap = organizationTeamRoleService.getList(organizationTeamRole);
		if (teamRolesResultMap != null && !teamRolesResultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamRolesResultMap));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param id
	 * @param organizationTeamRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改团队角色
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PutMapping(value = { "/organization/team/role/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody OrganizationTeamRole organizationTeamRole, BindingResult result) {
		/* 修改的数据是否存在 */
		organizationTeamRole.setId(id);
		Map<String, Object> organizationTeamRoleMap = organizationTeamRoleService.getOne(organizationTeamRole);
		if (organizationTeamRoleMap == null || organizationTeamRoleMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if (null != organizationTeamRole.getCode()) {
			Map<String, Object> teamRoleRepeatMap = organizationTeamRoleService.getRepeat(organizationTeamRole);
			if (teamRoleRepeatMap != null && !teamRoleRepeatMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("team.role.code.is.conflict"));
			}
		}
		/* 修改团队角色 */
		if (organizationTeamRoleService.update(organizationTeamRole) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
