package com.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.impl.AccountDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.exceptions.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Account;
import com.models.CurrentUser;
import com.models.User;
import com.services.impl.AccountServiceImpl;
import com.services.impl.UserServiceImpl;

public class PostController {
	final static Logger loggy = Logger.getLogger(User.class);
	static UserServiceImpl uSI = new UserServiceImpl();
	UserDaoImpl uDI = new UserDaoImpl();
	AccountDaoImpl aDI = new AccountDaoImpl();
	static AccountServiceImpl aSI = new AccountServiceImpl();
	private static ObjectMapper mapper = new ObjectMapper();
	private static CurrentUser currentUser = null;

	public PostController() {
		
	}
	
	public static HttpServletResponse postInfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.getQueryString();
		String direction = req.getParameter("direction");
		
		if(direction.equalsIgnoreCase("user")) {
			String userRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		    
			User newUser = mapper.readValue(userRequest, User.class);
			try {
				String encPass = uSI.passwordEncryption(newUser.getPassword());
				newUser.setPassword(encPass);
				if(uSI.createNewUser(newUser)) {
					uSI.createNewAccount(newUser.getEmail(), "0.00");
					currentUser = new CurrentUser(newUser.getEmail(), newUser.getName(), "0.00", "0.00", "customer");
					
					loggy.debug("New current user created. Email- " + currentUser.getEmail());
					mapper.writeValue(res.getWriter(), currentUser);
					return res;
				};
			} catch (BusinessException e) {
				res.getWriter().append("Failed to create a new user with email " + newUser.getEmail());
				loggy.info("Failed to create a new user with email " + newUser.getEmail());
				e.printStackTrace();
				return res;
			}
		}
		
		return null;
	}
	
	public static void postAccount(Account newAccount) {
		//uSI.createNewAccount(email, startingCheckingAmount)
	}

}
