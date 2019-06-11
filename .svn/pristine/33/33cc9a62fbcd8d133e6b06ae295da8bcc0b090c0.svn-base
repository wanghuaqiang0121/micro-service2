package org.web.module.service.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.service.redis.cache.RedisCacheConfiguration;
import org.service.redis.cache.RedisCacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.web.module.service.global.BaseGlobal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: Redis配置
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年8月31日
	 * @param factory
	 * @return {@link RedisCacheManager}
	 * @description: 配置RedisCacheManager
	 */
	@Bean
	public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
		/* redis序列化设置 */
		Jackson2JsonRedisSerializer<?> jsonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jsonSerializer.setObjectMapper(om);

		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		config = config.entryTtl(Duration.ofMinutes(30))/* 设置默认过期时间 */
				.disableCachingNullValues()/* 不缓存空值 */
				.serializeValuesWith(SerializationPair.fromSerializer(jsonSerializer));/* 设置序列化方式 */

		/* 设置一个初始化的缓存空间set集合 */
		Set<String> cacheNames = new HashSet<>();
		cacheNames.add(BaseGlobal.CACHE_ORGANIZATION_USER);

		/* 对每个缓存空间应用不同的配置 */
		Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
		configMap.put(BaseGlobal.CACHE_ORGANIZATION_USER, config);

		RedisCacheManager cacheManager = RedisCacheManager.builder(factory) /* 使用自定义的缓存配置初始化一个cacheManager */
				.initialCacheNames(cacheNames) /* 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置 */
				.withInitialCacheConfigurations(configMap).build();
		return cacheManager;
	}
}