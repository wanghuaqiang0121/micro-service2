package org.wechat.module.organization.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("org.wechat.module.organization.user.dao.**")
public class OrganizationUserApplication
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(OrganizationUserApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
    }
}
