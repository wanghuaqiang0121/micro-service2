package org.wechat.module.height.obesity.service.feign;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.springframework.stereotype.Component;
@Component
public class EvaluationServiceFallback implements IEvaluationService {

	@Override
	public JsonApi getEvaluationHighriskWechat(Integer userId) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}
}
