package org.wechat.module.height.obesity.service.feign;

import org.service.core.api.JsonApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${module.evaluation}", fallback = EvaluationServiceFallback.class)
public interface IEvaluationService {
	@RequestMapping(value = { "/wechat/evaluation/highrisk" }, method = RequestMethod.GET)
	//@GetMapping(value = { "/wechat/evaluation/highrisk" },consumes = "application/json")
	public JsonApi getEvaluationHighriskWechat(@RequestParam("userId") Integer userId) ;
}
