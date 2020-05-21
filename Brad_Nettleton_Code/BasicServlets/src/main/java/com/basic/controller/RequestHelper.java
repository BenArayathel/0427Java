package com.basic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {

		switch (request.getRequestURI()) {
		case "BasicServlets/api/Home":
			return HomeController.home(request,response);
		case "BasicServlets/api/login":
			return LoginController.login(request,response);

		}

		return "login.html";
	}

}
