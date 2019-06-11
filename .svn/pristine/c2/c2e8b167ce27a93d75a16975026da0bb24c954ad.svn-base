package org.web.module.base.controller.permission.organization;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.permission.organization.OperationalMenu;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.permission.organization.OperationalMenuService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月31日
 * @description: 菜单表
 */
@RestController
public class OperationalMenuController {

	@Resource
	private OperationalMenuService operationalMenuService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增机构菜单
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/operational/menu" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OperationalMenu operationalMenu, BindingResult result) {
		/* 判断是否重复 */
		Map<String, Object> operationalMenuMap = operationalMenuService.getRepeat(operationalMenu);
		if (operationalMenuMap != null && !operationalMenuMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("operational.menu.code.is.conflict"));
		}
		/* 设置时间 */
		operationalMenu.setCreateDate(new Date());
		if (operationalMenuService.insert(operationalMenu) > 0) {
			Map<String, Object> menuMap = new HashMap<>();
			menuMap.put("id", operationalMenu.getId());
			return new JsonApi(ApiCodeEnum.OK, menuMap);
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param operationalMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改机构菜单
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PutMapping(value = { "/operational/menu/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody OperationalMenu operationalMenu, BindingResult result) {
		operationalMenu.setId(id);
		// 判断是否存在
		Map<String, Object> operationalMenuOneMap = operationalMenuService.getOne(operationalMenu);
		if (operationalMenuOneMap == null || operationalMenuOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 判断是否重复 */
		if (null != operationalMenu.getCode()) {
			Map<String, Object> operationalMenuMap = operationalMenuService.getRepeat(operationalMenu);
			if (operationalMenuMap != null && !operationalMenuMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("operational.menu.code.is.conflict"));
			}
		}
		if (operationalMenuService.update(operationalMenu) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}

		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param operationalMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构菜单详情
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/menu/{id}" })
	public JsonApi getOne(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) OperationalMenu operationalMenu, BindingResult result) {
		operationalMenu.setId(id);
		Map<String, Object> operationalOperationMap = operationalMenuService.getOne(operationalMenu);
		if (operationalOperationMap != null && !operationalOperationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, operationalOperationMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);

	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalMenu
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构菜单列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/menus" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OperationalMenu operationalMenu, BindingResult result) {

		Page<?> page = PageHelper.startPage(operationalMenu.getPage(), operationalMenu.getPageSize());
		List<Map<String, Object>> operationalMenuList = operationalMenuService.getList(operationalMenu);
		if (operationalMenuList != null && !operationalMenuList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), operationalMenuList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);

	}
}
