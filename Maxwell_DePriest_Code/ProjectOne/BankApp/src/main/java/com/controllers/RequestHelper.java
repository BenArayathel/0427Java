package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {

		switch(request.getRequestURI()) {
		
		case "BankApp/api/Home":
			
			return HomeController.home(request,response);
		
//		case "BankApp/api/Login":
//
//			return LoginController.login(request,response);
//			
		}
		
		return "login.html";
	}

}
