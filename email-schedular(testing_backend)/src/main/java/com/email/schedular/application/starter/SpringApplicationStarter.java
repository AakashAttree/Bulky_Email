package com.email.schedular.application.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.email")
@EnableJpaRepositories(basePackages = "com.email.schedular" )
@EntityScan(basePackages = "com.email.schedular" )
public class SpringApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationStarter.class, args);

	}

}
