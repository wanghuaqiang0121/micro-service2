package org.service.task.delay;

import org.mybatis.spring.annotation.MapperScan;
import org.service.task.delay.lock.CacheKeyGenerator;
import org.service.task.delay.lock.LockKeyGenerator;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("org.service.task.delay.dao.**")
public class TaskDelayApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TaskDelayApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Bean
	public CacheKeyGenerator cacheKeyGenerator() {
		return new LockKeyGenerator();
	}
}
