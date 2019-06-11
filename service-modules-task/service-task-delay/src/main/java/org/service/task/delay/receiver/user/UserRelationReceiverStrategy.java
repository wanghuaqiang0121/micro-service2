package org.service.task.delay.receiver.user;

import java.util.Map;

import javax.annotation.Resource;

import org.service.redis.cache.RedisCacheManager;
import org.service.redis.lock.LockKey;
import org.service.redis.lock.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.service.task.delay.config.CacheConfig;
import org.service.task.delay.entity.user.UserRelation;
import org.service.task.delay.receiver.ReceiverStrategy;
import org.service.task.delay.service.UserRelationService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: SmsReceiverStrategy
 */
@Component
public class UserRelationReceiverStrategy implements ReceiverStrategy {
	private final static Logger log = LoggerFactory.getLogger(UserRelationReceiverStrategy.class);
	@Resource
	private RedisCacheManager manager;
	@Resource
	private UserRelationService userRelationService;

	@Override
	@RedisLock(prefix = CacheConfig.LOCK_PREFIX)
	public void run(@LockKey(name = "key") String key) {
		UserRelation relation = manager.getCache(CacheConfig.USER_RELATION_CACHE).get(key, UserRelation.class);
		Map<String, Object> relationMap = userRelationService.getOne(relation);
		/* 存在则修改 */
		if (relationMap != null && !relationMap.isEmpty()) {
			if (userRelationService.update(relation) > 0) {
				log.info("update user relation success userid<{}> teamid<{}>", relation.getUserId(), relation.getOrganizationTeamId());
			} else {
				log.warn("update user relation fail userid<{}> teamid<{}>", relation.getUserId(), relation.getOrganizationTeamId());
			}
		} else {
			if (userRelationService.insert(relation) > 0) {
				log.info("insert user relation success userid<{}> teamid<{}>", relation.getUserId(), relation.getOrganizationTeamId());
			} else {
				log.warn("insert user relation fail userid<{}> teamid<{}>", relation.getUserId(), relation.getOrganizationTeamId());
			}
		}
		manager.getCache(CacheConfig.USER_RELATION_CACHE).evict(key);
	}
}