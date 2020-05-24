package com.company.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	public static String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getRequestURI().equals("/BankWebApp/customer")) {
			return "CustomerController.home(request, response)";
		} else if (request.getRequestURI().equals("BankWebApp/admin")) {
			return "AdminController.home(request, response)";
		} else {
			return "index.html";
		}
		
	}

}
