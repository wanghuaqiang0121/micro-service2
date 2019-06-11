package org.service.task.delay.lock;

import org.aspectj.lang.ProceedingJoinPoint;

public interface CacheKeyGenerator {

	String getLockKey(ProceedingJoinPoint pjp);
}