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
import org.web.module.organization.domain.OrganizationGrade;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.OrganizationGradeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 机构等级
 */
@RestController
public class OrganizationGradeController {
	
	@Resource
	private OrganizationGradeService organizationGradeService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationGrade
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 新增机构等级
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-grade:insert" })
	@PostMapping(value = { "/organization/grade" })
	public JsonApi insert(
			@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationGrade organizationGrade, BindingResult result){
		organizationGrade.setCreateDate(new Date());
		Map<String,Object>  organizationGradeRepeatMap=organizationGradeService.getRepeat(organizationGrade);
		if (MapUtils.isNotEmpty(organizationGradeRepeatMap)) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.grade.name.is.exists"));
		}
		if (organizationGradeService.insert(organizationGrade)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param organizationGrade
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 修改机构等级
	 */
	@RequiresAuthentication(level=Level.OPERATION,value={"web-module-organization:organization-grade:update" })
	@PutMapping(value = { "/organization/grade/{id}" })
	public JsonApi update(
			@PathVariable("id")Integer id,
			@Validated({BaseEntity.Update.class})@RequestBody OrganizationGrade organizationGrade,BindingResult result){
		/* 判断数据是否存在*/
		organizationGrade.setId(id);
		Map<String, Object> organizationGradeDetailMap=organizationGradeService.getDetail(organizationGrade);
		if (MapUtils.isNotEmpty(organizationGradeDetailMap)) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 数据存在*/
		if (null!=organizationGrade.getName()) {
			/* 判断重复*/
			Map<String, Object> organizationGradeRepeatMap = organizationGradeService.getRepeat(organizationGrade);
			if (MapUtils.isNotEmpty(organizationGradeRepeatMap)) {
				if (!organizationGradeDetailMap.get("id").equals(organizationGradeRepeatMap.get("id"))) {
					return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.grade.name.is.exists"));
				}
			}
		}
		if (organizationGradeService.update(organizationGrade)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
		
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationGrade
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构等级列表
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-grade:get-list" })
	@GetMapping(value = { "/organization/grades" })
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class })  OrganizationGrade organizationGrade, BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationGrade.getPage(), organizationGrade.getPageSize());		
		List<Map<String, Object>> organizationGradeList = organizationGradeService.getList(organizationGrade);
		if (organizationGradeList !=null && !organizationGradeList.isEmpty() ) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(),organizationGradeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

}
