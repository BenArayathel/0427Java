package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exceptions.BusinessException;
import com.models.User;
import com.services.impl.UserServiceImpl;

public class HomeController {

	public static String home(HttpServletRequest req, HttpServletResponse res) {
		
		String email = req.getParameter("email");
		UserServiceImpl uSI = new UserServiceImpl();
		try {
			User currentUser = uSI.setCurrentUser(email);
		} catch (BusinessException e) {
			return "/404.html";
		}
		
		return "/index.html";
	}

}
