package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.servlets.Planet;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DirectServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		System.out.println("Inside doGet");
		
//		res.setContentType("text/html"); This is redundant, by default this is the type
		
		//PrintWriter Object? Is an object used to DIRECTLY respond to the client 
		PrintWriter pw = res.getWriter();
		pw.write("<h1> We're sending something back now doGet</h1>");
		
		//We have a presentation layer <-> servlets <-> service layer <-> dao layer <-> db 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside doPost");
		
		res.setContentType("application/json");
		
		
		//SERVLET CONTEXT
		String db = req.getServletContext().getInitParameter("databaseUrl");
		
		System.out.println(db);
		
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		
		Planet p = new Planet(name, id);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(p));
	}

}
