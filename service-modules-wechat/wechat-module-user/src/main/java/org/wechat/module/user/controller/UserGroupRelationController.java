package org.wechat.module.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.token.RedisSession;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.user.domain.UserGroup;
import org.wechat.module.user.domain.UserUserGroup;
import org.wechat.module.user.global.BaseGlobal;
import org.wechat.module.user.global.UserStatusCode;
import org.wechat.module.user.service.UserGroupService;
import org.wechat.module.user.service.UserUserGroupService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 用户与用户组关联表
 */
@RestController
public class UserGroupRelationController {

	@Resource
	private UserUserGroupService userUserGroupService;
	@Resource
	private UserGroupService userGroupService;
	@Resource
	private RedisCacheManager cacheManager;

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param token
	 * @param userUserGroup
	 * @param result
	 * @return  {@link JsonApi}
	 * @description:  用户组管理者的成员列表
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/user/group/users" })
	public JsonApi getGroupUserList(@RequestHeader(value = BaseGlobal.TOKEN_FLAG) String token,
			@Validated(UserUserGroup.UsersByGroup.class) UserUserGroup userUserGroup, BindingResult result) {
		/*从缓存中获取用户*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		/*登录用户的id*/ 
		Integer operationUserIdId = (Integer) session.get(Map.class).get("id");
		/*查询用户组是否存在*/ 
		UserGroup userGroup = new UserGroup();
		userGroup.setUserId(operationUserIdId);
		Map<String, Object> userGrouprepeatMap = userGroupService.getRepeat(userGroup);
		if (userGrouprepeatMap==null || userGrouprepeatMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		Integer userGroupId = (Integer) userGrouprepeatMap.get("id");
		userUserGroup.setUserId(null);
		userUserGroup.setUserGroupId(userGroupId);
		Integer[] trusts = { UserStatusCode.UserUserGroup.TRUSTEESHIP.getValue(),
				UserStatusCode.UserUserGroup.UNTRUSTEESHIP.getValue() };
		userUserGroup.setTrusts(trusts);
		Page<?> page = PageHelper.startPage(userUserGroup.getPage(), userUserGroup.getPageSize());
		List<Map<String, Object>> usersByGroupList = userUserGroupService.getList(userUserGroup);
		if (usersByGroupList != null && !usersByGroupList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), usersByGroupList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param id
	 * @param userUserGroup
	 * @param result
	 * @return  {@link JsonApi}
	 * @description: 移除成员
	 */
	@RequiresAuthentication(authc = true)
	@DeleteMapping(value = { "/user/user/group/{id}" })
	public JsonApi deleteGroupMember(@PathVariable("id") Integer id,
			@Validated(BaseEntity.Delete.class) UserUserGroup userUserGroup, 
			BindingResult result) {
		userUserGroup.setId(id);
		if (userUserGroupService.delete(userUserGroup) > 0) {
			return new JsonApi(ApiCodeEnum.OK);
		}
		return new JsonApi(ApiCodeEnum.FAIL);
	}
}
