package com.test.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.app.dao.userLoginDAO;
import com.test.app.model.UserLogin;
import com.test.app.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService{
	
	@Autowired
	private userLoginDAO userLoginDAO;

	@Override
	public UserLogin createUser(UserLogin UserLogin) {
		return userLoginDAO.save(UserLogin);
	}

	@Override
	public List<UserLogin> getAllUser() {
		return userLoginDAO.findAll();
	}

	@Override
	public UserLogin findByUsername(String userName) {
		return userLoginDAO.findByuserName(userName);
	}

}
