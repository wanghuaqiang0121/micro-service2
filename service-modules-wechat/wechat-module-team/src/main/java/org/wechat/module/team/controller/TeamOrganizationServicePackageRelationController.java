package org.wechat.module.team.controller;

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
import org.wechat.module.team.domain.Organization;
import org.wechat.module.team.domain.TeamOrganizationServicePackage;
import org.wechat.module.team.global.BaseGlobalEnum;
import org.wechat.module.team.service.TeamOrganizationServicePackageService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 团队机构服务包
 */
@RestController
public class TeamOrganizationServicePackageRelationController {

	@Resource
	private TeamOrganizationServicePackageService teamOrganizationServicePackageService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param id
	 * @param teamOrganizationServicePackage
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 团队服务包详情
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/team/organization/service/package/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id,@Validated({ BaseEntity.SelectOne.class }) TeamOrganizationServicePackage  teamOrganizationServicePackage,BindingResult result){
		/*设置主键*/
		teamOrganizationServicePackage.setId(id);
		/*团队服务包详情*/ 
		Map<String, Object> teamPackageMap = teamOrganizationServicePackageService.getOne(teamOrganizationServicePackage);
		if (teamPackageMap!=null && !teamPackageMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,teamPackageMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param teamOrganizationServicePackage
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 团队服务包列表
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/team/organization/service/packages" })
	public JsonApi getList(
			@Validated(BaseEntity.SelectAll.class) TeamOrganizationServicePackage teamOrganizationServicePackage,BindingResult result){
		/*机构经纬度*/  
		if (teamOrganizationServicePackage.getOrganization() != null) {
			if (teamOrganizationServicePackage.getOrganization().getLat() != null
					&& teamOrganizationServicePackage.getOrganization().getLng() != null
					&& teamOrganizationServicePackage.getOrganization().getMaxRaidus() != null) {
				/*设置经纬度 */ 
				Organization organization = teamOrganizationServicePackage.getOrganization();
				organization.setRectangle(organization.getRectangle());
			}
		}
		/* 分页设置*/
		Page<?> page = PageHelper.startPage(teamOrganizationServicePackage.getPage(), teamOrganizationServicePackage.getPageSize());
		/* 查询团队机构服务包列表*/
		/* 设置查询非基本公共卫生服务包 */
		teamOrganizationServicePackage.setIsBasePublicHealth(false);
		teamOrganizationServicePackage.setServicePackageTypeCode(BaseGlobalEnum.Type.JGW.getValue());
		List<Map<String, Object>> teamOrganizationServicePackagesList = teamOrganizationServicePackageService.getList(teamOrganizationServicePackage);
		if (teamOrganizationServicePackagesList!=null && !teamOrganizationServicePackagesList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(), teamOrganizationServicePackagesList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
