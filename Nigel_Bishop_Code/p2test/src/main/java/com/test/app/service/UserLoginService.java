package com.test.app.service;

import java.util.List;

import com.test.app.model.UserLogin;

public interface UserLoginService {

	
	public UserLogin createUser(UserLogin UserLogin);
	public List<UserLogin> getAllUser();
}
