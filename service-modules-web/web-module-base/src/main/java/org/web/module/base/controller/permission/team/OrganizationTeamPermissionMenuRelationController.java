package org.web.module.base.controller.permission.team;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity.Delete;
import org.service.core.entity.BaseEntity.Insert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.permission.team.OrganizationTeamPermissionMenu;
import org.web.module.base.domain.permission.team.OrganizationTeamPermissionMenu.HaveAndNotHaveMenu;
import org.web.module.base.service.permission.team.OrganizationTeamPermissionMenuService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author <font color="red"><b>Wang.HuaQiang</b></font>
 * @Date 2018年5月16日
 * @Version
 * @Description 团队权限菜单关联
 */
@RestController
public class OrganizationTeamPermissionMenuRelationController {

	@Resource
	private OrganizationTeamPermissionMenuService organizationTeamPermissionMenuService;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamPermissionMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增团队权限菜单关联
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/organization/team/permission/menu" })
	public JsonApi insert(@Validated({ Insert.class }) @RequestBody OrganizationTeamPermissionMenu organizationTeamPermissionMenu, BindingResult result) {
		/* 判断数据是否重复 */
		Map<String, Object> teamPermissionMenuRepeatMap = organizationTeamPermissionMenuService.getRepeat(organizationTeamPermissionMenu);
		if (teamPermissionMenuRepeatMap != null && !teamPermissionMenuRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		/* 新增团队权限菜单关联 */
		if (organizationTeamPermissionMenuService.insert(organizationTeamPermissionMenu) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamMenuId
	 * @param organizationTeamPermissionId
	 * @param organizationTeamPermissionMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除团队权限菜单关联
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/organization/team/{organizationTeamPermissionId}/permission/menu/{organizationTeamMenuId}" })
	public JsonApi delete(@PathVariable("organizationTeamMenuId") Integer organizationTeamMenuId, @PathVariable("organizationTeamPermissionId") Integer organizationTeamPermissionId,
			@Validated({ Delete.class }) OrganizationTeamPermissionMenu organizationTeamPermissionMenu, BindingResult result) {
		organizationTeamPermissionMenu.setOrganizationTeamPermissionId(organizationTeamPermissionId);
		organizationTeamPermissionMenu.setOrganizationTeamMenuId(organizationTeamMenuId);
		/* 删除团队权限菜单关联 */
		if (organizationTeamPermissionMenuService.delete(organizationTeamPermissionMenu) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param permissionId
	 * @param organizationTeamPermissionMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 团队权限（拥有未拥有）的菜单
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/permission/menu/{permissionId}" })
	public JsonApi getListByPermission(@PathVariable("permissionId") Integer permissionId, @Validated({ HaveAndNotHaveMenu.class }) OrganizationTeamPermissionMenu organizationTeamPermissionMenu,
			BindingResult result) {
		organizationTeamPermissionMenu.setOrganizationTeamPermissionId(permissionId);
		Page<?> page = PageHelper.startPage(organizationTeamPermissionMenu.getPage(), organizationTeamPermissionMenu.getPageSize());
		List<Map<String, Object>> organizationTeamPermissionMenuList = organizationTeamPermissionMenuService.getTeamPermissionHaveAndNotHaveMenus(organizationTeamPermissionMenu);
		if (organizationTeamPermissionMenuList != null && !organizationTeamPermissionMenuList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationTeamPermissionMenuList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
