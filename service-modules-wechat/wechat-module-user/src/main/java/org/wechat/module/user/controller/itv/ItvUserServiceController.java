package org.wechat.module.user.controller.itv;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.user.domain.UserService;
import org.wechat.module.user.global.BaseGlobal;
import org.wechat.module.user.global.Service;
import org.wechat.module.user.service.UserServiceService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: itv用户服务
 */
@RestController
public class ItvUserServiceController {

	@Resource
	private UserServiceService userServiceService;
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param token
	 * @param userService
	 * @param result
	 * @return
	 * @description: 用户视频服务列表
	 */
	@RequiresAuthentication(ignore = true)
	@GetMapping(value = { "/user/services" })
	public JsonApi getUserServices(@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token,
			@Validated(BaseEntity.SelectAll.class) UserService userService, BindingResult result) {
		userService.setServiceTypeCode(Service.ServiceType.VIDEOCONSULTATION.getValue());
		Page<?> page = PageHelper.startPage(userService.getPage(), userService.getPageSize());
		/*用户服务列表*/ 
		List<Map<String, Object>> usersResult = userServiceService.getList(userService);
		if (usersResult!=null && !usersResult.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), usersResult));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
	}
}
