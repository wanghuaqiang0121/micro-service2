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
import org.web.module.base.domain.permission.organization.ModuleOperationalRole;
import org.web.module.base.service.permission.organization.ModuleOperationalRoleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月15日
 * @description: ModuleOperationalRoleRelationController
 */
@RestController
public class ModuleOperationalRoleRelationController {

	@Resource
	private ModuleOperationalRoleService moduleOperationalRoleService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param moduleOperationalRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增模块角色关联表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/module/operational/role" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) ModuleOperationalRole moduleOperationalRole, BindingResult result) {
		/* 检查数据重复 */
		Map<String, Object> moduleOperationalRoleMap = moduleOperationalRoleService.getRepeat(moduleOperationalRole);
		if (moduleOperationalRoleMap != null && !moduleOperationalRoleMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		/* 新增 */
		if (moduleOperationalRoleService.insert(moduleOperationalRole) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param systemModuleId
	 * @param operationalRoleId
	 * @param moduleOperationalRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除模块角色关联
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/module/{systemModuleId}/operational/role/{operationalRoleId}" })
	public JsonApi delete(@PathVariable("systemModuleId") Integer systemModuleId, @PathVariable("operationalRoleId") Integer operationalRoleId,
			@Validated({ BaseEntity.Delete.class }) ModuleOperationalRole moduleOperationalRole, BindingResult result) {
		moduleOperationalRole.setSystemModuleId(systemModuleId);
		moduleOperationalRole.setOperationalRoleId(operationalRoleId);
		if (moduleOperationalRoleService.delete(moduleOperationalRole) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param moduleOperationalRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询该模块拥有和未拥有的角色
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/module/operational/roles" })
	public JsonApi getModuleRoleIsChoose(@Validated({ ModuleOperationalRole.GetModuleRoleIsChoose.class }) ModuleOperationalRole moduleOperationalRole, BindingResult result) {
		Page<?> page = PageHelper.startPage(moduleOperationalRole.getPage(), moduleOperationalRole.getPageSize());
		List<Map<String, Object>> moduleOperationalRoleList = moduleOperationalRoleService.getModuleRoleIsChoose(moduleOperationalRole);
		if (moduleOperationalRoleList != null && !moduleOperationalRoleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), moduleOperationalRoleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param moduleOperationalRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询模块角色关联表列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/module/operation/roles" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) ModuleOperationalRole moduleOperationalRole, BindingResult result) {
		Page<?> page = PageHelper.startPage(moduleOperationalRole.getPage(), moduleOperationalRole.getPageSize());
		List<Map<String, Object>> moduleOperationalRoleList = moduleOperationalRoleService.getList(moduleOperationalRole);
		if (moduleOperationalRoleList != null && !moduleOperationalRoleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), moduleOperationalRoleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
