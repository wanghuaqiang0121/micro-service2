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
import org.web.module.base.domain.permission.organization.OperationalRole;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.permission.organization.OperationalRoleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月31日
 * @description: 机构角色表
 */
@RestController
public class OperationalRoleController {

	@Resource
	private OperationalRoleService operationalRoleService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增角色
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/operational/role" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OperationalRole operationalRole, BindingResult result) {
		/* 判断是否重复 */
		Map<String, Object> operationalRoleMap = operationalRoleService.getRepeat(operationalRole);
		if (operationalRoleMap != null && !operationalRoleMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("operational.role.code.is.conflict"));
		}
		/* 设置时间 */
		operationalRole.setCreateDate(new Date());
		if (operationalRoleService.insert(operationalRole) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param operationalRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改角色
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PutMapping(value = { "/operational/role/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody OperationalRole operationalRole, BindingResult result) {
		operationalRole.setId(id);
		/* 判断是否存在 */
		Map<String, Object> operationalRoleOneMap = operationalRoleService.getOne(operationalRole);
		if (operationalRoleOneMap == null || operationalRoleOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 判断是否重复 */
		if (null != operationalRole.getCode()) {
			Map<String, Object> operationalRoleMap = operationalRoleService.getRepeat(operationalRole);
			if (operationalRoleMap != null && !operationalRoleMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("operational.role.code.is.conflict"));
			}
		}
		if (operationalRoleService.update(operationalRole) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param operationalRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 角色详情
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/role/{id}" })
	public JsonApi getOne(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) OperationalRole operationalRole, BindingResult result) {
		operationalRole.setId(id);
		Map<String, Object> operationalRoleMap = operationalRoleService.getOne(operationalRole);
		if (operationalRoleMap != null && !operationalRoleMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, operationalRoleMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalRole
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 角色列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/roles" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OperationalRole operationalRole, BindingResult result) {
		Page<?> page = PageHelper.startPage(operationalRole.getPage(), operationalRole.getPageSize());
		List<Map<String, Object>> operationalRoleList = operationalRoleService.getList(operationalRole);
		if (operationalRoleList != null && !operationalRoleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), operationalRoleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

}
