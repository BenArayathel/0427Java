package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exceptions.BusinessException;
import com.services.impl.AccountServiceImpl;
import com.services.impl.UserServiceImpl;

public class LoginController {

	public static boolean login(String email, String password) {
		UserServiceImpl uSI = new UserServiceImpl();
		AccountServiceImpl aSI = new AccountServiceImpl();
		try {
			if(uSI.userLogin(email, password)) {
				System.out.println("login worked!");
				System.out.println("Email- " + email + " Password- " + password);
				
				
				
				//return "/api/Home";
				return true;				
			} else {
				System.out.println("Login failed");
				System.out.println("Email- " + email + " Password- " + password);
				return false;
			}
		} catch (BusinessException e) {
			return false;
		}
		
	}

}

/*
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println(req);
		String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		System.out.println(test);
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		System.out.println("Email2- " + email + " Password- " + password);
		doGet(req, res);
	}

}
*/
