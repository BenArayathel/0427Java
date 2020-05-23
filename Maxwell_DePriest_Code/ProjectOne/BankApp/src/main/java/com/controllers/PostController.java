package com.controllers;

import org.apache.log4j.Logger;

import com.dao.impl.AccountDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.exceptions.BusinessException;
import com.models.Account;
import com.models.User;
import com.services.impl.AccountServiceImpl;
import com.services.impl.UserServiceImpl;

public class PostController {
	final static Logger loggy = Logger.getLogger(User.class);
	static UserServiceImpl uSI = new UserServiceImpl();
	UserDaoImpl uDI = new UserDaoImpl();
	AccountDaoImpl aDI = new AccountDaoImpl();
	static AccountServiceImpl aSI = new AccountServiceImpl();

	public PostController() {
		
	}
	
	public static void postUser(User newUser) {
		try {
			uSI.createNewUser(newUser);
		} catch (BusinessException e) {
			loggy.error("Failed to create new user with email " + newUser.getEmail());
			e.printStackTrace();
		}
		
	}
	
	public static void postAccount(Account newAccount) {
		uSI.createNewAccount(email, startingCheckingAmount)
	}

}
