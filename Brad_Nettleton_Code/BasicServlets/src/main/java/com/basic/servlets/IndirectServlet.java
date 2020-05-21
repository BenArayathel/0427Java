package com.basic.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndirectServlet extends HttpServlet {
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//Servlet Config
		String food = getInitParameter("Favorite Food");
		
		System.out.println("Inside doGet method, IndirectServlet");
		
//		res.sendRedirect("http://localhost:9090/BasicServlets/gibberish");
		
		res.sendRedirect("https://www.google.com/");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Inside indirect Servlet, doPost");
		
//		RequestDispatcher redis = req.getRequestDispatcher("/welcome.html");
		RequestDispatcher redis = req.getRequestDispatcher("/gibberish");
		
		redis.forward(req, res);
		
	}
}
