package org.web.module.organization.controller.service.packages;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.service.packages.OrganizationPackageService;
import org.web.module.organization.global.BaseGlobal;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.service.packages.OrganizationPackageServiceService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年10月11日
 * @description: 机构服务包和服务关联表
 */
@RestController
public class OrganizationPackageServiceRelationController {

	@Resource
	private OrganizationPackageServiceService organizationPackageServiceService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationPackageService
	 * @param result
	 * @return{@link JsonApi}
	 * @description: 新增机构服务包和服务关联
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-package-service:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/package/service" })
	public JsonApi insert(
			@Validated(BaseEntity.Insert.class) @RequestBody OrganizationPackageService organizationPackageService,BindingResult result){
		/* 查询数据是否存在重复*/
		Map<String, Object> organizationPackageServiceMap = organizationPackageServiceService.getRepeat(organizationPackageService);
		if (organizationPackageServiceMap!=null && !organizationPackageServiceMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.package.service.is.exists"));
		}
		/*添加机构服务包和服务关联*/ 
		if (organizationPackageServiceService.insert(organizationPackageService) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationPackageService
	 * @param result
	 * @return{@link JsonApi}
	 * @description: 批量新增机构服务包和服务关联
	 */
	@Transactional
	@RequiresAuthentication( value = { "web-module-organization:organization-package-service:batch-insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/package/service/batchInsert" })
	public JsonApi batchInsert(
			@Validated(OrganizationPackageService.BatchInsert.class) @RequestBody OrganizationPackageService organizationPackageService,BindingResult result){
		/* 批量新增服务包内服务项*/
		int rows=organizationPackageServiceService.batchInsert(organizationPackageService.getOrganizationPackageServices());
		if (rows == organizationPackageService.getOrganizationPackageServices().size()) {
			return new JsonApi(ApiCodeEnum.OK);
		}else if (rows != organizationPackageService.getOrganizationPackageServices().size()) {
			throw new RuntimeException();
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	
	}
	
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationPackageService
	 * @param result
	 * @param organizationId
	 * @return{@link JsonApi}
	 * @description: 查询机构服务包没有的服务列表
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-package-service:get-service-null-list" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/package/is/null/service" })
	public JsonApi getServiceNullList(
			@Validated(BaseEntity.SelectAll.class)  OrganizationPackageService organizationPackageService,BindingResult result,
			@RequestHeader(required = true, value = BaseGlobal.ORGANIZATION_ID) Integer organizationId){
		organizationPackageService.setOrganizationId(organizationId);
		Page<?> page = PageHelper.startPage(organizationPackageService.getPage(), organizationPackageService.getPageSize());
		List<Map<String, Object>>  organizationPackageServiceList=organizationPackageServiceService.getOrganizationPackageIsNullService(organizationPackageService);
		if (organizationPackageServiceList!=null && !organizationPackageServiceList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationPackageServiceList));	
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationPackageService
	 * @param result
	 * @return{@link JsonApi}
	 * @description: 删除服务包服务关联(单条)
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-package-service:delete" },level=Level.OPERATION)
	@DeleteMapping(value = { "/organization/package/Service/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id,
			@Validated(BaseEntity.Delete.class) OrganizationPackageService organizationPackageService,BindingResult result){
		/*设置id*/ 
		organizationPackageService.setId(id);
		/* 删除服务包服务关联(单条)*/
		if (organizationPackageServiceService.delete(organizationPackageService) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationPackageService
	 * @param result
	 * @return{@link JsonApi}
	 * @description: 修改服务包服务关联
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-package-service:update" },level=Level.OPERATION)
	@PutMapping(value = { "/organization/package/service/{id}" })
	public JsonApi update(@PathVariable("id") Integer id,
			@Validated(BaseEntity.Update.class) @RequestBody OrganizationPackageService organizationPackageService,BindingResult result){
		/* 设置id*/
		organizationPackageService.setId(id);
		/* 查询数据是否存在*/
		Map<String, Object> organizationPackageServiceMap = organizationPackageServiceService.getOne(organizationPackageService);
		if (organizationPackageServiceMap==null || organizationPackageServiceMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 修改服务包服务关联*/
		if (organizationPackageServiceService.update(organizationPackageService) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
