package org.service.task.delay.receiver;

import org.service.redis.lock.LockKey;
import org.service.redis.lock.RedisLock;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月20日
 * @description: ReceiverStrategy
 */
public interface ReceiverStrategy {
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月20日
	 * @description: 执行方法
	 */
	@RedisLock(prefix = "lock")
	public void run(@LockKey(name = "key") String key);
}