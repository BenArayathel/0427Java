package com.examples.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		
		switch(request.getRequestURI()) {
		case "HelloFrontController/api/Home":
			return HomeController.home(request, response);
//			break;
		case "HelloFrontController/api/Login":
			return LoginController.login(request, response);
//			break;
		}
		System.out.println("Didn't recognize option");
		return "login.html";
	}

}
