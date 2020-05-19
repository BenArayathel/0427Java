package com.example.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndirectServlet extends HttpServlet{
	
	/*
	 * 3 ways a servlet can respond to an incoming request:
	 * 
	 * Direct Response (using the PrintWriter object):
	 * 		This happens when the servlet directly responds
	 * 
	 * Forward (1 req, 1 resp. Uses RequestDispathcer's .forward() method)
	 * 		The servlet asks another servlet/resouce (internal to your server) to assist with the response
	 * 
	 * Redirect (2req, 2resp. Use HttpServletResponse object's .sendredirect() method)
	 * 		The servlet informs the client that it must go somethweher else to have the request handled 
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//Servlet Config
		String food = getInitParameter("Favourite Food");
		
		System.out.println("Inside doGet emthod, indirect Servlet");
		
		/*
		 * REDIRECTING
		 * 2 requests, TWO response
		 * Client is AWARE of the new resource/entity 
		 * 
		 * Can be linked to an extenal resource
		 */
		
		//res.sendRedirect("http://localhost:8088/BasicServlets/gibberish");
		
		res.sendRedirect("https://www.google.com/");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Inside Indirect Servlet, doPost");
		
		/*
		 * FORWARDING
		 * 1 request, 1 response
		 * client is UNAWARE of the new resource/entity 
		 */
		
//		RequestDispatcher redis = req.getRequestDispatcher("/Welcome.html");
		RequestDispatcher redis = req.getRequestDispatcher("/gibberish");
		
		//Outside resources -- WILL NOT WORK
//		RequestDispatcher redis = req.getRequestDispatcher("https://www.google.com/");
		
		//SERVLET CONTEXT
				String db = req.getServletContext().getInitParameter("databaseUrl");
				
				System.out.println(db);
		//
		
		redis.forward(req, res);
		
	}

}
