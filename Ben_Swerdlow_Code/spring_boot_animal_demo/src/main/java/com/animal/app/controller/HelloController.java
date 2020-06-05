package com.animal.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * com.<artifact-id?>.app is necessary to find controllers
 */

@RestController
public class HelloController {

	@GetMapping("/")
	public String sayHello() {
		return "Welcome to Spring Boot App";
	}
}
