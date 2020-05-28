package com.bhanking.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {

	public static String login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("LoginController.login");
		return "/customer_login.html";
	}

}
