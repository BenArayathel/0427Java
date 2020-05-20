package com.examples.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {

	public static String login(HttpServletRequest request, HttpServletResponse response) {
		// check for post method, (can't use get)
		/*
		 * You should route guard your controllers (or at least the important ones). We have to check for http method.
		 * You can also do things like check if they're an admin, etc.
		 */
		if (!request.getMethod().equals("POST")) {
			return "/login.html";
		}
		
		/*
		 * Example of Business Logic in Controller to access db for validation
		 * serviceLayer.routeGuard(request);
		 */
		
		// Bad practice to use GET and getParameter, don't do this! Will return information in URL
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username.equals("apples") && password.equals("louise")) {
			System.out.println("Login worked");
			request.getSession().setAttribute("loggedUsername", username);
			request.getSession().setAttribute("loggedPassword", password);
			// if wanted to return a json, do it through the response object
			return "api/Home";
		} else {
			System.out.println("Login didn't work. Please try again.");
			return "/login.html";
		}
	}

}
