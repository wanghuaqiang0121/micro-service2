package org.wechat.module.service.task;

import org.service.core.api.JsonApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wechat.module.service.domain.inquire.ServiceTimeout;

@FeignClient(value = "${service.task.delay}",fallback = AppointmentServiceFallback.class)
public interface IAppointmentService {

	@RequestMapping(value = { "/service/timeout/send" }, method = RequestMethod.POST)
	JsonApi sendTimeout(@RequestBody ServiceTimeout serviceTimeout);
}
