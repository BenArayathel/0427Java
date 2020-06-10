package com.test.app.service;

import java.util.List;

import com.test.app.model.user;

public interface UserService {

	public user createUser(user user);
	public List<user> getAllUser();


	
}
