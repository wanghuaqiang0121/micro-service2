package org.service.redis.lock;

import java.lang.annotation.*;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月21日
 * @description: 缓存参数
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LockKey {
	/**
	 * @author: LiuGangQiang
	 * @date: 2018年11月21日
	 * @return {@link String}
	 * @description: 参数名
	 */
	String name() default "";
}