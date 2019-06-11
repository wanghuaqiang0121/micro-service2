package org.wechat.module.team;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("org.wechat.module.team.dao.**")
public class OrganizationTeamApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(OrganizationTeamApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
    }
}
