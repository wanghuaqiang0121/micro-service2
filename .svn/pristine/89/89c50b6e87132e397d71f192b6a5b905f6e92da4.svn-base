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
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.organization.domain.Organization;
import org.wechat.module.organization.service.OrganizationService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构
 */
@RestController
public class OrganizationController {
	
	@Resource
	private OrganizationService organizationService;
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organization
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  查询机构列表
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/organizations" })
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class })  Organization organization,BindingResult result) {
		/*设置值*/
		if (organization.getLat() != null
				&& organization.getLng() != null
				&& organization.getMaxRaidus() != null) {
			organization.setRectangle(organization.getRectangle());
		}
		Page<?> page = PageHelper.startPage(organization.getPage(),organization.getPageSize());
		List<Map<String, Object> > organizationList = organizationService.getList(organization);
		if (organizationList != null && !organizationList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),organizationList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
