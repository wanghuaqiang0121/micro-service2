package org.web.module.base.controller.permission.organization;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.permission.organization.SystemModule;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.permission.organization.SystemModuleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月31日
 * @description: 系统模块表
 */
@RestController
public class SystemModuleController {

	@Resource
	private SystemModuleService systemModuleService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param systemModule
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增系统模块
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/system/module" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody SystemModule systemModule, BindingResult result) {
		/* 判断是否重复 */
		Map<String, Object> systemModuleMap = systemModuleService.getRepeat(systemModule);
		if (systemModuleMap != null && !systemModuleMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("system.module.code.is.conflict"));
		}
		/* 设置时间 */
		systemModule.setCreateDate(new Date());
		if (systemModuleService.insert(systemModule) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param systemModule
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改系统模块
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PutMapping(value = { "/system/module/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody SystemModule systemModule, BindingResult result) {
		systemModule.setId(id);
		/* 判断是否存在 */
		Map<String, Object> systemModuleOneMap = systemModuleService.getOne(systemModule);
		if (systemModuleOneMap == null || systemModuleOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 判断是否重复 */
		if (null != systemModule.getCode()) {
			Map<String, Object> systemModuleMap = systemModuleService.getRepeat(systemModule);
			if (systemModuleMap != null && !systemModuleMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("system.module.code.is.conflict"));
			}
		}
		if (systemModuleService.update(systemModule) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param systemModule
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 系统模块详情
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/system/module/{id}" })
	public JsonApi getOne(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) SystemModule systemModule, BindingResult result) {
		systemModule.setId(id);
		Map<String, Object> systemModuleMap = systemModuleService.getOne(systemModule);
		if (systemModuleMap != null && !systemModuleMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, systemModuleMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param systemModule
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询系统模块列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/system/modules" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) SystemModule systemModule, BindingResult result) {
		Page<?> page = PageHelper.startPage(systemModule.getPage(), systemModule.getPageSize());
		List<Map<String, Object>> systemModuleList = systemModuleService.getList(systemModule);
		if (systemModuleList != null && !systemModuleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), systemModuleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
