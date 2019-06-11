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
import org.web.module.base.domain.data.base.ServiceType;
import org.web.module.base.global.BaseGlobalEnum;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.data.base.ServiceTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月15日
 * @description: ServiceTypeController
 */
@RestController
public class ServiceTypeController {

	@Resource
	private ServiceTypeService serviceTypeService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param serviceType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 服务类型列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service-type:get-list" })
	@GetMapping(value = { "/service/types" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) ServiceType serviceType, BindingResult result) {
		Page<?> page = PageHelper.startPage(serviceType.getPage(), serviceType.getPageSize());
		List<Map<String, Object>> serviceTypeList = serviceTypeService.getList(serviceType);
		if (serviceTypeList != null && !serviceTypeList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), serviceTypeList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param serviceType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增服务类型
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service-type:insert" })
	@PostMapping(value = { "/service/type" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody ServiceType serviceType, BindingResult result) {
		/* 检查数据是否存在 */
		Map<String, Object> serviceTypeRepeatMap = serviceTypeService.getRepeat(serviceType);
		if (serviceTypeRepeatMap != null && !serviceTypeRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("service.type.code.is.repeat"));
		}
		/* 设置创建时间和默认状态 */
		serviceType.setCreateDate(new Date());
		serviceType.setStatus(BaseGlobalEnum.ServiceTypeStatus.ENABLE.getValue());
		/* 新增服务类型 */
		if (serviceTypeService.insert(serviceType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月15日
	 * @param id
	 * @param serviceType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改服务类型
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service-type:update" })
	@PutMapping(value = { "/service/type/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody ServiceType serviceType, BindingResult result) {
		serviceType.setId(id);
		/* 检查数据是否存在 */
		Map<String, Object> serviceTypeMap = serviceTypeService.getOne(serviceType);
		if (serviceTypeMap == null || serviceTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 检查数据是否重复 */
		Map<String, Object> serviceTypeRepeatMap = serviceTypeService.getRepeat(serviceType);
		if (serviceTypeRepeatMap != null && !serviceTypeRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("service.type.code.is.repeat"));
		}
		/* 修改服务类型 */
		if (serviceTypeService.update(serviceType) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月15日
	 * @param id
	 * @param serviceType
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 服务类型详情
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service-type:get-detail" })
	@GetMapping(value = { "/service/type/{id}" })
	public JsonApi getDetail(@PathVariable("id") Integer id, @Validated({ BaseEntity.SelectOne.class }) ServiceType serviceType, BindingResult result) {
		serviceType.setId(id);
		/* 数据是否存在 */
		Map<String, Object> serviceTypeMap = serviceTypeService.getOne(serviceType);
		if (serviceTypeMap != null && !serviceTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, serviceTypeMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
