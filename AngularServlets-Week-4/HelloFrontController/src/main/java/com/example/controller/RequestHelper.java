package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * Point the request to the right controller
		 */

		switch(request.getRequestURI()) {
		
		case "HelloFrontController/api/Home":
			
			return HomeController.home(request,response);
		
		case "HelloFrontController/api/Login":

			return LoginController.login(request,response);
			
		}
		
		return "login.html";
	}

}
