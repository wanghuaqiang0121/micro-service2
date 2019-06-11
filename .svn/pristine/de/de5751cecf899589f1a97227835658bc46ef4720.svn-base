package org.web.module.base.controller.permission.team;

import java.util.Date;
import java.util.HashMap;
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
import org.web.module.base.domain.permission.team.OrganizationTeamMenu;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.permission.team.OrganizationTeamMenuService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 团队菜单
 */
@RestController
public class OrganizationTeamMenuController {

	@Resource
	private OrganizationTeamMenuService organizationTeamMenuService;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增团队菜单
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/organization/team/menu" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationTeamMenu organizationTeamMenu, BindingResult result) {
		organizationTeamMenu.setCreateDate(new Date());
		organizationTeamMenu.setIsUsed(true);
		/* 判断数据是否重复 */
		Map<String, Object> teamMenuRepeatMap = organizationTeamMenuService.getRepeat(organizationTeamMenu);
		if (teamMenuRepeatMap != null && !teamMenuRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("team.menu.code.is.conflict"));
		}
		/* 新增团队菜单 */
		if (organizationTeamMenuService.insert(organizationTeamMenu) > 0) {
			Map<String, Object> menuMap = new HashMap<String, Object>();
			menuMap.put("id", organizationTeamMenu.getId());
			return new JsonApi(ApiCodeEnum.OK, menuMap);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationTeamMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查看团队菜单详情
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/menu/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) OrganizationTeamMenu organizationTeamMenu, BindingResult result) {
		organizationTeamMenu.setId(id);
		/* 查看团队菜单详情 */
		Map<String, Object> teamMenuOneResultMap = organizationTeamMenuService.getOne(organizationTeamMenu);
		if (teamMenuOneResultMap != null && !teamMenuOneResultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, teamMenuOneResultMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationTeamMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除团队菜单
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/organization/team/menu/{id}" })
	public JsonApi teamMenuDelete(@PathVariable("id") Integer id, @Validated({ BaseEntity.Delete.class }) OrganizationTeamMenu organizationTeamMenu, BindingResult result) {
		organizationTeamMenu.setId(id);
		/* 删除团队菜单 */
		if (organizationTeamMenuService.delete(organizationTeamMenu) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查看团队菜单列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/menus" })
	public JsonApi teamMenuList(@Validated({ BaseEntity.SelectAll.class }) OrganizationTeamMenu organizationTeamMenu, BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationTeamMenu.getPage(), organizationTeamMenu.getPageSize());
		/* 查看团队菜单列表 */
		List<Map<String, Object>> teamOperationsResultList = organizationTeamMenuService.getList(organizationTeamMenu);
		if (teamOperationsResultList != null && !teamOperationsResultList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamOperationsResultList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationTeamMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改团队菜单
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PutMapping(value = { "/organization/team/menu/{id}" })
	public JsonApi teamMenuUpdate(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody OrganizationTeamMenu organizationTeamMenu, BindingResult result) {
		/* 修改的数据是否存在 */
		organizationTeamMenu.setId(id);
		Map<String, Object> teamOperationOneMap = organizationTeamMenuService.getOne(organizationTeamMenu);
		if (teamOperationOneMap == null || teamOperationOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 如果修改code唯一标识 */
		if (StringUtils.isNotEmpty(organizationTeamMenu.getCode())) {
			Map<String, Object> teamOperationRepeatOneMap = organizationTeamMenuService.getRepeat(organizationTeamMenu);
			if (teamOperationRepeatOneMap != null && !teamOperationRepeatOneMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("team.menu.code.is.conflict"));
			}
		}
		/* 修改团队菜单 */
		if (organizationTeamMenuService.update(organizationTeamMenu) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
