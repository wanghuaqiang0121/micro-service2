package org.web.module.team.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.team.domain.OrganizationTeamRole;
import org.web.module.team.service.OrganizationTeamRoleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年10月11日
 * @description: 团队角色
 */
@RestController
public class OrganizationTeamRoleController {

	@Resource
	private OrganizationTeamRoleService organizationTeamRoleService;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年10月11日
	 * @param organizationTeamRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 获取团队角色列表
	 */
	@RequiresAuthentication(value = { "web-module-team:organization-team-role:get-list" }, level = Level.OPERATION)
	@GetMapping(value = { "/organization/team/roles" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OrganizationTeamRole organizationTeamRole, BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationTeamRole.getPage(), organizationTeamRole.getPageSize());
		List<Map<String, Object>> organizationTeamRoleList = organizationTeamRoleService.getList(organizationTeamRole);
		if (organizationTeamRoleList != null && !organizationTeamRoleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationTeamRoleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
