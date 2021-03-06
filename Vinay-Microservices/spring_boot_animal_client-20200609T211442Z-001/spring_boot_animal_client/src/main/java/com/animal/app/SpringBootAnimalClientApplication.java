package com.animal.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringBootAnimalClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAnimalClientApplication.class, args);
	}

}
