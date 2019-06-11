package org.service.task.delay.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: RedisListenerConfig
 */
@Configuration
public class RedisListenerConfig {
	@Value("${spring.redis.database}")
	private Integer db;

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月20日
	 * @param connectionFactory
	 * @return {@link RedisMessageListenerContainer}
	 * @description: 监听器配置 __keyevent@8__:expired 监听8号数据库的过期事件
	 */
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(new RedisExpiredListener(), new PatternTopic("__keyevent@" + db + "__:expired"));
		return container;
	}
}
