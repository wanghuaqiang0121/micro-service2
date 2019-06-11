package org.web.module.base.controller.permission.organization;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.permission.organization.OperationalPermission;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.permission.organization.OperationalPermissionService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月31日
 * @description: 机构权限表
 */
@RestController
public class OperationalPermissionController {

	@Autowired
	private OperationalPermissionService operationalPermissionService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增机构权限
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/operational/permission" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OperationalPermission operationalPermission, BindingResult result) {
		/* 判断是否重复 */
		Map<String, Object> operationalPermissionMap = operationalPermissionService.getRepeat(operationalPermission);
		if (operationalPermissionMap != null && !operationalPermissionMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("operational.permission.code.is.conflict"));
		}
		/* 设置时间 */
		operationalPermission.setCreateDate(new Date());
		if (operationalPermissionService.insert(operationalPermission) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param operationalPermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改机构权限
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PutMapping(value = { "/operational/permission/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody OperationalPermission operationalPermission, BindingResult result) {
		operationalPermission.setId(id);
		/* 判断是否存在 */
		Map<String, Object> operationalPermissionOneMap = operationalPermissionService.getOne(operationalPermission);
		if (operationalPermissionOneMap == null || operationalPermissionOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 判断是否重复 */
		if (null != operationalPermission.getCode()) {
			Map<String, Object> operationalPermissionMap = operationalPermissionService.getRepeat(operationalPermission);
			if (operationalPermissionMap != null && !operationalPermissionMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("operational.permission.code.is.conflict"));
			}
		}
		if (operationalPermissionService.update(operationalPermission) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param operationalPermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构权限详情
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/permission/{id}" })
	public JsonApi getOne(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) OperationalPermission operationalPermission, BindingResult result) {
		operationalPermission.setId(id);
		Map<String, Object> operationalPermissionMap = operationalPermissionService.getOne(operationalPermission);
		if (operationalPermissionMap != null && !operationalPermissionMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, operationalPermissionMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermission
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构权限列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/permissions" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OperationalPermission operationalPermission, BindingResult result) {
		Page<?> page = PageHelper.startPage(operationalPermission.getPage(), operationalPermission.getPageSize());
		List<Map<String, Object>> operationalPermissionList = operationalPermissionService.getList(operationalPermission);
		if (operationalPermissionList != null && !operationalPermissionList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), operationalPermissionList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
