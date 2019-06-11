package org.service.task.delay.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.service.redis.lock.RedisLock;
import org.service.task.delay.lock.CacheKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * redis 方案
 */
@Aspect
@Component
public class LockMethodInterceptor {

	@Autowired
	public LockMethodInterceptor(StringRedisTemplate lockRedisTemplate, CacheKeyGenerator cacheKeyGenerator) {
		this.lockRedisTemplate = lockRedisTemplate;
		this.cacheKeyGenerator = cacheKeyGenerator;
	}

	private final StringRedisTemplate lockRedisTemplate;
	private final CacheKeyGenerator cacheKeyGenerator;

	@Around("execution(public * *(..)) && @annotation(org.service.redis.lock.RedisLock)")
	public Object interceptor(ProceedingJoinPoint pjp) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		RedisLock lock = method.getAnnotation(RedisLock.class);
		if (StringUtils.isEmpty(lock.prefix())) {
			throw new RuntimeException("lock key can't be null...");
		}
		final String lockKey = cacheKeyGenerator.getLockKey(pjp);
		try {
			// key不存在才能设置成功
			final Boolean success = lockRedisTemplate.opsForValue().setIfAbsent(lockKey, "");
			if (success) {
				lockRedisTemplate.expire(lockKey, lock.expire(), lock.timeUnit());
			} else {
				return null;
			}
			return pjp.proceed();
		} catch (Throwable e) {
			throw new RuntimeException("redis expired event error");
		} finally {
			// 如果演示的话需要注释该代码;实际应该放开
			lockRedisTemplate.delete(lockKey);
		}
	}
}