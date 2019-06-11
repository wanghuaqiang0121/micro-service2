package org.service.task.delay.receiver.sms;

import javax.annotation.Resource;

import org.service.redis.cache.RedisCacheManager;
import org.service.redis.lock.LockKey;
import org.service.redis.lock.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.service.task.delay.config.CacheConfig;
import org.service.task.delay.entity.sms.Sms;
import org.service.task.delay.receiver.ReceiverStrategy;
import org.service.task.delay.service.SmsService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: SmsReceiverStrategy
 */
@Component
public class SmsReceiverStrategy implements ReceiverStrategy {
	private final static Logger log = LoggerFactory.getLogger(SmsReceiverStrategy.class);
	@Resource
	private RedisCacheManager manager;
	@Resource
	private SmsService smsService;

	@Override
	@RedisLock(prefix = CacheConfig.LOCK_PREFIX)
	public void run(@LockKey(name = "key") String key) {
		Sms sms = manager.getCache(CacheConfig.SMS_CACHE).get(key, Sms.class);
		/* 发送短信 */
		sms = Send.SMS(sms);
		if (sms.getStatus().equalsIgnoreCase("Success")) {
			/* 批量修改短信状态 */
			smsService.update(sms);
			log.info("send sms success sms info <{}>", sms);
		} else {
			log.warn("send sms fail sms info <{}>", sms);
			/* 失败则返还次数给机构 */
			if (smsService.update(sms) > 0) {
				smsService.restore(sms);
			}
		}
		manager.getCache(CacheConfig.SMS_CACHE).evict(key);
	}
}