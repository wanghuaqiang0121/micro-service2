package org.service.task.delay.receiver.wechat;

import java.util.Map;

import javax.annotation.Resource;

import org.service.redis.cache.RedisCacheManager;
import org.service.redis.lock.LockKey;
import org.service.redis.lock.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.service.task.delay.config.CacheConfig;
import org.service.task.delay.entity.wechat.WechatTemplate;
import org.service.task.delay.receiver.ReceiverStrategy;

import com.alibaba.fastjson.JSONObject;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: SmsReceiverStrategy
 */
@Component
public class WechatTemplateReceiverStrategy implements ReceiverStrategy {
	private final static Logger log = LoggerFactory.getLogger(WechatTemplateReceiverStrategy.class);
	@Resource
	private RedisCacheManager manager;
	@Resource
	private RestTemplate restTemplate;
	@Value("${wechat.push.url}")
	private String url;

	@Override
	@RedisLock(prefix = CacheConfig.LOCK_PREFIX)
	public void run(@LockKey(name = "key") String key) {
		WechatTemplate template = manager.getCache(CacheConfig.WECHAT_TEMPLATE_CACHE).get(key, WechatTemplate.class);
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> postForEntity = restTemplate.postForObject(url, JSONObject.toJSONString(template), Map.class);
			// 微信推送失败
			if (postForEntity == null || postForEntity.size() <= 0) {
				log.error("send wechat msg {} to {} fail connect fail", JSONObject.toJSONString(template), url);
			} else {
				String errmsg = (String) postForEntity.get("errmsg");
				// 微信返回结果不是ok
				if (!"ok".equals(errmsg)) {
					log.warn("send wechat msg {} to {} fail response msg {}", JSONObject.toJSONString(template), url, errmsg);
				}
			}
		} catch (Exception e) {
			log.error("send wechat msg {} to {} fail error msg {}", JSONObject.toJSONString(template), url, e.getMessage());
		} finally {
			manager.getCache(CacheConfig.WECHAT_TEMPLATE_CACHE).evict(key);
		}
	}
}