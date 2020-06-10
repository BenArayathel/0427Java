package com.test.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.app.model.UserLogin;
import com.test.app.service.UserLoginService;

@CrossOrigin
@RestController
public class UserLoginController {
	
	@Autowired
	private UserLoginService loginservice;
	
	@PostMapping("/userlogin")
	public UserLogin createUser(@RequestBody UserLogin UserLogin) {
		return loginservice.createUser(UserLogin);
	}

	@GetMapping("/userlogins")
	public List<UserLogin> getAllUser() {
		return loginservice.getAllUser();
	}

	

}
