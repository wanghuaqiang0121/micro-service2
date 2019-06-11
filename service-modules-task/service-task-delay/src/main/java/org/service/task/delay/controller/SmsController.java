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
import org.service.task.delay.entity.sms.Sms;
import org.service.task.delay.sender.sms.SmsSender;
import org.service.task.delay.service.SmsService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月3日
 * @description: 生产者
 */
@RestController
@RequestMapping(value = { "/sms" })
public class SmsController {

	@Resource
	private SmsSender smsSender;
	@Resource
	private SmsService smsService;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年9月21日
	 * @param sms
	 * @return {@link JsonApi}
	 * @description: 发送短信
	 */
	@PostMapping(value = { "/send" })
	public JsonApi sendPush(@RequestBody @Validated({ Insert.class }) Sms sms, BindingResult result) {
		if (smsService.insert(sms) == sms.getRecipients().size()) {
			/* 锁定短信次数 */
			smsService.lock(sms);
			smsSender.send(sms);
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
