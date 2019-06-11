package org.service.task.delay.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.service.task.delay.config.CacheConfig;
import org.service.task.delay.receiver.service.ServiceTimeoutReceiverStrategy;
import org.service.task.delay.receiver.sms.SmsReceiverStrategy;
import org.service.task.delay.receiver.user.UserRelationReceiverStrategy;
import org.service.task.delay.receiver.wechat.WechatTemplateReceiverStrategy;
import org.service.task.delay.tools.SpringContextUtils;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: RedisExpiredListener
 */
public class RedisExpiredListener implements MessageListener {
	@Override
	public void onMessage(Message message, byte[] bytes) {
		String key = new String(message.getBody());
		String prefix = key.substring(0, key.lastIndexOf(".") + 1);
		if (prefix.equals(CacheConfig.SERVICE_TIMEOUT_PREFIX)) {
			SpringContextUtils.getBean(ServiceTimeoutReceiverStrategy.class).run(key);
		} else if (prefix.equals(CacheConfig.SMS_PREFIX)) {
			SpringContextUtils.getBean(SmsReceiverStrategy.class).run(key);
		} else if (prefix.equals(CacheConfig.USER_RELATION_PREFIX)) {
			SpringContextUtils.getBean(UserRelationReceiverStrategy.class).run(key);
		} else if (prefix.equals(CacheConfig.WECHAT_TEMPLATE_PREFIX)) {
			SpringContextUtils.getBean(WechatTemplateReceiverStrategy.class).run(key);
		}
	}
}
