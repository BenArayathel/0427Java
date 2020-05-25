package com.servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.controllers.LoginController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;
import com.models.Account;
import com.models.CurrentUser;
import com.dao.impl.UserDaoImpl;
import com.dao.impl.AccountDaoImpl;
import com.exceptions.BusinessException;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDaoImpl uDI = new UserDaoImpl();
	private static AccountDaoImpl aDI = new AccountDaoImpl();
	final static Logger loggy = Logger.getLogger(User.class);
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.getWriter().append("doGet-ing in LoginServlet").append(req.getContextPath());
		
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if ("POST".equalsIgnoreCase(req.getMethod())) 
			loggy.debug("Posting to loginServlet");
		{
			String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		    ObjectMapper mapper = new ObjectMapper();
			User userCheck = mapper.readValue(test, User.class);
		    if(LoginController.login(userCheck.getEmail(), userCheck.getPassword())) {
		    	try {
					User approvedUser = uDI.selectUserByEmail(userCheck.getEmail());
					Account userAccount = aDI.selectAccountByEmail(approvedUser.getEmail());
					CurrentUser currentUser = new CurrentUser(approvedUser.getEmail(), approvedUser.getName(), userAccount.getCheckingBalance(), userAccount.getSavingsBalance(), approvedUser.getStatus());
					loggy.debug("New current user created. Email- " + currentUser.getEmail());
					mapper.writeValue(res.getWriter(), currentUser);
					
					
			    	doGet(req, res);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		    else {
		    	res.getWriter().append("Login Failed. Please try again.");
		    	doGet(req,res);
		    }
		}
		
	}

}
