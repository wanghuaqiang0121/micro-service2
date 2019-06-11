package org.wechat.module.service.task;

import org.service.core.api.JsonApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wechat.module.service.domain.inquire.ServiceTimeout;

@FeignClient(value = "${service.task.delay}",fallback = InquiryServiceFallback.class)
public interface IInquiryService {
	
	@PostMapping(value = { "/service/timeout/send" })
	public JsonApi sendTimeout(@RequestBody ServiceTimeout serviceTimeout);
}