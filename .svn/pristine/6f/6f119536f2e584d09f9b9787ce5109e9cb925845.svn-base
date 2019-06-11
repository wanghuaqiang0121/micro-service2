package org.wechat.module.service.task;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.springframework.stereotype.Component;
import org.wechat.module.service.domain.inquire.ServiceTimeout;

@Component
public class InquiryServiceFallback implements IInquiryService{

	@Override
	public JsonApi sendTimeout(ServiceTimeout serviceTimeout) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}
}
