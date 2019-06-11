package org.wechat.module.service.task;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.springframework.stereotype.Component;
import org.wechat.module.service.domain.bespeak.WechatApi;
@Component
public class WeChatServiceFallback implements IWeChatService {

	@Override
	public JsonApi insertWechatNews(WechatApi wechatApi) {
		return new JsonApi(ApiCodeEnum.TIMEOUT);
	}

}
