package com.aaa.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.aaa.model") // scan entity folder
@EnableJpaRepositories("com.aaa.repository") // scan repository folder
@ComponentScan({"com.aaa.application,com.aaa.ctrl" }) // scan controller,config folder
																		
public class AppConfig extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(AppConfig.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppConfig.class, args);
	}
}
