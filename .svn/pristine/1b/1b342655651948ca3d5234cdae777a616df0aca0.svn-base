package org.web.module.team.controller.packages;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.team.domain.packages.TeamOrganizationServicePackage;
import org.web.module.team.global.BaseGlobalEnum;
import org.web.module.team.message.Prompt;
import org.web.module.team.service.packages.TeamOrganizationServicePackageService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 团队机构服务包关联表
 */
@RestController
public class TeamOrganizationServicePackageRelationController {

	@Resource
	private TeamOrganizationServicePackageService teamOrganizationServicePackageService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param teamOrganizationServicePackage
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 添加团队机构服务包关联表
	 */
	@RequiresAuthentication( value = { "web-module-team:team-organization-service-package:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/team/organization/service/package" })
	public JsonApi insert(
			@Validated(BaseEntity.Insert.class) @RequestBody TeamOrganizationServicePackage teamOrganizationServicePackage,BindingResult result){
		/* 查询数据是否存在重复*/
		Map<String, Object> teamOrganizationServicePackageMap = teamOrganizationServicePackageService.getRepeat(teamOrganizationServicePackage);
		if (teamOrganizationServicePackageMap!=null  && !teamOrganizationServicePackageMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("team.organization.service.package.is.exists"));
		}
		/*设置状态 启用 ：0*/ 
		teamOrganizationServicePackage.setStatus(BaseGlobalEnum.OrganizationTeam.ENABLE.getValue());
		/*设置创建时间*/ 
		teamOrganizationServicePackage.setCreateDate(new Date());
		/* 添加机构服务包和服务关联*/
		if (teamOrganizationServicePackageService.insert(teamOrganizationServicePackage) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param teamOrganizationServicePackage
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改团队机构服务包关联
	 */
	@RequiresAuthentication( value = { "web-module-team:team-trganization-service-package:update" },level=Level.OPERATION)
	@PutMapping(value = { "/team/organization/service/package/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,
			@Validated(BaseEntity.Update.class) @RequestBody TeamOrganizationServicePackage teamOrganizationServicePackage,BindingResult result){
		/*设置id*/ 
		teamOrganizationServicePackage.setId(id);
		/*设置状态为空*/ 
		teamOrganizationServicePackage.setStatus(null);
		/*查询数据是否存在*/ 
		Map<String, Object> teamOrganizationServicePackageMap = teamOrganizationServicePackageService.getOne(teamOrganizationServicePackage);
		if (teamOrganizationServicePackageMap==null || teamOrganizationServicePackageMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/*查询数据是否存在*/
		if (teamOrganizationServicePackageService.update(teamOrganizationServicePackage) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param teamOrganizationServicePackage
	 * @param result
	 * @return{@link JsonApi}
	 * @description: 团队服务包上架
	 */
	@RequiresAuthentication( value = { "web-module-team:team-trganization-service-package:on-the-shelf" },level=Level.OPERATION)
	@PutMapping(value = { "/team/organization/service/package/onshelves/{id}" })
	public JsonApi onTheShelf(@PathVariable("id") Integer id,
			@Validated(TeamOrganizationServicePackage.Onshelves.class) TeamOrganizationServicePackage teamOrganizationServicePackage,BindingResult result){
		TeamOrganizationServicePackage onshelvesPackage = new TeamOrganizationServicePackage();
		/*设置id*/ 
		onshelvesPackage.setId(id);
		/*查询数据是否存在*/ 
		Map<String, Object> teamOrganizationServicePackageMap = teamOrganizationServicePackageService.getOne(teamOrganizationServicePackage);
		if (teamOrganizationServicePackageMap==null || teamOrganizationServicePackageMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/*如果已上架*/ 
		if ((Integer) teamOrganizationServicePackageMap.get("status") == BaseGlobalEnum.OrganizationServicePackage.THESHELVES.getValue()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("team.organization.service.package.shelves"));
		}
		/* 设置状态*/
		onshelvesPackage.setStatus(BaseGlobalEnum.OrganizationServicePackage.THESHELVES.getValue());
		/*修改团队机构服务包关联（状态   上架）*/ 
		if (teamOrganizationServicePackageService.update(onshelvesPackage) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param teamOrganizationServicePackage
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 团队服务包下架
	 */
	@RequiresAuthentication( value = { "web-module-team:team-trganization-service-package:lower-frame" },level=Level.OPERATION)
	@PutMapping(value = { "/team/organization/service/package/offshelves/{id}" })
	public JsonApi lowerFrame(@PathVariable("id") Integer id,
			@Validated(TeamOrganizationServicePackage.Offshelves.class) TeamOrganizationServicePackage teamOrganizationServicePackage,BindingResult result){
		TeamOrganizationServicePackage offshelvesPackage = new TeamOrganizationServicePackage();
		/*设置id*/ 
		offshelvesPackage.setId(id);
		/*查询数据是否存在*/ 
		Map<String, Object> teamOrganizationServicePackageOneResult = teamOrganizationServicePackageService.getOne(offshelvesPackage);
		if (teamOrganizationServicePackageOneResult==null || teamOrganizationServicePackageOneResult.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/*如果已下架*/ 
		if ((Integer) teamOrganizationServicePackageOneResult.get("status") == BaseGlobalEnum.OrganizationServicePackage.OFFTHESHELF.getValue()) {
			return new JsonApi(ApiCodeEnum.FAIL).setMsg(Prompt.bundle("team.organization.service.package.offshelves"));
		}
		/*设置状态 下架*/ 
		offshelvesPackage.setStatus(BaseGlobalEnum.OrganizationServicePackage.OFFTHESHELF.getValue());
		/*修改团队机构服务包关联（状态   下架）*/ 
		if (teamOrganizationServicePackageService.update(offshelvesPackage) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param doctorTeamId
	 * @param organizationServicePackageId
	 * @param teamOrganizationServicePackage
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 删除团队机构服务包关联
	 */
	@RequiresAuthentication( value = { "web-module-team:team-trganization-service-package:delete" },level=Level.OPERATION)
	@DeleteMapping(value = { "/team/{doctorTeamId}/organization/service/package/{organizationServicePackageId}" })
	public JsonApi delete(
			@PathVariable("doctorTeamId") Integer doctorTeamId,@PathVariable("organizationServicePackageId") Integer organizationServicePackageId,
			@Validated(BaseEntity.Delete.class) TeamOrganizationServicePackage teamOrganizationServicePackage,BindingResult result){
		/*设置团队id*/ 
		teamOrganizationServicePackage.setDoctorTeamId(doctorTeamId);
		/*设置机构服务包id*/ 
		teamOrganizationServicePackage.setOrganizationServicePackageId(organizationServicePackageId);
		/* 删除团队机构服务包关联*/
		if (teamOrganizationServicePackageService.delete(teamOrganizationServicePackage) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param teamOrganizationServicePackage
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 查询团队机构服务包列表
	 */
	@RequiresAuthentication( value = { "web-module-team:team-trganization-service-package:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/team/organization/service/packages" })
	public JsonApi getList(
			@Validated(BaseEntity.SelectAll.class) TeamOrganizationServicePackage teamOrganizationServicePackage,BindingResult result){
		/* 分页设置*/
		Page<?> page = PageHelper.startPage(teamOrganizationServicePackage.getPage(), teamOrganizationServicePackage.getPageSize());
		/*查询团队机构服务包列表*/ 
		List<Map<String, Object>> teamOrganizationServicePackagesList = teamOrganizationServicePackageService.getList(teamOrganizationServicePackage);
		if (teamOrganizationServicePackagesList!=null && !teamOrganizationServicePackagesList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(), teamOrganizationServicePackagesList));
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param teamOrganizationServicePackage
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 查询本机构服务包对应团队授权操作
	 */
	@RequiresAuthentication( value = { "web-module-team:team-trganization-service-package:get-teams-authorize" },level=Level.OPERATION)
	@GetMapping(value = { "/team/organization/service/packages/team/authorize" })
	public JsonApi getTeamAuthorize(
			@Validated(TeamOrganizationServicePackage.OrganizationServicePackageTeamAuthorize.class) TeamOrganizationServicePackage teamOrganizationServicePackage,BindingResult result){
		/*分页设置*/ 
		Page<?> page = PageHelper.startPage(teamOrganizationServicePackage.getPage(), teamOrganizationServicePackage.getPageSize());
		/*查询机构服务包对应团队授权操作*/ 
		List<Map<String, Object>> teamAuthorizeList = teamOrganizationServicePackageService.getTeamAuthorizeList(teamOrganizationServicePackage);
		if (teamAuthorizeList!=null && teamAuthorizeList.size()>0) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(), teamAuthorizeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
}
