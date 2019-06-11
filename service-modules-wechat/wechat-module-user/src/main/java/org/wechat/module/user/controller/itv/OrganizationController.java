package org.wechat.module.user.controller.itv;

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
import org.wechat.module.user.domain.itv.Organization;
import org.wechat.module.user.service.itv.OrganizationService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 机构
 */
@RestController
public class OrganizationController {
	
	@Resource
	private OrganizationService organizationService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param id
	 * @param organization
	 * @param result
	 * @return
	 * @description: 机构详情
	 */
	@RequiresAuthentication(ignore = true)
	@GetMapping(value = { "/itv/organization/{id}" })
	public JsonApi organizationDetail(@PathVariable("id")Integer id,
			@Validated({ BaseEntity.SelectOne.class })  Organization organization,BindingResult result) {
		organization.setId(id);
		Map<String, Object> organizationOneMap = organizationService.getOne(organization);
		if (organizationOneMap!=null && !organizationOneMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,organizationOneMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param organization
	 * @param result
	 * @return
	 * @description: 查询机构列表
	 */
	@RequiresAuthentication(ignore = true)
	@GetMapping(value = { "/itv/organizations" })
	public JsonApi organizationList(
			@Validated({ BaseEntity.SelectAll.class })  Organization organization,BindingResult result) {
		Page<?> page = PageHelper.startPage(organization.getPage(),organization.getPageSize());
		List<Map<String, Object> > organizationList = organizationService.getList(organization);
		if (organizationList != null && organizationList.size() >0) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),organizationList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
