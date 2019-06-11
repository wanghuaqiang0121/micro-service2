package org.service.task.delay.sender.sms;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.service.redis.cache.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.service.task.delay.config.CacheConfig;
import org.service.task.delay.entity.sms.Sms;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月3日
 * @description: SmsSender
 */
@Component
public class SmsSender {

	@Autowired
	@Qualifier(value="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	private RedisCacheManager manager;

	public void send(Sms sms) {
		String key = CacheConfig.SMS_PREFIX + UUID.randomUUID();
		redisTemplate.opsForValue().set(key, "", sms.getTime(), TimeUnit.MILLISECONDS);
		manager.getCache(CacheConfig.SMS_CACHE).put(key, sms);
	}
}