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
import org.service.task.delay.entity.wechat.WechatTemplate;
import org.service.task.delay.sender.wechat.WechatTemplateSender;

@RestController
@RequestMapping(value = { "/wechat" })
public class WechatTemplateRabbitController {

	@Resource
	private WechatTemplateSender wechatTemplateSender;

	@PostMapping(value = { "/send" })
	public JsonApi insertWechatNews(@RequestBody @Validated({ Insert.class }) WechatTemplate template, BindingResult result) {
		wechatTemplateSender.send(template);
		return new JsonApi(ApiCodeEnum.OK);
	}
}
