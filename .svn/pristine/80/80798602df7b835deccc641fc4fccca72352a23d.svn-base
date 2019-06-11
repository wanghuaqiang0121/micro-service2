package org.web.module.organization.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.OrganizationType;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.OrganizationTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 机构类型
 */
@RestController
public class OrganizationTypeController {

	@Resource
	private OrganizationTypeService organizationTypeService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增机构类型
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-organization:organization-type:insert" })
	@PostMapping(value = { "/organization/type" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationType organizationType,
			BindingResult result) {
		organizationType.setCreateDate(new Date());
		/* 判断是否重复 */
		Map<String, Object> organizationScaleRepeatMap = organizationTypeService.getRepeat(organizationType);
		if (MapUtils.isNotEmpty(organizationScaleRepeatMap)) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.type.name.is.conflict"));
		}
		/* 新增 */
		if (organizationTypeService.insert(organizationType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改机构类型
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-organization:organization-type:update" })
	@PutMapping(value = { "/organization/type/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,
			@Validated({ BaseEntity.Update.class }) @RequestBody OrganizationType organizationType,
			BindingResult result) {
		organizationType.setId(id);
		/* 数据是否存在 */
		Map<String, Object> organizationTypeOneMap = organizationTypeService.getOne(organizationType);
		if (MapUtils.isEmpty(organizationTypeOneMap)) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 修改的数据是否重复 */
		Map<String, Object> organizationTypeRepeatMap = organizationTypeService.getRepeat(organizationType);
		if (MapUtils.isNotEmpty(organizationTypeRepeatMap)) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.type.name.is.conflict"));
		}
		/* 修改 */
		if (organizationTypeService.update(organizationType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 删除机构类型
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-organization:organization-type:delete" })
	@DeleteMapping(value = { "/organization/type/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id,
			@Validated({ BaseEntity.Delete.class }) OrganizationType organizationType, BindingResult result) {
		organizationType.setId(id);
		/* 删除 */
		if (organizationTypeService.delete(organizationType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构类型详情
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-organization:organization-type:get-detail" })
	@RequestMapping(value = { "/organization/type/{id}" }, method = RequestMethod.GET)
	public JsonApi getDetail(@PathVariable("id") Integer id,
			@Validated({ BaseEntity.SelectOne.class }) OrganizationType organizationType, BindingResult result) {
		organizationType.setId(id);
		Map<String, Object> organizationTypeOneMap = organizationTypeService.getOne(organizationType);
		if (MapUtils.isNotEmpty(organizationTypeOneMap)) {
			return new JsonApi(ApiCodeEnum.OK, organizationTypeOneMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构类型列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-organization:organization-type:get-list" })
	@GetMapping(value = { "/organization/type" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OrganizationType organizationType,
			BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationType.getPage(), organizationType.getPageSize());
		List<Map<String, Object>> organizationTypeList = organizationTypeService.getList(organizationType);
		if (organizationTypeList != null && !organizationTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationTypeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
