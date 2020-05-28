package com.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;

public class RequestHelper {


	
	public static void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		/*
		 * Point the request to the right controller
		 */
		System.out.println("In RequestHelper " + req.getRequestURI());
		
		switch(req.getRequestURI()) {
		//Check the account balance
		case "/controller/api/checkBalance":
			AccountController.requestBalance(req, resp);
			break;
		
		case "/controller/api/Home":
			
			HomeController.home(req,resp);
			break;
			
		case "/controller/api/LoginServlet":
			LoginController.login(req,resp);
			break;
		case "/controller/api/deposit":
			AccountController.deposit(req, resp);
			break;
		case "/controller/api/withdraw":
			AccountController.withdraw(req, resp);
			break;
		case "/controller/api/signup":
			try {
				AccountController.addAccount(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/controller/api/ReturnAccounts":
			try {
				WebEmployeeController.returnAllAccounts(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
	}
	
	private static void sendResponse(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		resp.setContentType("application/json");
		String userID = req.getParameter("userID");
		String password = req.getParameter("password");
		User user = new User(userID, password);
		user = new ObjectMapper().readValue(req.getReader(), com.models.User.class);
		resp.getWriter().write(new ObjectMapper().writeValueAsString(user));
		System.out.println("UserID: " + user.getUserID());
	}
}
