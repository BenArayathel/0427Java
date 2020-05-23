package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exceptions.BusinessException;
import com.models.User;
import com.services.impl.AccountServiceImpl;
import com.services.impl.UserServiceImpl;

import org.apache.log4j.Logger;

public class LoginController {
	final static Logger loggy = Logger.getLogger(User.class);

	public static boolean login(String email, String password) {
		UserServiceImpl uSI = new UserServiceImpl();
		AccountServiceImpl aSI = new AccountServiceImpl();
		
		try {
			if(uSI.userLogin(email, password)) {
			loggy.debug("Login successful with email " + email + " and password " + password);
				
				return true;				
			} else {
				loggy.debug("Login failed with Email- " + email + " Password- " + password);
				loggy.error("Login failed with Email- " + email + " Password- " + password);
				return false;
			}
		} catch (BusinessException e) {
			return false;
		}
		
	}

}

