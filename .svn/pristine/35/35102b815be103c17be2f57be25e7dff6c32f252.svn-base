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
import org.web.module.base.domain.data.base.Service;
import org.web.module.base.domain.data.base.ServiceType;
import org.web.module.base.message.Prompt;
import org.web.module.base.service.data.base.ServiceService;
import org.web.module.base.service.data.base.ServiceTypeService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月15日
 * @description: 服务
 */
@RestController
public class ServiceController {

	@Resource
	private ServiceService serviceService;
	@Resource
	private ServiceTypeService serviceTypeService;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @param service
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 查询服务列表
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service:get-list" })
	@GetMapping(value = { "/services" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) Service service, BindingResult result) {
		Page<?> page = PageHelper.startPage(service.getPage(), service.getPageSize());
		List<Map<String, Object>> serviceList = serviceService.getList(service);
		if (serviceList != null && !serviceList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), serviceList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月15日
	 * @param service
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 新增服务
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service:insert" })
	@PostMapping(value = { "/service" })
	public JsonApi insert(@Validated({ BaseEntity.Insert.class }) @RequestBody Service service, BindingResult result) {
		/* 查询服务类型是否存在 */
		ServiceType serviceType = new ServiceType();
		serviceType.setId(service.getServiceTypeId());
		Map<String, Object> serviceTypeMap = serviceTypeService.getOne(serviceType);
		if (serviceTypeMap == null || serviceTypeMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("service.type.not.found"));
		}
		/* 查询服务是否重复 */
		Map<String, Object> serviceMap = serviceService.getRepeat(service);
		if (serviceMap != null && !serviceMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("service.exists"));
		}
		/* 设置创建时间 */
		service.setCreateDate(new Date());
		if (serviceService.insert(service) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月15日
	 * @param id
	 * @param service
	 * @param result
	 * @return {@link JsonApi}
	 * @description: 修改服务
	 */
	@RequiresAuthentication(level = Level.OPERATION, value = { "web-module-base:service:update" })
	@PutMapping(value = { "/service/{id}" })
	public JsonApi update(@PathVariable("id") Integer id, @Validated({ BaseEntity.Update.class }) @RequestBody Service service, BindingResult result) {
		/* 查询服务是否存在 */
		service.setId(id);
		Map<String, Object> serviceMap = serviceService.getOne(service);
		if (serviceMap == null || serviceMap.size() <= 0) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		/* 查询服务是否重复 */
		Map<String, Object> serviceRepeatMap = serviceService.getRepeat(service);
		if (serviceRepeatMap != null && !serviceRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("service.is.exists"));
		}
		if (serviceService.update(service) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
