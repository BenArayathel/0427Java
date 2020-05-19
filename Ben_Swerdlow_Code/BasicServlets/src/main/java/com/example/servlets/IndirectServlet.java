package com.example.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndirectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1098791925239249702L;
	
	/*
	 * 3 ways a servlet can respond to an incoming request
	 * 1) Direct Response (using the PrintWriter object)
	 * 		This is something that happens when the servlet directly responds
	 * 2) Forward (1 req, 1 res, uses RequestDispatcher's .forward() method)
	 * 		The servelt asks another servlet/resource (internal to your server) to assist with the response
	 * 3) Redirect (2 req, 2 res, uses HttpServletResponse object's .sendRedirect() method)
	 * 		The servlet informs the client that it must go somewhere else to have the request handled
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside doGet method, indirect servlet");
		
		// Servlet Config
		String favFood = getInitParameter("Favorite Food");
		System.out.println(favFood);
		String bestFood = getInitParameter("Best Food");
		System.out.println(bestFood);
		
		/*
		 * REDIRECTING
		 * 2 requests, 2 responses
		 * Client is AWARE of the new resource/entity
		 * 
		 * Can be linked to an external resource
		 */
		
//		res.sendRedirect("http://localhost:9999/BasicServlets/gibberish");
		// For redirect you can use external sources
		res.sendRedirect("https://www.google.com");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside doPost, indirect servlet");
		String db = req.getServletContext().getInitParameter("databaseUrl");
		System.out.println(db);
		
		/*
		 * FORWARDING
		 * 1 request, 1 response
		 * Client is UNAWARE of the new resource/entity
		 */
		
//		RequestDispatcher redis = req.getRequestDispatcher("/Welcome.html");
		RequestDispatcher redis = req.getRequestDispatcher("/gibberish?name=Earth");
		
		// Outside resources WILL NOT WORK with Req Dispatcher
//		RequestDispatcher redis = req.getRequestDispatcher("https://www.google.com");
		
		redis.forward(req, res);
	}

}
