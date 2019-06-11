package org.wechat.module.height.obesity.service.feign;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements IUserService {

	@Override
	public JsonApi getSession(String cacheName, String key,String token) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

	
}
