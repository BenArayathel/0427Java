package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controllers.RequestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;

public class MasterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("WE ARE IN GET");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session;
		
		if(req.getSession().isNew()) {
			System.out.println("New Session");
			session = req.getSession(true);
			
		}else {
			System.out.println("New Session");
			session = req.getSession(false);
		}
		

		RequestHelper.process(req, resp);
		
		
		/*
		 * ALL GOOD CODE!!!
		 */
//		resp.setContentType("application/json");
//		String userID = req.getParameter("userID");
//		String password = req.getParameter("password");
//		User user = new User(userID, password);
//		user = new ObjectMapper().readValue(req.getReader(), com.models.User.class);
//		resp.getWriter().write(new ObjectMapper().writeValueAsString(user));
//		System.out.println(user.getUserID());
	}

//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher(RequestHelper.process(req,resp)).forward(req,resp);
//		resp.setContentType("application/json");
//		
//		String userID = req.getParameter("userID");
//		String password = req.getParameter("password");
//		
//		System.out.println("From SERVICE We get " + userID);
//	}

	
}
