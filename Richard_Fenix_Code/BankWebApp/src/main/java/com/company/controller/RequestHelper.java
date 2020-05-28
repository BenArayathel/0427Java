package com.company.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	public static String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getRequestURI());
		
		switch(request.getRequestURI()) {
			case "/BankWebApp/api/login":
				return LoginController.login(request,response);
		
			case "/BankWebApp/api/customer":
				return CustomerController.home(request, response);
		
			case "/BankWebApp/api/customer/accountlist":
				return CustomerController.getAccountList(request,response);

			case "/BankWebApp/api/deposit":
				return CustomerController.deposit(request,response);				

			case "/BankWebApp/api/withdraw":
				return CustomerController.withdraw(request,response);				

				
		}
		
		return "/loginPage.html";

		
//		if (request.getRequestURI().equals("/BankWebApp/customer")) {
//			return "CustomerController.home(request, response)";
//		} else if (request.getRequestURI().equals("BankWebApp/admin")) {
//			return "AdminController.home(request, response)";
//		} else {
//			return "index.html";
//		}
		
	}

}
