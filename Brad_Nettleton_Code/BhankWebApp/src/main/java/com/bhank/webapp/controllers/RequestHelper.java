package com.bhank.webapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {

		switch(request.getRequestURI()) {
		case "BhankWebApp/CustomerLogin":
			return LoginController.login(request,response);
		}
		
		return "index.html";
	}

}
