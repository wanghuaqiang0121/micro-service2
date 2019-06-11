package org.service.task.delay.controller;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.entity.BaseEntity.Insert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.service.task.delay.entity.service.ServiceTimeout;
import org.service.task.delay.sender.service.ServiceTimeoutSender;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月3日
 * @description: 生产者
 */
@RestController
@RequestMapping(value = { "/service" })
public class ServiceTimeoutController {

	@Resource
	private ServiceTimeoutSender serviceTimeoutSender;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年9月21日
	 * @return {@link JsonApi}
	 * @description: 新增超时
	 */
	@PostMapping(value = { "/timeout/send" })
	public JsonApi sendTimeout(@RequestBody @Validated({ Insert.class }) ServiceTimeout serviceTimeout, BindingResult result) {
		serviceTimeoutSender.send(serviceTimeout);
		return new JsonApi(ApiCodeEnum.OK);
	}
}
