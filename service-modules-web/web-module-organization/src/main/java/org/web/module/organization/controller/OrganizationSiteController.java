package org.web.module.organization.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.Organization;
import org.web.module.organization.domain.OrganizationSite;
import org.web.module.organization.global.BaseGlobal;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.OrganizationService;
import org.web.module.organization.service.OrganizationSiteService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 机构站点
 */
@RestController
public class OrganizationSiteController {

	@Resource
	private OrganizationSiteService organizationSiteService;
	@Resource
	private OrganizationService organizationService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationSite
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 新增机构站点
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-site:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/site" })
	public JsonApi insert(
			@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationSite organizationSite, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		organizationSite.setCreateDate(new Date());
		organizationSite.setOrganizationId(organizationId);
		/*判断数据是否重复*/
		Map<String, Object> organizationSiteMap = organizationSiteService.getRepeat(organizationSite);
		if (organizationSiteMap!=null && !organizationSiteMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.site.name.is.exists"));
		}
		/*判断机构是否存在*/
		Organization organization = new Organization();
		organization.setId(organizationId);
		Map<String, Object> organizationMap = organizationService.getOne(organization);
		if (organizationMap==null || organizationMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("organization.is.null"));
		}
		/*新增*/
		if (organizationSiteService.insert(organizationSite) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationSite
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构站点详情
	 */
	@RequiresAuthentication(value = { "web-module-organization:organization-site:get-detail" },level = Level.OPERATION)
	@GetMapping(value = { "/organization/site/{id}" })
	public JsonApi getDetail(
			@PathVariable("id")Integer id,
			@Validated({ BaseEntity.SelectOne.class }) OrganizationSite organizationSite, BindingResult result) {
		organizationSite.setId(id);
		Map<String, Object> organizationSiteMap = organizationSiteService.getOne(organizationSite);
		if (organizationSiteMap!=null && !organizationSiteMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,organizationSiteMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationSite
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构站点列表
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-site:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/site" })
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class }) OrganizationSite organizationSite, BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationSite.getPage(), organizationSite.getPageSize());
		List<Map<String, Object>> organizationSiteList = organizationSiteService.getList(organizationSite);
		if (organizationSiteList !=null && !organizationSiteList.isEmpty() ) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),organizationSiteList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationSite
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 修改站点
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-site:update" },level=Level.OPERATION)
	@PutMapping(value = { "/organization/site/{id}" })
	public JsonApi update(
			@PathVariable("id")Integer id,
			@Validated({ BaseEntity.Update.class })@RequestBody OrganizationSite organizationSite, BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId) {
		/*修改的数据是否存在*/ 
		organizationSite.setId(id);
		Map<String, Object> organizationSiteMap = organizationSiteService.getOne(organizationSite);
		if (organizationSiteMap ==null || organizationSiteMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/*名称是否重复*/
		if (null != organizationSite.getName()) {
			organizationSite.setOrganizationId(organizationId);
			Map<String, Object> organizationSiteRepeatMap = organizationSiteService.getRepeat(organizationSite);
			if (organizationSiteRepeatMap!=null && !organizationSiteRepeatMap.isEmpty()) {
				return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.site.name.is.conflct"));
			}
		}
		/*修改*/
		if (organizationSiteService.update(organizationSite) >0 ) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
