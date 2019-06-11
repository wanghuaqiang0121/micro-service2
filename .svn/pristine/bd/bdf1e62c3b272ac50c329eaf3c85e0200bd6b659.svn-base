package org.web.module.organization.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.auth.level.Level;
import org.service.core.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.OrganizationTeam;
import org.web.module.organization.domain.user.OrganizationUserTeam;
import org.web.module.organization.global.BaseGlobal;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.OrganizationTeamService;
import org.web.module.organization.service.user.OrganizationUserTeamService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 机构团队表
 */
@RestController
public class OrganizationTeamController {
	@Resource
	private OrganizationTeamService organizationTeamService;
	
	@Resource
	private OrganizationUserTeamService organizationUserTeamService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationTeam
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 新建机构团队
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-team:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/team" })
	@Transactional
	public JsonApi insert(
			@RequestBody @Validated({ BaseEntity.Insert.class }) OrganizationTeam  organizationTeam,BindingResult result,			
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId
			){
		/*设置时间*/
		organizationTeam.setCreateDate(new Date());
		/*设置机构*/
		organizationTeam.setOrganizationId(organizationId);
		
		/*判断机构团队是否重复*/
		Map<String, Object> organizationTeamMap = organizationTeamService.getRepeat(organizationTeam);
		if (organizationTeamMap!=null && !organizationTeamMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.team.organization.name.is.exists"));
		}
		/*新增机构团队*/
		if (organizationTeamService.insert(organizationTeam)>0) {
			OrganizationUserTeam doctorDoctorTeam = new OrganizationUserTeam();
			doctorDoctorTeam.setOrganizationTeamId(organizationTeam.getId());
			doctorDoctorTeam.setOrganizationUserId(organizationTeam.getOrganizationUserId());
			doctorDoctorTeam.setIsManager(true);
			doctorDoctorTeam.setCreateDate(new Date());
			if (organizationUserTeamService.insert(doctorDoctorTeam)>0) {
				return new JsonApi(ApiCodeEnum.OK);
			}
		
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationTeam
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  修改机构团队
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-team:update" },level=Level.OPERATION)
	@PutMapping(value = { "/organization/team/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,@RequestBody @Validated({ BaseEntity.Update.class })  OrganizationTeam  organizationTeam,BindingResult result
			){
		/*判断数据是否存在*/
		organizationTeam.setId(id);
		Map<String, Object> organizationTeamMap = organizationTeamService.getOne(organizationTeam);
		if (organizationTeamMap==null || organizationTeamMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		if (organizationTeamService.update(organizationTeam)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
		
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationTeam
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构团队详情
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-team:get-detail" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/team/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id,@Validated({ BaseEntity.SelectOne.class }) OrganizationTeam  organizationTeam,BindingResult result){
		/*设置主键*/
		organizationTeam.setId(id);
		Map<String, Object> organizationTeamMap = organizationTeamService.getOne(organizationTeam);
		if (organizationTeamMap!=null && !organizationTeamMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,organizationTeamMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationTeam
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 查询机构团队列表
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-team:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/teams" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OrganizationTeam  organizationTeam,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId ){
		organizationTeam.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationTeam.getPage(), organizationTeam.getPageSize());
		List<Map<String, Object>>  organizationTeamList=organizationTeamService.getList(organizationTeam);
		if (organizationTeamList!=null && !organizationTeamList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationTeamList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}
	
	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationTeam
	 * @param token
	 * @param organizationId
	 * @param moduleId
	 * @param result
	 * @return
	 * @date 2018年3月20日
	 * @version 1.0
	 * @description 验证团队名称
	 * 
	 */
	@RequiresAuthentication(value = { "organization:configure:organizationTeam:validTeamNameRepeat" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/team/valid/team/name" })
	public JsonApi validTeamNameRepeat(
			@Validated({ OrganizationTeam.validTeamNameRepeat.class }) OrganizationTeam  organizationTeam,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId
			){
		Map<String, Object> resultMap = new HashMap<>();
		/*设置机构*/
		organizationTeam.setOrganizationId(organizationId);
		/*判断机构团队是否重复*/
		Map<String, Object> organizationTeamMap = organizationTeamService.getRepeat(organizationTeam);
		if (organizationTeamMap!=null && !organizationTeamMap.isEmpty()) {
			resultMap.put("isRepeat",true);
			return new JsonApi(ApiCodeEnum.OK,resultMap).setMsg(Prompt.bundle("organization.team.organization.name.is.exists"));
		}
		resultMap.put("isRepeat",false);
		return new JsonApi(ApiCodeEnum.OK,resultMap);
	}

}
