package org.web.module.team;

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
@MapperScan("org.web.module.team.dao.**")
public class TeamApplication
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(TeamApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
    }
}
