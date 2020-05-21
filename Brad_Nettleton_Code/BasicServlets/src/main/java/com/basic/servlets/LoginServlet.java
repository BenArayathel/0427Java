package com.basic.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		RequestDispatcher view = req.getRequestDispatcher("/path/to/file.html");
//      view.forward(req, resp); 
		
		RequestDispatcher view = req.getRequestDispatcher("/login.html");
		view.forward(req, res);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		final String LOGIN_NAME = "Bilbo";
		final String LOGIN_PASSWORD = "the1ring";
	
		String reqName = req.getParameter("username");
		String reqPassword = req.getParameter("password");
		
		if(reqName.equals(LOGIN_NAME) && reqPassword.equals(LOGIN_PASSWORD)) {
			RequestDispatcher view = req.getRequestDispatcher("/success.html");
			view.forward(req, res);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("/login.html");
			view.forward(req, res);
		}
	}

}
