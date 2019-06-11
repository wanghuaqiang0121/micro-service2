package org.web.module.organization.controller;

import java.util.HashMap;
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
import org.web.module.organization.domain.OrganizationModule;
import org.web.module.organization.global.BaseGlobalEnum;
import org.web.module.organization.service.OrganizationModuleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description:  机构模块关联表
 */
@RestController
public class OrganizationModuleRelationController {
	
	@Resource
	private OrganizationModuleService organizationModuleService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationModule
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增机构和模块关联
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-module:insert" })
	@PostMapping(value = { "/organization/module" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody OrganizationModule organizationModule,BindingResult result){
		/*判断数据是否重复*/
		/*存在就修改*/ 
		Map<String, Object> organizationModuleMap = organizationModuleService.getRepeat(organizationModule);
		if (organizationModuleMap!=null && !organizationModuleMap.isEmpty()) {
			OrganizationModule organizationModuleNew = new OrganizationModule();
			organizationModuleNew.setId((Integer)organizationModuleMap.get("id"));
			organizationModuleNew.setStatus(BaseGlobalEnum.Organization.ENABLE.getValue());
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("id", (Integer)organizationModuleMap.get("id"));
			if (organizationModuleService.update(organizationModuleNew)>0) {
				return new JsonApi(ApiCodeEnum.OK,resultMap);
			}
		}else {/*不存在就新增*/ 
			organizationModule.setStatus(BaseGlobalEnum.Organization.ENABLE.getValue());
			if (organizationModuleService.insert(organizationModule)>0) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("id", organizationModule.getId());
				return new JsonApi(ApiCodeEnum.OK,resultMap);
			}
		}
		return new JsonApi(ApiCodeEnum.FAIL);
		
	}
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param organizationModule
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构模块列表
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-module:get-list" })
	@GetMapping(value = { "/organization/modules" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class })OrganizationModule organizationModule,BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationModule.getPage(), organizationModule.getPageSize());
		List<Map<String, Object>> organizationModuleList = organizationModuleService.getList(organizationModule);
		if (organizationModuleList != null && !organizationModuleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationModuleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月19日
	 * @param organizationModule
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询机构拥有和未拥有的模块列表
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-module:get-organization-have-not-have-modules" })
	@GetMapping(value = { "/organization/module/isChoose" })
	public JsonApi getOrganizationHaveNotHaveModule(@Validated({OrganizationModule.getOrganizationModuleIsChoose.class})OrganizationModule organizationModule,
			BindingResult result) {
		Page<?> page = PageHelper.startPage(organizationModule.getPage(), organizationModule.getPageSize());
		List<Map<String, Object>> organizationModuleList = organizationModuleService.getOrganizationModuleIsChoose(organizationModule);
		if (organizationModuleList != null && !organizationModuleList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), organizationModuleList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	
	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param id
	 * @param organizationModule
	 * @param result
	 * @return {@link JsonApi}
	 * @date 2018年4月18日
	 * @version 1.0
	 * @description 删除机构和模块关联 逻辑删除
	 */
	@RequiresAuthentication(level=Level.OPERATION, value = { "web-module-organization:organization-module:delete" })
	@PutMapping(value = { "/organization/module/{id}" })
	public JsonApi delete(@PathVariable("id") Integer id,
			@Validated({ BaseEntity.Delete.class })  OrganizationModule organizationModule, BindingResult result) {
		organizationModule.setId(id);
		organizationModule.setStatus(BaseGlobalEnum.Organization.DISABLE.getValue());
		Map<String, Object> organizationModuleOneMap = organizationModuleService.getOne(organizationModule);
		if (MapUtils.isEmpty(organizationModuleOneMap)) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		// 修改数据
		if (organizationModuleService.update(organizationModule) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	

}
