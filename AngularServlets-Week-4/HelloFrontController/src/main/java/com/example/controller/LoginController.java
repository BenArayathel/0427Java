package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {
	/*
	 * Perform business logic 
	 */

	public static String login(HttpServletRequest request, HttpServletResponse response) {

			/*
			 * you should route guard for your controllers. We have to check for http method.
			 * You can also check stuff like, they are an admin. 
			 */
		if(!request.getMethod().equals("POST")) {
			return "/login.html";
		}
		
		//servicelayer.routeguuard(reequest)
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equals("apples") && password.equals("louise")) {
			System.out.println("login worked!");
			
			request.getSession().setAttribute("loggedusername", username);
			request.getSession().setAttribute("loggedpassword", password);
			
			return "/api/Home";
			
		} else {
			return "/login.html";
		}
		
	}

}
