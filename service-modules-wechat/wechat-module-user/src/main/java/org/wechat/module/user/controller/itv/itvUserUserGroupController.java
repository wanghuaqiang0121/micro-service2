package org.wechat.module.user.controller.itv;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.redis.cache.RedisCacheManager;
import org.service.redis.token.RedisSession;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * @date: 2018年10月25日
 * @description: itv用户组
 */
@RestController
public class itvUserUserGroupController {
	
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
	 * @return
	 * @description: 管理者的成员列表
	 */
	@RequiresAuthentication(authc = true)
	@RequestMapping(value = { "/itv/user/group/users" }, method = RequestMethod.GET)
	public JsonApi getGroupUserList(@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@Validated(UserUserGroup.ItvUsersByGroup.class) UserUserGroup userUserGroup, BindingResult result) {
		/*从缓存中获取用户*/ 
		RedisSession session = cacheManager.getSession(BaseGlobal.CACHE_USER, token);
		int userId=Integer.parseInt(session.get(Map.class).get("id").toString());
		/* 用户ID*/
		userUserGroup.setUserId(userId);
		/* 查询用户组是否存在*/
		UserGroup userGroup = new UserGroup();
		userGroup.setUserId(userUserGroup.getUserId());
		Map<String, Object> userGrouprepeatRestltMap = userGroupService.getRepeat(userGroup);
		if (userGrouprepeatRestltMap==null || userGrouprepeatRestltMap.isEmpty()) {
			return new JsonApi(ApiCodeEnum.NOT_FOUND);
		}
		Integer userGroupId = (Integer) userGrouprepeatRestltMap.get("id");
		userUserGroup.setUserId(null);
		userUserGroup.setUserGroupId(userGroupId);
		Integer[] trusts = { UserStatusCode.UserUserGroup.TRUSTEESHIP.getValue(),
				UserStatusCode.UserUserGroup.UNTRUSTEESHIP.getValue() };
		userUserGroup.setTrusts(trusts);
		Page<?> page = PageHelper.startPage(userUserGroup.getPage(), userUserGroup.getPageSize());
		/*用户组成员列表*/ 
		List<Map<String, Object>> usersByGroupResultList = userUserGroupService.getList(userUserGroup);
		if (usersByGroupResultList!=null && !usersByGroupResultList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), usersByGroupResultList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
