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
import org.web.module.base.domain.permission.organization.OperationalPermissionOperation;
import org.web.module.base.service.permission.organization.OperationalPermissionOperationService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月31日
 * @description: 权限操作关联表
 */
@RestController
public class OperationalPermissionOperationRelationController {
	@Resource
	private OperationalPermissionOperationService operationalPermissionOperationService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermissionOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增权限操作
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/operational/permission/operation" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class }) OperationalPermissionOperation operationalPermissionOperation, BindingResult result) {
		/* 检查数据是否存在 */
		Map<String, Object> operationalPermissionOperationMap = operationalPermissionOperationService.getRepeat(operationalPermissionOperation);
		if (operationalPermissionOperationMap != null && !operationalPermissionOperationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT);
		}
		/* 新增 */
		if (operationalPermissionOperationService.insert(operationalPermissionOperation) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermissionId
	 * @param operationalOperationId
	 * @param operationalPermissionOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除权限操作
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/operational/permission/{operationalPermissionId}/operation/{operationalOperationId}" })
	public JsonApi delete(@PathVariable("operationalPermissionId") Integer operationalPermissionId, @PathVariable("operationalOperationId") Integer operationalOperationId,
			@Validated({ BaseEntity.Delete.class }) OperationalPermissionOperation operationalPermissionOperation, BindingResult result) {
		operationalPermissionOperation.setOperationalPermissionId(operationalPermissionId);
		operationalPermissionOperation.setOperationalOperationId(operationalOperationId);
		if (operationalPermissionOperationService.delete(operationalPermissionOperation) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param operationalPermissionOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询该权限拥有和未拥有的操作
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/operational/permission/operations" })
	public JsonApi getPermissionOperationIsChoose(@Validated({ OperationalPermissionOperation.GetPermissionOperationIsChoose.class }) OperationalPermissionOperation operationalPermissionOperation,
			BindingResult result) {
		Page<?> page = PageHelper.startPage(operationalPermissionOperation.getPage(), operationalPermissionOperation.getPageSize());
		List<Map<String, Object>> operationalPermissionOperationList = operationalPermissionOperationService.getPermissionOperationIsChoose(operationalPermissionOperation);
		if (operationalPermissionOperationList != null && !operationalPermissionOperationList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), operationalPermissionOperationList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
