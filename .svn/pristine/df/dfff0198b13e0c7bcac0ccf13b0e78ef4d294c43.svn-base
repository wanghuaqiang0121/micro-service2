package org.wechat.module.height.obesity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("org.wechat.module.height.obesity.dao**")
public class HeightobesityApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(HeightobesityApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
