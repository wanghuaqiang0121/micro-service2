package org.web.module.organization.user.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.user.domain.OrganizationUserType;
import org.web.module.organization.user.global.BaseGlobal;
import org.web.module.organization.user.message.Prompt;
import org.web.module.organization.user.sevice.OrganizationUserTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月23日
 * @description: 机构用户标签关联表
 */
@RestController
public class OrganizationUserTypeRelationController {
	@Resource
	private OrganizationUserTypeService organizationUserTypeService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserType
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 新增机构,用户类型关联表
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-type:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/user/type" })
	public JsonApi insert(@RequestBody @Validated({ BaseEntity.Insert.class })OrganizationUserType  organizationUserType,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId
			){
		organizationUserType.setOrganizationId(organizationId);
		Map<String, Object> OrganizationUserTypeMap=organizationUserTypeService.getRepeat(organizationUserType);
		if (OrganizationUserTypeMap!=null && !OrganizationUserTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.user.type.is.exists"));
		}
		if (organizationUserTypeService.insert(organizationUserType)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserType
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 查询机构,用户类型列表
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-type:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/user/types" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) OrganizationUserType  organizationUserType,
			BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId){
		organizationUserType.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationUserType.getPage(), organizationUserType.getPageSize());
		List<Map<String, Object>>  rganizationUserTypeList=organizationUserTypeService.getList(organizationUserType);
		if (rganizationUserTypeList!=null && !rganizationUserTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), rganizationUserTypeList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserType
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 当前机构没有关联的用户类型
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-type:get-is-null-user-types" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/is/null/user/types" })
	public JsonApi getIsNullUserTypes(
			@Validated({ OrganizationUserType.organizationUserTypeIsNull.class }) OrganizationUserType  organizationUserType,
			BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId ){
		organizationUserType.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationUserType.getPage(), organizationUserType.getPageSize());
		List<Map<String, Object>>  rganizationUserTypeList=organizationUserTypeService.getOrganizationUserTypeIsNull(organizationUserType);
		if (rganizationUserTypeList!=null && !rganizationUserTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), rganizationUserTypeList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationUserType
	 * @param result
	 * @param organizationId
	 * @return {@link JsonApi}
	 * @description: 机构拥有和未拥有的用户类型
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-type:get-organiztion-have-not-have-user-types" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/is/have/nothave/user/types" })
	public JsonApi gethaveNotHaveUserTypes(
			@Validated({ OrganizationUserType.organizationUserTypehaveAndNotHave.class }) OrganizationUserType  organizationUserType,
			BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId ){
		organizationUserType.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationUserType.getPage(), organizationUserType.getPageSize());
		List<Map<String, Object>>  rganizationUserTypeList=organizationUserTypeService.getOrganizationUserTypehaveAndNotHaveList(organizationUserType);
		if (rganizationUserTypeList!=null && !rganizationUserTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), rganizationUserTypeList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationIdd
	 * @param userTypeId
	 * @param rganizationUserType
	 * @param result
	 * @param organizationId
	 * @return
	 * @description: 删除机构用户标签
	 */
	@RequiresAuthentication( value = { "web-module-organization-user:organization-user-type:delete" },level=Level.OPERATION)
	@DeleteMapping(value = { "/organization/{organizationId}/user/type/{userTypeId}" })
	public JsonApi delete(@PathVariable("organizationId") Integer organizationIdd,
			@PathVariable("userTypeId") Integer userTypeId,
			@Validated({ BaseEntity.Delete.class }) OrganizationUserType  rganizationUserType,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId){
	    /*设置数据*/
		rganizationUserType.setOrganizationId(organizationId);
		rganizationUserType.setUserTypeId(userTypeId);
		if (organizationUserTypeService.delete(rganizationUserType)>0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

}
