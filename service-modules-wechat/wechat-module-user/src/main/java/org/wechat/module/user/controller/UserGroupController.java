package org.wechat.module.user.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.token.RedisSession;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.user.domain.UserGroup;
import org.wechat.module.user.global.BaseGlobal;
import org.wechat.module.user.message.Prompt;
import org.wechat.module.user.service.UserGroupService;


/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 用户组表
 */
@RestController
public class UserGroupController {

	@Resource
	private UserGroupService userGroupService;
	@Resource
	private RedisCacheManager cacheManager;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param token
	 * @param userGroup
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 新增用户组
	 */
	@RequiresAuthentication(authc = true)
	@PostMapping(value = { "/user/group" })
	public JsonApi insertBaseUserGroup(@RequestHeader(value = BaseGlobal.TOKEN_FLAG) String token,
			@Validated({ BaseEntity.Insert.class }) @RequestBody UserGroup userGroup, BindingResult result) {
		/*从缓存中获取用户*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		/* 登录用户的id*/
		Integer userId = (Integer) session.get(Map.class).get("id");
		/*判断当前用户有没有用户组*/ 
		userGroup.setUserId(userId);
		Map<String, Object> userGroupRepeatMap = userGroupService.getRepeat(userGroup);
		if (userGroupRepeatMap!=null && !userGroupRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.CONFLICT).setMsg(Prompt.bundle("user.group.is.exist"));
		}
		userGroup.setCreateDate(new Date());
		if (userGroupService.insert(userGroup) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param token
	 * @param userGroup
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 查询用户组是否存在
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/user/group" })
	public JsonApi getUserGroupDetails(@RequestHeader(value = BaseGlobal.TOKEN_FLAG) String token,
			@Validated({ BaseEntity.SelectOne.class }) UserGroup userGroup, BindingResult result) {
		/*从缓存中获取用户*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		/*登录用户的id*/ 
		Integer userId = (Integer) session.get(Map.class).get("id");
		userGroup.setUserId(userId);
		Map<String, Object> userGroupRepeatMap = userGroupService.getRepeat(userGroup);
		if (userGroupRepeatMap!=null && !userGroupRepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, userGroupRepeatMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
