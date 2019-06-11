package org.web.module.organization.controller.service.packages;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.organization.domain.service.packages.OrganizationPackageUserType;
import org.web.module.organization.message.Prompt;
import org.web.module.organization.service.service.packages.OrganizationPackageUserTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月11日
 * @description: 服务包使用人群关联表
 */
@RestController
public class OrganizationPackageUserTypeRelationController {

	@Resource
	private OrganizationPackageUserTypeService organizationPackageUserTypeService;
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationPackageUserType
	 * @param result
	 * @return{@link JsonApi}
	 * @description: 新增服务包使用人群关联
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-package-user-type:insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/package/user/type" })
	public JsonApi insert(
			@Validated(BaseEntity.Insert.class) @RequestBody OrganizationPackageUserType organizationPackageUserType,BindingResult result){
		/*查询数据是否存在重复*/ 
		Map<String, Object> organizationPackageUserTypeMap = organizationPackageUserTypeService.getRepeat(organizationPackageUserType);
		if (organizationPackageUserTypeMap!=null && !organizationPackageUserTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("organization.package.user.type.is.exists"));
		}
		/*添加服务包使用人群关联*/ 
		if (organizationPackageUserTypeService.insert(organizationPackageUserType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationPackageUserType
	 * @param result
	 * @return{@link JsonApi}
	 * @description: 批量新增服务包使用人群关联
	 */
	@Transactional
	@RequiresAuthentication( value = { "web-module-organization:organization-package-user-type:batch-insert" },level=Level.OPERATION)
	@PostMapping(value = { "/organization/package/user/type/batchInsert" })
	public JsonApi batchInsert(
			@Validated(OrganizationPackageUserType.BatchInsert.class) @RequestBody OrganizationPackageUserType organizationPackageUserType,BindingResult result){
		/* 删除服务包所有使用人群关联*/
		OrganizationPackageUserType oPackageUserType = new OrganizationPackageUserType();
		oPackageUserType.setOrganizationServicePackageId(organizationPackageUserType.getOrganizationPackageUserTypes().get(0).getOrganizationServicePackageId());
		organizationPackageUserTypeService.deleteByOrganizationPackageId(oPackageUserType);
		/*批量添加服务包使用人群关联*/ 
		int rows=organizationPackageUserTypeService.batchInsert(organizationPackageUserType.getOrganizationPackageUserTypes());
		if (rows == organizationPackageUserType.getOrganizationPackageUserTypes().size()) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		throw new RuntimeException();
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param id
	 * @param organizationPackageUserType
	 * @param result
	 * @return
	 * @description: 删除服务包使用人群关联
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-package-user-type:delete" },level=Level.OPERATION)
	@DeleteMapping(value = { "/organization/package/user/type/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id,
			@Validated(BaseEntity.Delete.class) OrganizationPackageUserType organizationPackageUserType,BindingResult result){
		/*设置id*/ 
		organizationPackageUserType.setId(id);
		/* 删除服务包使用人群关联*/
		if (organizationPackageUserTypeService.delete(organizationPackageUserType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月11日
	 * @param organizationPackageUserType
	 * @param result
	 * @return
	 * @description: 查询服务包的人群类型列表
	 */
	@RequiresAuthentication( value = { "web-module-organization:organization-package-user-type:get-list" },level=Level.OPERATION)
	@GetMapping(value = { "/organization/package/user/types" })
	public JsonApi getList( @Validated({BaseEntity.SelectAll.class }) OrganizationPackageUserType organizationPackageUserType,
			BindingResult result) {
		/* 设置分页条件 */
		Page<?> page = PageHelper.startPage(organizationPackageUserType.getPage(),organizationPackageUserType.getPageSize());
		/* 查询列表 */
		List<Map<String, Object>> resultList = organizationPackageUserTypeService.getList(organizationPackageUserType);
		if (CollectionUtils.isNotEmpty(resultList)) {
			return new JsonApi(ApiCodeEnum.OK,new MultiLine(page.getTotal(), resultList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
}
