package org.web.module.organization.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.Organization;
import org.web.module.organization.domain.OrganizationServiceType;
import org.web.module.organization.domain.service.packages.ServiceType;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.OrganizationService;
import org.web.module.organization.service.service.packages.OrganizationServiceTypeService;
import org.web.module.organization.service.service.packages.ServiceTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 机构和服务类型关联
 */
@RestController
public class OrganizationServiceTypeRelationController {

	@Resource
	private OrganizationServiceTypeService organizationServiceTypeService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private ServiceTypeService serviceTypeService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationServiceType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 机构服务类型列表
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-service-type:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/service/type" })
	public JsonApi getList(
			@Validated({ BaseEntity.SelectAll.class }) OrganizationServiceType organizationServiceType, BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationServiceType.getPage(), organizationServiceType.getPageSize());
		List<Map<String, Object>> organizationServiceTypeList = organizationServiceTypeService.getList(organizationServiceType);
		if (organizationServiceTypeList != null && !organizationServiceTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationServiceTypeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationid
	 * @param organizationServiceType
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  本机构没有的服务类型
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-service-type:get-is-null-service-types" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/is/null/service/type/{organizationid}" })
	public JsonApi getIsNullServiceTypes(
			@PathVariable("organizationid")Integer organizationid,
			@Validated({ BaseEntity.SelectAll.class }) OrganizationServiceType organizationServiceType, BindingResult result
			) {
		organizationServiceType.setOrganizationId(organizationid);
		Page<?> page = PageHelper.startPage(organizationServiceType.getPage(), organizationServiceType.getPageSize());
		List<Map<String, Object>> organizationServiceTypeList = organizationServiceTypeService.getOrganizationIsNullServiceTypeList(organizationServiceType);
		if (organizationServiceTypeList != null && !organizationServiceTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationServiceTypeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationid
	 * @param serviceTypeId
	 * @param organizationServiceType
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  删除机构服务类型关联
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-service-type:delete" },level=Level.OPERATION)
	@DeleteMapping(value = { "/organization/{organizationid}/service/type/{serviceTypeId}" })
	public JsonApi delete(
			@PathVariable("organizationid")Integer organizationid,
			@PathVariable("serviceTypeId")Integer serviceTypeId,
			@Validated({ BaseEntity.Delete.class }) OrganizationServiceType organizationServiceType, BindingResult result) {
		organizationServiceType.setOrganizationId(organizationid);
		organizationServiceType.setServiceTypeId(serviceTypeId);
		if (organizationServiceTypeService.delete(organizationServiceType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationServiceType
	 * @param result
	 * @return
	 * @description: 新增机构服务类型
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-service-type:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/service/type" })
	public JsonApi insert(
			@Validated({ BaseEntity.Insert.class })@RequestBody OrganizationServiceType organizationServiceType, BindingResult result) {
		/*判断是否存在*/
		Map<String, Object> organizationServiceTypeMap = organizationServiceTypeService.getRepeat(organizationServiceType);
		if (organizationServiceTypeMap!=null && !organizationServiceTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.service.type.repeat"));
		}
		/*机构是否存在*/
		Organization organization = new Organization();
		organization.setId(organizationServiceType.getOrganizationId());
		Map<String, Object> organizatioMap = organizationService.getOne(organization);
		if (organizatioMap==null || organizatioMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("organization.is.null"));
		}
		/*服务类型是否存在*/
		ServiceType serviceType = new ServiceType();
		serviceType.setId(organizationServiceType.getServiceTypeId());
		Map<String, Object> serviceTypeMap = serviceTypeService.getOne(serviceType);
		if (serviceTypeMap==null || serviceTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("service.type.is.null"));
		}
		/*新增数据*/
		if (organizationServiceTypeService.insert(organizationServiceType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationid
	 * @param organizationServiceType
	 * @param result
	 * @return
	 * @description: 查询机构拥有的服务类型和没有的服务类型
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-service-type:get-have-not-have-service-types" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/service/type/is/choose/service/type/{organizationid}" })
	public JsonApi getHaveNotHaveServiceTypes(
			@PathVariable("organizationid")Integer organizationid,
			@Validated(BaseEntity.SelectAll.class)  OrganizationServiceType organizationServiceType,BindingResult result){
		organizationServiceType.setOrganizationId(organizationid);
		Page<?> page = PageHelper.startPage(organizationServiceType.getPage(), organizationServiceType.getPageSize());
		List<Map<String, Object>>  organizationServiceTypeList=organizationServiceTypeService.getOrganizationServiceTypeIsChoose(organizationServiceType);
		if (organizationServiceTypeList!=null && !organizationServiceTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationServiceTypeList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
