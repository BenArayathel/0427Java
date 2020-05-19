package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.models.Planet;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DirectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3881268350963696938L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside doGet");
		
//		res.setContentType("text/html"); This is redundant because it's the default type
		
		// PrintWriter Object is an object used to DIRECTLY respond to the client
		PrintWriter pw = res.getWriter();
		pw.write("<h1>We're sending something back now doGet</h1>");
		
		// We have a all the things! presentation layer <-> servlets <-> service layer <-> dao layer <-> db
		// Business Logic
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside doPost");
		
		// Send response as a json
		res.setContentType("application/json");
		
		String db = req.getServletContext().getInitParameter("databaseUrl");
		System.out.println(db);
		
		// Gets name from Postman (client)
		String name = req.getParameter("name");
		
		Planet p = new Planet(name);
		
		// Send a JSON of p to the site (allowed because of jackson jar)
		res.getWriter().write(new ObjectMapper().writeValueAsString(p));
	}

}
