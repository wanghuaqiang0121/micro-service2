package  org.wechat.module.user.rabbit;

import java.util.Map;

import org.service.core.api.JsonApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(value = "${micro.service.rabbit.producer}")
public interface IUserRabbitService {

	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @param param
	 * @return
	 * @description:  更新用户团队关系
	 */
	@RequestMapping(value = { "/user/team/relation" }, method = RequestMethod.POST)
	JsonApi userRabbit(@RequestBody Map<String, Object> param);
}
