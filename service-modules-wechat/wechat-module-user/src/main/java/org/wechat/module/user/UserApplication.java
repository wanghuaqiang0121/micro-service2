package org.wechat.module.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("org.wechat.module.user.dao.**")
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UserApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}