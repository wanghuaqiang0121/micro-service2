package org.web.module.organization.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.OrganizationPersonType;
import org.web.module.organization.service.OrganizationPersonTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 机构用户类型
 */
@RestController
public class OrganizationPersonTypeController {

	@Resource
	private OrganizationPersonTypeService organizationPersonTypeService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationPersonType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构用户类型列表
	 */
	@RequiresAuthentication(value = { "web-module-organization:organization-person-type:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/person/types" })
	public JsonApi getOrganizationPersonTypeList(
			@Validated({ BaseEntity.SelectAll.class }) OrganizationPersonType organizationPersonType,BindingResult result ){
		Page<?> page = PageHelper.startPage(organizationPersonType.getPage(), organizationPersonType.getPageSize());
		List<Map<String, Object>>  organizationPersonTypeList=organizationPersonTypeService.getList(organizationPersonType);
		if (organizationPersonTypeList!=null && !organizationPersonTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationPersonTypeList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}
}
