package org.web.module.base.controller.data.base;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web.module.base.domain.data.base.ServicePackageType;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.data.base.ServicePackageTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月15日
 * @description: ServicePackageTypeController
 */
@RestController
public class ServicePackageTypeController {
	@Resource
	private ServicePackageTypeService servicePackageTypeService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param servicePackageType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 服务包类型列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service-package-type:get-list" })
	@GetMapping(value = { "/service/package/types" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) ServicePackageType servicePackageType, BindingResult result) {
		Page<?> page = PageHelper.startPage(servicePackageType.getPage(), servicePackageType.getPageSize());
		List<Map<String, Object>> servicePackageTypeList = servicePackageTypeService.getList(servicePackageType);
		if (servicePackageTypeList != null && !servicePackageTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), servicePackageTypeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param servicePackageType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增服务包类型
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service-package-type:insert" })
	@PostMapping(value = { "/service/package/type" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody ServicePackageType servicePackageType, BindingResult result) {
		/* 检查数据是否存在 */
		Map<String, Object> servicePackageTypeMap = servicePackageTypeService.getRepeat(servicePackageType);
		if (servicePackageTypeMap != null && !servicePackageTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("service.package.type.code.is.exists"));
		}
		/* 设置创建时间 */
		servicePackageType.setCreateDate(new Date());
		if (servicePackageTypeService.insert(servicePackageType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param servicePackageType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改服务包类型
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service-package-type:update" })
	@PutMapping(value = { "/service/package/type/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody ServicePackageType servicePackageType, BindingResult result) {
		servicePackageType.setId(id);
		/* 检查数据是否存在 */
		Map<String, Object> servicePackageTypeMap = servicePackageTypeService.getOne(servicePackageType);
		if (servicePackageTypeMap == null || servicePackageTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 检查数据是否重复 */
		Map<String, Object> servicePackageTypeRepeatMap = servicePackageTypeService.getRepeat(servicePackageType);
		if (servicePackageTypeRepeatMap != null && !servicePackageTypeRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("service.package.type.code.is.exists"));
		}
		/* 修改机构服务包 */
		if (servicePackageTypeService.update(servicePackageType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param id
	 * @param servicePackageType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 服务包类型详情
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service-package-type:get-detail" })
	@GetMapping(value = { "/service/package/type/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) ServicePackageType servicePackageType, BindingResult result) {
		servicePackageType.setId(id);
		/* 查询数据 */
		Map<String, Object> servicePackageTypeMap = servicePackageTypeService.getOne(servicePackageType);
		if (servicePackageTypeMap != null && !servicePackageTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, servicePackageTypeMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
