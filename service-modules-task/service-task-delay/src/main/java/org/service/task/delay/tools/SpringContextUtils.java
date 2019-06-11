package org.service.task.delay.tools;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月18日
 * @description: SpringContextUtils
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringContextUtils.applicationContext == null) {
			SpringContextUtils.applicationContext = applicationContext;
		}
	}

	/**
	 * @apiNote 获取applicationContext
	 * @author hongsir 2017/11/3 19:40:00
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @apiNote 通过name获取 Bean.
	 * @author hongsir 2017/11/3 19:39:00
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	/**
	 * @apiNote 通过class获取Bean.
	 * @author hongsir 2017/11/3 19:39:00
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * @apiNote 通过name, 以及Clazz返回指定的Bean
	 * @author hongsir 2017/11/3 19:39:00
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
}
