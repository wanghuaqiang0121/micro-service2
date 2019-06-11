package org.service.task.delay.sender.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.service.redis.cache.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.service.task.delay.config.CacheConfig;
import org.service.task.delay.entity.service.ServiceTimeout;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月3日
 * @description: ServiceTimeoutSender
 */
@Component
public class ServiceTimeoutSender {

	@Autowired
	@Qualifier(value="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	private RedisCacheManager manager;

	public void send(ServiceTimeout serviceTimeout) {
		String key = CacheConfig.SERVICE_TIMEOUT_PREFIX + UUID.randomUUID();
		redisTemplate.opsForValue().set(key, "", serviceTimeout.getTime(), TimeUnit.MILLISECONDS);
		manager.getCache(CacheConfig.SERVICE_TIMEOUT_CACHE).put(key, serviceTimeout);
	}
}