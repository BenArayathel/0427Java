package com.bhanking.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	public static String process(HttpServletRequest request, HttpServletResponse response) {

		switch(request.getRequestURI()) {
		case "Bhanking/CustomerLogin":
			return LoginController.login(request,response);
		}
		
		return "index.html";
	}

}
