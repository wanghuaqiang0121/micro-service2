package org.web.module.bone.age;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableFeignClients
@MapperScan("org.web.module.bone.age.dao.**")
public class BoneAgeApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(BoneAgeApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
    }
}
