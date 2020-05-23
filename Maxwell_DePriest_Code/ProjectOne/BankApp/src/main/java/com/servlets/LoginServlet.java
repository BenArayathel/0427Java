package com.servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controllers.LoginController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;
import com.models.ValidLogin;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.getWriter().append("doGet-ing in LoginServlet").append(req.getContextPath());
		
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Posting in loginServlet");
		if ("POST".equalsIgnoreCase(req.getMethod())) 
		{
			String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		    ObjectMapper mapper = new ObjectMapper();
			User userCheck = mapper.readValue(test, User.class);
		    if(LoginController.login(userCheck.getEmail(), userCheck.getPassword())) {
		    	res.getWriter().append("Login successful");
		    	doGet(req, res);
		    }
		    else {
		    	res.getWriter().append("Login Failed. Please try again.");
		    	doGet(req,res);
		    }
		}
		
	}

}
