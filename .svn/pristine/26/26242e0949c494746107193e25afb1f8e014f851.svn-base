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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.organization.domain.OrganizationSite;
import org.wechat.module.organization.service.OrganizationSiteService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构站点
 */
@RestController
public class OrganizationSiteController {

	@Resource
	private OrganizationSiteService organizationSiteService;

	

	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationSite
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 站点列表
	 */
	@RequiresAuthentication(authc = true, value = { })
	@RequestMapping(value = { "/organization/sites" }, method = RequestMethod.GET)
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class }) OrganizationSite organizationSite, BindingResult result) {
		if (organizationSite.getLat() != null
				&& organizationSite.getLng() != null
				&& organizationSite.getMaxRaidus() != null) {
			organizationSite.setRectangle(organizationSite.getRectangle());
		}
		Page<?> page = PageHelper.startPage(organizationSite.getPage(), organizationSite.getPageSize());
		List<Map<String, Object>> organizationSiteList = organizationSiteService.getList(organizationSite);
		if (organizationSiteList !=null && !organizationSiteList.isEmpty() ) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),organizationSiteList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
}
