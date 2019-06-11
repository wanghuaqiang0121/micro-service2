package org.web.module.bone.age.service.feign;

import java.util.Map;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;

public class UserTeamRealationServiceFallback implements IUserTeamRealationService {

	@Override
	public JsonApi userRabbit(Map<String, Object> param) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

}
