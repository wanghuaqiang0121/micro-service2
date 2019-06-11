package org.service.task.delay.lock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.service.redis.lock.LockKey;
import org.service.redis.lock.RedisLock;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * 通过接口注入的方式去写不同的生成规则;
 */
public class LockKeyGenerator implements CacheKeyGenerator {

	@Override
	public String getLockKey(ProceedingJoinPoint pjp) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		RedisLock redisLock = method.getAnnotation(RedisLock.class);
		final Object[] args = pjp.getArgs();
		final Parameter[] parameters = method.getParameters();
		StringBuilder builder = new StringBuilder();
		// 默认解析方法里面带 CacheParam 注解的属性,如果没有尝试着解析实体对象中的
		for (int i = 0; i < parameters.length; i++) {
			final LockKey lockKey = parameters[i].getAnnotation(LockKey.class);
			if (lockKey == null) {
				continue;
			}
			builder.append(redisLock.delimiter()).append(args[i]);
		}
		if (StringUtils.isEmpty(builder.toString())) {
			final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			for (int i = 0; i < parameterAnnotations.length; i++) {
				final Object object = args[i];
				final Field[] fields = object.getClass().getDeclaredFields();
				for (Field field : fields) {
					final LockKey lockKey = field.getAnnotation(LockKey.class);
					if (lockKey == null) {
						continue;
					}
					field.setAccessible(true);
					builder.append(redisLock.delimiter()).append(ReflectionUtils.getField(field, object));
				}
			}
		}
		return redisLock.prefix() + builder.toString();
	}
}