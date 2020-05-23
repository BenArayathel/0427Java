package com.lunch.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.LunchUser;

public class LunchServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		 System.out.println("GET");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("\n\nTHIS IS LUNCH SERVLET !!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("POST");
		PrintWriter pw = res.getWriter();
		//res.getContentType("text/html");
		LunchUser lu = new LunchUser();
		//lu.setUsername("lunch");
		//lu.setPassword("pass");
		ObjectMapper om = new ObjectMapper();
		
		
		String username = req.getParameter("username");
		String pass = req.getParameter("pass");
		System.out.println("username: " + username);
		System.out.println("pass is: " + pass);
		if (username.equals("lunch") && pass.equals("pass")) {
			System.out.println("Success !");
			res.sendRedirect("https://www.google.com/");
		} else {
			System.out.println("Invalid");
			pw.println("Invalid");
			pw.close();
		}
		

	}

}
