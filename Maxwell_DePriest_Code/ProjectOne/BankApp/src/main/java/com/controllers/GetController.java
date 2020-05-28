package com.controllers;

import com.models.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.impl.AccountDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.exceptions.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Account;
import com.models.CurrentUser;


public class GetController {
	
	private static UserDaoImpl uDI = new UserDaoImpl();
	private static AccountDaoImpl aDI= new AccountDaoImpl();
	private static ObjectMapper mapper = new ObjectMapper();

	public GetController() {
		
	}
	
	public static HttpServletResponse getUserInfo(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		req.getQueryString();
		String direction = req.getParameter("direction");
		String userEmail = req.getParameter("email"); 
		if(direction.equalsIgnoreCase("user")) {
			
		}

		try {
			User newUser = uDI.selectUserByEmail(userEmail);
			Account foundAccount = aDI.selectAccountByEmail(userEmail);
			CurrentUser foundUser = new CurrentUser(newUser.getEmail(), newUser.getName(), foundAccount.getCheckingAccountNumber(), foundAccount.getSavingsAccountNumber(), foundAccount.getActive());
			mapper.writeValue(res.getWriter(), foundUser);
		} catch (BusinessException e) {
			e.printStackTrace();
			res = null;		
			}
		
		return res;
	}

}
