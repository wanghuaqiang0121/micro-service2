package org.web.module.base.controller.permission.organization;

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
import org.web.module.base.domain.permission.organization.OperationalPermissionMenu;
import org.web.module.base.service.permission.organization.OperationalPermissionMenuService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月31日
 * @description: 权限菜单关联表
 */
@RestController
public class OperationalPermissionMenuRelationController {
	@Resource
	private OperationalPermissionMenuService operationalPermissionMenuService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermissionMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增权限菜单关联
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/operational/permission/menu" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) OperationalPermissionMenu operationalPermissionMenu, BindingResult result) {
		/* 检查数据是否存在 */
		Map<String, Object> operationalPermissionMenuMap = operationalPermissionMenuService.getRepeat(operationalPermissionMenu);
		if (operationalPermissionMenuMap != null && !operationalPermissionMenuMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		/* 新增 */
		if (operationalPermissionMenuService.insert(operationalPermissionMenu) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermissionId
	 * @param operationalMenuId
	 * @param operationalPermissionMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/operational/permission/{operationalPermissionId}/menu/{operationalMenuId}" })
	public JsonApi delete(@PathVariable("operationalPermissionId") Integer operationalPermissionId, @PathVariable("operationalMenuId") Integer operationalMenuId,
			@Validated({ BaseEntity.Delete.class }) OperationalPermissionMenu operationalPermissionMenu, BindingResult result) {
		operationalPermissionMenu.setOperationalPermissionId(operationalPermissionId);
		operationalPermissionMenu.setOperationalMenuId(operationalMenuId);
		if (operationalPermissionMenuService.delete(operationalPermissionMenu) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermissionMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询该权限拥有和未拥有的菜单
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/permission/menus" })
	public JsonApi getPermissionMenuIsChoose(@Validated({ OperationalPermissionMenu.GetPermissionMenuIsChoose.class }) OperationalPermissionMenu operationalPermissionMenu, BindingResult result) {
		Page<?> page = PageHelper.startPage(operationalPermissionMenu.getPage(), operationalPermissionMenu.getPageSize());
		List<Map<String, Object>> operationalPermissionMenuList = operationalPermissionMenuService.getPermissionMenuIsChoose(operationalPermissionMenu);
		if (operationalPermissionMenuList != null && !operationalPermissionMenuList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), operationalPermissionMenuList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
