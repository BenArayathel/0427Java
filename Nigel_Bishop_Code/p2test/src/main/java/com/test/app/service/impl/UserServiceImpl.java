package com.test.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.app.dao.userDAO;
import com.test.app.model.user;
import com.test.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private userDAO userDAO;

	@Override
	public user createUser(user user) {
		return userDAO.save(user);
	}

	@Override
	public List<user> getAllUser() {
		return userDAO.findAll();
	}

}
