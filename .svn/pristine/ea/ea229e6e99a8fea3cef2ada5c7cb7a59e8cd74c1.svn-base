package org.service.task.delay.sender.user;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.service.redis.cache.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.service.task.delay.config.CacheConfig;
import org.service.task.delay.entity.user.UserRelation;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: UserRelationSender
 */
@Component
public class UserRelationSender {

	@Autowired
	@Qualifier(value="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	@Resource
	private RedisCacheManager manager;

	public void send(UserRelation relation) {
		String key = CacheConfig.USER_RELATION_PREFIX + UUID.randomUUID();
		redisTemplate.opsForValue().set(key, "", relation.getTime(), TimeUnit.MILLISECONDS);
		manager.getCache(CacheConfig.USER_RELATION_CACHE).put(key, relation);
	}
}