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
import org.web.module.organization.domain.PensionOrganizationInfo;
import org.web.module.organization.service.PensionOrganizationInfoService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 养老机构扩展信息
 */
@RestController
public class PensionOrganizationInfoController {

	@Resource
	private PensionOrganizationInfoService pensionOrganizationInfoService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param pensionOrganizationInfo
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询养老机构扩展信息列表
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:pension-organization-info:get-list" })
	@GetMapping(value = { "/pension/organization/info" })
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class })  PensionOrganizationInfo pensionOrganizationInfo,BindingResult result) {
		Page<?> page = PageHelper.startPage(pensionOrganizationInfo.getPage(), pensionOrganizationInfo.getPageSize());
		List<Map<String, Object>> pensionOrganizationInfoList = pensionOrganizationInfoService.getList(pensionOrganizationInfo);
		if (pensionOrganizationInfoList != null && !pensionOrganizationInfoList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), pensionOrganizationInfoList));
		}

		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param pensionOrganizationInfo
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增养老机构扩展信息
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:pension-organization-info:insert" })
	@PostMapping(value = { "/pension/organization/info" })
	public JsonApi insert(
			@Validated({ BaseEntity.Insert.class })@RequestBody  PensionOrganizationInfo pensionOrganizationInfo,
			BindingResult result) {
		pensionOrganizationInfo.setCreateDate(new Date());
		if (pensionOrganizationInfoService.insert(pensionOrganizationInfo) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param pensionOrganizationInfo
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改养老机构扩展信息
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:pension-organization-info:update" })
	@PutMapping(value = { "/pension/organization/info/{id}" })
	public JsonApi update(
			@PathVariable("id")Integer id,
			@Validated({ BaseEntity.Update.class })@RequestBody  PensionOrganizationInfo pensionOrganizationInfo,BindingResult result) {
		pensionOrganizationInfo.setId(id);
		Map<String, Object> pensionOrganizationInfoOne = pensionOrganizationInfoService.getOne(pensionOrganizationInfo);
		if (MapUtils.isEmpty(pensionOrganizationInfoOne)) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if (pensionOrganizationInfoService.update(pensionOrganizationInfo) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param pensionOrganizationInfo
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询养老机构扩展信息详情
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:pension-organization-info:get-detail" })
	@GetMapping(value = { "/pension/organization/info/{id}" })
	public JsonApi getDetail(@PathVariable("id")Integer id,
			@Validated({ BaseEntity.SelectOne.class })  PensionOrganizationInfo pensionOrganizationInfo,
			BindingResult result) {
		pensionOrganizationInfo.setId(id);
		/*数据是否存在*/
		Map<String, Object> pensionOrganizationInfoOne = pensionOrganizationInfoService.getOne(pensionOrganizationInfo);
		if (MapUtils.isNotEmpty(pensionOrganizationInfoOne)) {
			return new JsonApi(ApiCodeEnum.OK,pensionOrganizationInfoOne);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
