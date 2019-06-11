package org.wechat.module.height.obesity.service.feign;

import org.service.core.api.JsonApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wechat.module.height.obesity.global.BaseGlobal;

@FeignClient(value = "${module.user}", fallback = UserServiceFallback.class)
public interface IUserService {

	/** 
	 * @author: WangHuaQiang
	 * @date: 2018年12月3日
	 * @param authenticationToken
	 * @return
	 * @description: 根据token获取用户id
	 */
	@RequestMapping(value = { "/user/info/by/token" }, method = RequestMethod.GET)
	JsonApi getSession(@RequestParam("cacheName") String cacheName, @RequestParam("key") String key,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token);
	
	
}
