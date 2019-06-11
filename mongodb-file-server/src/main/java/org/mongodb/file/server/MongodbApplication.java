package org.mongodb.file.server;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongodbApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MongodbApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}