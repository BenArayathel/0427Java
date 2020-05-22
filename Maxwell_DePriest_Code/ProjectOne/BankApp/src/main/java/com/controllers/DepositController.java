package com.controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exceptions.BusinessException;
import com.models.Account;
import com.models.User;
import com.services.impl.AccountServiceImpl;
import com.services.impl.UserServiceImpl;

public class DepositController {
	
	
	public static String deposit(HttpServletRequest req, HttpServletResponse res) {
		String email = req.getParameter("email");
		System.out.println(email);
		UserServiceImpl uSI = new UserServiceImpl();
		try {
			Account a = uSI.setCurrentAccount(email);
		} catch (BusinessException e) {
			return "/404.html";
		}
		
		return "/api/Home";
	}
	
}
