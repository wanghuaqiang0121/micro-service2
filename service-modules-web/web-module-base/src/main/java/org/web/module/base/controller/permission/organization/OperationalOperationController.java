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
import org.web.module.base.domain.permission.organization.OperationalOperation;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.permission.organization.OperationalOperationService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月31日
 * @description: 机构操作表
 */
@RestController
public class OperationalOperationController {

	@Resource
	private OperationalOperationService operationalOperationService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增机构操作
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/operational/operation" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OperationalOperation operationalOperation, BindingResult result) {
		/* 判断是否重复 */
		Map<String, Object> operationalOperationMap = operationalOperationService.getRepeat(operationalOperation);
		if (operationalOperationMap != null && !operationalOperationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("operational.operation.code.is.conflict"));
		}
		/* 设置时间 */
		operationalOperation.setCreateDate(new Date());
		if (operationalOperationService.insert(operationalOperation) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param operationalOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改机构操作
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PutMapping(value = { "/operational/operation/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody OperationalOperation operationalOperation, BindingResult result) {
		operationalOperation.setId(id);
		/* 判断是否存在 */
		Map<String, Object> operationalOperationOneMap = operationalOperationService.getOne(operationalOperation);
		if (operationalOperationOneMap == null || operationalOperationOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 判断是否重复 */
		if (null != operationalOperation.getCode()) {
			Map<String, Object> operationalOperationMap = operationalOperationService.getRepeat(operationalOperation);
			if (operationalOperationMap != null && !operationalOperationMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("operational.operation.code.is.conflict"));
			}
		}
		if (operationalOperationService.update(operationalOperation) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);

	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param operationalOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构操作详情
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/operation/{id}" })
	public JsonApi getOne(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) OperationalOperation operationalOperation, BindingResult result) {
		operationalOperation.setId(id);
		Map<String, Object> operationalOperationMap = operationalOperationService.getOne(operationalOperation);
		if (operationalOperationMap != null && !operationalOperationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, operationalOperationMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);

	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构操作列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/operations" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OperationalOperation operationalOperation, BindingResult result) {
		Page<?> page = PageHelper.startPage(operationalOperation.getPage(), operationalOperation.getPageSize());
		List<Map<String, Object>> operationalOperationList = operationalOperationService.getList(operationalOperation);
		if (operationalOperationList != null && !operationalOperationList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), operationalOperationList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);

	}
}
