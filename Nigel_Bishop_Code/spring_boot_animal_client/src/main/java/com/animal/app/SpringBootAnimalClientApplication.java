package com.animal.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableEurekaClient

// UPDATE
@RestController
public class SpringBootAnimalClientApplication {

	// UPDATE
	@Autowired
	@Lazy
	private EurekaClient client;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Value("${server.port}")
	private String port;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAnimalClientApplication.class, args);
	}
	
	//UPDATE
	@RequestMapping("/hello")
	public String helloMethod() {
		System.out.println("Request received from port number "+port);
		return "Hello from application name -"+client.getApplication(appName).getName()+" which is running on port number "+port; 
	}
}
