package org.service.task.delay.controller;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.entity.BaseEntity.Insert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.service.task.delay.entity.user.UserRelation;
import org.service.task.delay.sender.user.UserRelationSender;

@RestController
@RequestMapping(value = { "/user" })
public class UserRelationRabbitController {

	@Resource
	private UserRelationSender userRelationSender;

	/**
	 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
	 * @param param
	 * @date 2018年3月27日
	 * @version 1.0
	 * @description 更新用户团队关系
	 */
	@RequestMapping(value = { "/team/relation" }, method = RequestMethod.POST)
	public JsonApi updateRelation(@RequestBody @Validated({ Insert.class }) UserRelation relation, BindingResult result) {
		userRelationSender.send(relation);
		return new JsonApi(ApiCodeEnum.OK);
	}
}