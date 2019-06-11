package org.service.task.delay.receiver.service;

import java.util.Map;

import javax.annotation.Resource;

import org.service.redis.cache.RedisCacheManager;
import org.service.redis.lock.LockKey;
import org.service.redis.lock.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.service.task.delay.config.CacheConfig;
import org.service.task.delay.entity.service.ServiceTimeout;
import org.service.task.delay.receiver.ReceiverStrategy;
import org.service.task.delay.service.ServiceTimeoutService;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月18日
 * @description: 短信消息消费者
 */
@Component
public class ServiceTimeoutReceiverStrategy implements ReceiverStrategy {
	private final static Logger log = LoggerFactory.getLogger(ServiceTimeoutReceiverStrategy.class);
	@Resource
	private RedisCacheManager manager;
	@Resource
	private ServiceTimeoutService serviceTimeoutService;

	@Override
	@Transactional
	@RedisLock(prefix = CacheConfig.LOCK_PREFIX)
	public void run(@LockKey(name = "key") String key) {
		ServiceTimeout serviceTimeout = manager.getCache(CacheConfig.SERVICE_TIMEOUT_CACHE).get(key, ServiceTimeout.class);
		/* 判断类型 */
		switch (serviceTimeout.getType()) {
		case APPOINTMENT:
			/* 获取预约详情 判断状态 */
			Map<String, Object> appointmentMap = serviceTimeoutService.getAppointment(serviceTimeout);
			if (appointmentMap != null && appointmentMap.size() > 0) {
				/* 判断预约状态是否为待取号 */
				if (appointmentMap.get("status").equals(1)) {
					/* 修改预约为已取号并扣除用户次数 */
					if (serviceTimeoutService.updateAppointment(serviceTimeout) > 0) {
						/* 扣除用户服务 */
						serviceTimeoutService.deductService(appointmentMap);
						log.info("appointment run success appointment info <{}>", appointmentMap);
					}
				}
			}
			break;
		case INQUIRY:
			/* 获取问询详情 判断状态 */
			Map<String, Object> inquiryMap = serviceTimeoutService.getInquiry(serviceTimeout);
			if (inquiryMap != null && inquiryMap.size() > 0) {
				/* 判断问询是否是未结束 */
				if (!inquiryMap.get("status").equals(4)) {
					/* 关闭问询 */
					serviceTimeoutService.closeInquiry(serviceTimeout);
					log.info("inquiry run success inquiry info <{}>", inquiryMap);
				}
			}
			break;
		}
		manager.getCache(CacheConfig.SERVICE_TIMEOUT_CACHE).evict(key);
	}
}