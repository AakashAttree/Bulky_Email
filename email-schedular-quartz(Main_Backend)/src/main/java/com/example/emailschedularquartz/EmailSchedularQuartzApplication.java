package com.example.emailschedularquartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example","com.emailschedularquartz"})
public class EmailSchedularQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSchedularQuartzApplication.class, args);
	}

}
