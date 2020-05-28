package com.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.exceptions.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;
import com.services.impl.UserServiceImpl;

public class DeleteController {
	private static ObjectMapper mapper = new ObjectMapper();
	final static Logger loggy = Logger.getLogger(User.class);
	static UserServiceImpl uSI = new UserServiceImpl();

	public DeleteController() {
		// TODO Auto-generated constructor stub
	}
	
	public static String deleteUser(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getQueryString();
		String direction = req.getParameter("direction");
		
		if(direction.equalsIgnoreCase("user")) {
			String userRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			User user = mapper.readValue(userRequest, User.class);
			
			try {
				uSI.removeUserProfile(user.getEmail());
				loggy.debug("User with email: " + user.getEmail() + " deleted");
				return("Account with email " + user.getEmail() + " deleted. Good riddance");
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		}
		return("Error. Couldn't delete user");
		
	}

}
