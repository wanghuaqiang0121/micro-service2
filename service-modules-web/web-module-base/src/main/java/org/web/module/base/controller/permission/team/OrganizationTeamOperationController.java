package org.web.module.base.controller.permission.team;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.permission.team.OrganizationTeamOperation;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.permission.team.OrganizationTeamOperationService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 团队操作
 */
@RestController
public class OrganizationTeamOperationController {

	@Resource
	private OrganizationTeamOperationService organizationTeamOperationService;

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增团队操作
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PostMapping(value = { "/organization/team/operation" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationTeamOperation organizationTeamOperation, BindingResult result) {
		/* 判断数据是否重复 */
		Map<String, Object> teamOperationRepeatMap = organizationTeamOperationService.getRepeat(organizationTeamOperation);
		if (teamOperationRepeatMap != null && !teamOperationRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("team.operation.code.is.conflict"));
		}
		organizationTeamOperation.setCreateDate(new Date());
		organizationTeamOperation.setIsUsed(true);
		/* 新增团队操作 */
		if (organizationTeamOperationService.insert(organizationTeamOperation) > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", organizationTeamOperation.getId());
			return new JsonApi(ApiCodeEnum.OK, map);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationTeamOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查看团队操作详情
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/operation/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) OrganizationTeamOperation organizationTeamOperation, BindingResult result) {
		organizationTeamOperation.setId(id);
		/* 查看团队操作详情 */
		Map<String, Object> organizationTeamOperationMap = organizationTeamOperationService.getOne(organizationTeamOperation);
		if (organizationTeamOperationMap != null && !organizationTeamOperationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, organizationTeamOperationMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationTeamOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除团队操作
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@DeleteMapping(value = { "/organization/team/operation/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id, @Validated({ BaseEntity.Delete.class }) OrganizationTeamOperation organizationTeamOperation, BindingResult result) {
		organizationTeamOperation.setId(id);
		/* 删除团队操作 */
		if (organizationTeamOperationService.delete(organizationTeamOperation) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationTeamOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查看团队操作列表
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@GetMapping(value = { "/organization/team/operations" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OrganizationTeamOperation organizationTeamOperation, BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationTeamOperation.getPage(), organizationTeamOperation.getPageSize());
		/* 查看团队操作列表 */
		List<Map<String, Object>> teamOperationsResultMap = organizationTeamOperationService.getList(organizationTeamOperation);
		if (teamOperationsResultMap != null && !teamOperationsResultMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), teamOperationsResultMap));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationTeamOperation
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改团队操作
	 */
	@RequiresAuthentication(value = { "web-module-base:permission:manager" }, level = Level.ROLE)
	@PutMapping(value = { "/organization/team/operation/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody OrganizationTeamOperation organizationTeamOperation, BindingResult result) {
		/* 修改的数据是否存在 */
		organizationTeamOperation.setId(id);
		Map<String, Object> organizationTeamOperationMap = organizationTeamOperationService.getOne(organizationTeamOperation);
		if (organizationTeamOperationMap == null || organizationTeamOperationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if (null != organizationTeamOperation.getCode()) {
			Map<String, Object> teamOperationRepeatMap = organizationTeamOperationService.getRepeat(organizationTeamOperation);
			if (teamOperationRepeatMap != null && !teamOperationRepeatMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("team.operation.code.is.conflict"));
			}
		}
		/* 修改团队操作 */
		if (organizationTeamOperationService.update(organizationTeamOperation) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
