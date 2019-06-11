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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.team.domain.Organization;
import org.wechat.module.team.domain.OrganizationTeam;
import org.wechat.module.team.global.BaseGlobal;
import org.wechat.module.team.service.OrganizationTeamService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构团队
 */
@RestController
public class OrganizationTeamController {

	@Resource
	private OrganizationTeamService organizationTeamService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param id
	 * @param organizationTeam
	 * @param result
	 * @param token
	 * @return  {@link JsonApi}
	 * @description: 机构团队详情
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/organization/team/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id,@Validated({ BaseEntity.SelectOne.class }) OrganizationTeam  organizationTeam,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		/*设置主键*/
		organizationTeam.setId(id);
		Map<String, Object> organizationTeamMap = organizationTeamService.getOne(organizationTeam);
		if (organizationTeamMap!=null && !organizationTeamMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,organizationTeamMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月12日
	 * @param organizationTeam
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 查询机构团队列表（内嵌服务包列表）
	 */
	@RequiresAuthentication(authc = true, value = { })
	@GetMapping(value = { "/organization/teams" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OrganizationTeam  organizationTeam,BindingResult result){
		/*机构经纬度*/  
		if (organizationTeam.getOrganization() != null) {
			if (organizationTeam.getOrganization().getLat() != null
					&& organizationTeam.getOrganization().getLng() != null
					&& organizationTeam.getOrganization().getMaxRaidus() != null) {
				/*设置经纬度 */ 
				Organization organization = organizationTeam.getOrganization();
				organization.setRectangle(organization.getRectangle());
			}
		}
		Page<?> page = PageHelper.startPage(organizationTeam.getPage(), organizationTeam.getPageSize());
		List<Map<String, Object>>  organizationTeamList=organizationTeamService.getList(organizationTeam);
		if (organizationTeamList!=null && !organizationTeamList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationTeamList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}
}
