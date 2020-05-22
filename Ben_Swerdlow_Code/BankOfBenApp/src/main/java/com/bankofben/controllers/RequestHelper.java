package com.bankofben.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bankofben.exceptions.BusinessException;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
		
		System.out.println(request.getRequestURI());
		
		switch(request.getRequestURI()) {
		case "BankOfBen/api/Home":
			return HomeController.home(request, response);
//			break;
		case "BankOfBen/api/InitialRegistration":
			return InitialRegistration.register(request, response);
//		case "BankOfBen/api/CompleteRegistration":
//			return CompleteRegistration.register(request, response);
		case "BankOfBen/api/Login":
			return LoginController.login(request, response);
//			break;
		}
		System.out.println("Didn't recognize option");
		return "login.html";
	}

}
