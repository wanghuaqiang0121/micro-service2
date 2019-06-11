package org.wechat.module.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.user.domain.UserService;
import org.wechat.module.user.global.Service.BusinessType;
import org.wechat.module.user.service.UserServiceService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 用户服务
 */
@RestController
public class UserServiceController {
	@Resource
	private UserServiceService userServiceService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param userService
	 * @param result
	 * @return {@link JsonApi}
	 * @description:  用户预约服务列表
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/user/appointment/services" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class }) UserService userService,BindingResult result) {
		userService.setBusinessCode(BusinessType.APPOINTMENTTYPE.getValue());
		Page<?> page = PageHelper.startPage(userService.getPage(), userService.getPageSize());
		List<Map<String, Object>> userServiceServiceList = userServiceService.getAppointmentServiceList(userService);
		if (userServiceServiceList != null && userServiceServiceList.size() > 0) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), userServiceServiceList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
