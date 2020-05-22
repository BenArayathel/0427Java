package com.bhank.webapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {

	public static String login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("LoginController.login");
		return "/api/login.html";
	}

}
