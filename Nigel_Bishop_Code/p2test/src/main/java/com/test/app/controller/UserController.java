package com.test.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.app.model.user;
import com.test.app.service.UserService;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping("/user")
	public user createUser(@RequestBody user user) {
		return service.createUser(user);
	}

	@GetMapping("/users")
	public List<user> getAllUser() {
		return service.getAllUser();
	}

}
