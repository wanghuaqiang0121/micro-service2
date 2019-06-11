package org.web.module.height.obesity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.web.module.height.obesity.intercept.UserSecurityInterceptor;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年8月31日
 * @description: 拦截器配置
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
	@Bean
	public UserSecurityInterceptor getUserSecurityInterceptor() {
		return new UserSecurityInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(getUserSecurityInterceptor()).addPathPatterns("/**");
	}
}