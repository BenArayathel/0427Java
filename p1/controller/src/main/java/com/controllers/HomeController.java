package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		return "/home.html";
	}
}
