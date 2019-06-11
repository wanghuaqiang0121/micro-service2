package org.wechat.module.organization.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.organization.domain.OrganizationServicePackage;
import org.wechat.module.organization.service.OrganizationServicePackageService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构服务包
 */
@RestController
public class OrganizationServicePackageController {

	@Resource
	private OrganizationServicePackageService organizationServicePackageService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param id
	 * @param organizationServicePackage
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构的服务包详情
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/organization/service/package/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id,
			@Validated(BaseEntity.SelectOne.class) OrganizationServicePackage organizationServicePackage,
			BindingResult result) {
		/*设置id*/ 
		organizationServicePackage.setId(id);
		/*查询机构的服务包详情*/ 
		Map<String, Object> organizationServicePackageMap = organizationServicePackageService.getOne(organizationServicePackage);
		if (organizationServicePackageMap!=null && !organizationServicePackageMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, organizationServicePackageMap);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationServicePackage
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构服务包列表
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/organization/service/packages" })
	public JsonApi getList(
			@Validated(OrganizationServicePackage.SelectAll.class) OrganizationServicePackage organizationServicePackage,
			BindingResult result) {
		/*分页设置*/ 
		Page<?> page = PageHelper.startPage(organizationServicePackage.getPage(),organizationServicePackage.getPageSize());
		/*查询包列表*/ 
		List<Map<String, Object>> organizationServicePackageList = organizationServicePackageService.getList(organizationServicePackage);
		if (organizationServicePackageList!=null && !organizationServicePackageList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationServicePackageList));
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
