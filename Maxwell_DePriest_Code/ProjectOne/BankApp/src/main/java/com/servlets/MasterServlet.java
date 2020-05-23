package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.controllers.LoginController;
import com.controllers.PostController;
import com.controllers.RequestHelper;
import com.exceptions.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Account;
import com.models.User;
import com.models.ValidLogin;
import com.services.impl.UserServiceImpl;

public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger loggy = Logger.getLogger(User.class);
	private static UserServiceImpl uSI = new UserServiceImpl();
       
    public MasterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
	
		
		pw.append("get at: MasterServlet. ");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();

		if ("POST".equalsIgnoreCase(req.getMethod())) 
		{
			req.getQueryString();
			String direction = req.getParameter("direction");
			
			if(direction.equalsIgnoreCase("user")) {
				String userRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			    ObjectMapper mapper = new ObjectMapper();
				User newUser = mapper.readValue(userRequest, User.class);
				try {
					uSI.createNewUser(newUser);
					res.getWriter().append("Successfully created user with email " + newUser.getEmail());
					doGet(req, res);
				} catch (BusinessException e) {
					loggy.info("Failed to create a new user with email " + newUser.getEmail());
					e.printStackTrace();
				}
			}
			
			else if (direction.equalsIgnoreCase("account")) {
				String accountRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			    ObjectMapper mapper = new ObjectMapper();
				Account newAccount = mapper.readValue(accountRequest, Account.class);
				
				try {
					uSI.createNewAccount(newAccount.getEmail(), newAccount.getCheckingBalance());
					res.getWriter().append("Successfully created account associated with email " + newAccount.getEmail() + " and starting balance of " + newAccount.getCheckingBalance());
					doGet(req, res);
				} catch (BusinessException e) {
					loggy.info("Failed to create a new account with email " + newAccount.getEmail());
					res.getWriter().append("Failed to create account for email " + newAccount.getEmail());
					doGet(req, res);
					e.printStackTrace();
				}
			}
			
			pw.append("Successfully sent post action. And now we wait");
		}
		
		else {
			pw.append("Error. Please use the post action next time");
		}
		
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
//	private void determineDirection(HttpServletRequest req, HttpServletResponse res, String action) {
//		req.getQueryString();
//		String direction = req.getParameter("direction");
//
//		if (direction.equalsIgnoreCase("user")) {
//			System.out.println("Modding user");
//		}
//		else if (direction.equalsIgnoreCase("account")) {
//			System.out.println("Modding account");
//		}
//		
//	}
	
	

}
