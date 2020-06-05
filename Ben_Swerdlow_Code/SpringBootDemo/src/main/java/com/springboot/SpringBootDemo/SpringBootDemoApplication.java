package com.springboot.SpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// @RestController says this code describes an endpoint and should be made web-available
@RestController
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value="name", defaultValue="World") String name) {
		return String.format("Hello %s!", name);
	}

}
