package org.web.module.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("org.web.module.service.dao.**")
public class ServiceApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(ServiceApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
    }
}
