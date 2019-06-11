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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.OrganizationWechatInfo;
import org.web.module.organization.service.OrganizationWechatInfoService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 机构微信公众号
 */
@RestController
public class OrganizationWechatInfoController {

	@Resource
	private OrganizationWechatInfoService organizationWechatInfoService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationWechatInfo
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构微信公众号列表
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-wechat-info:get-list" })
	@GetMapping(value = { "/organization/wechat/info" })
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class })  OrganizationWechatInfo organizationWechatInfo,BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationWechatInfo.getPage(), organizationWechatInfo.getPageSize());
		List<Map<String, Object>> organizationWechatInfoList = organizationWechatInfoService.getList(organizationWechatInfo);
		if (organizationWechatInfoList != null && !organizationWechatInfoList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationWechatInfoList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationWechatInfo
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增机构微信公众号
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-wechat-info:insert" })
	@PostMapping(value = { "/organization/wechat/info" })
	public JsonApi insert(
			@Validated({ BaseEntity.Insert.class })@RequestBody  OrganizationWechatInfo organizationWechatInfo,
			BindingResult result) {
		organizationWechatInfo.setCreateDate(new Date());
		if (organizationWechatInfoService.insert(organizationWechatInfo) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationWechatInfo
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改机构微信公众号
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-wechat-info:update" })
	@PutMapping(value = { "/organization/wechat/info/{id}" })
	public JsonApi update(
			@PathVariable("id")Integer id,
			@Validated({ BaseEntity.Update.class }) @RequestBody OrganizationWechatInfo organizationWechatInfo,BindingResult result) {
		organizationWechatInfo.setId(id);
		/*数据是否存在*/
		Map<String, Object> organizationWechatInfoOneMap = organizationWechatInfoService.getOne(organizationWechatInfo);
		if (MapUtils.isEmpty(organizationWechatInfoOneMap)) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/*修改机构微信公众号*/
		if (organizationWechatInfoService.update(organizationWechatInfo) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationWechatInfo
	 * @param resultd
	 * @return {@link JsonApi}
	 * @description: 机构微信公众号详情
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-wechat-info:get-detail" })
	@GetMapping(value = { "/organization/wechat/info/{id}" })
	public JsonApi getDetail(@PathVariable("id")Integer id,
			@Validated({ BaseEntity.SelectOne.class })  OrganizationWechatInfo organizationWechatInfo,
			BindingResult resultd) {
		organizationWechatInfo.setId(id);
		/*数据是否存在*/
		Map<String, Object> organizationWechatInfoOneMap = organizationWechatInfoService.getOne(organizationWechatInfo);
		if (MapUtils.isNotEmpty(organizationWechatInfoOneMap)) {
			return new JsonApi(ApiCodeEnum.OK,organizationWechatInfoOneMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
