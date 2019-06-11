package org.web.module.organization;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("org.web.module.organization.dao.**")
public class OrganizationApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(OrganizationApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
    }
}
