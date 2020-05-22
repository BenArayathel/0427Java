package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exceptions.BusinessException;
import com.services.impl.AccountServiceImpl;
import com.services.impl.UserServiceImpl;

public class LoginController {

	public static String login(HttpServletRequest request, HttpServletResponse response) {
		UserServiceImpl uSI = new UserServiceImpl();
		AccountServiceImpl aSI = new AccountServiceImpl();

		if(!request.getMethod().equals("POST")) {
			return "/login.html";
		}
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		
		
		
		try {
			if(uSI.userLogin(email, password)) {
				System.out.println("login worked!");
				
				request.getSession().setAttribute("currentUserEmail", email);
				
				return "/api/Home";
				
			} else {
				return "/login.html";
			}
		} catch (BusinessException e) {
			return "/login.html";
		}
		
	}

}
