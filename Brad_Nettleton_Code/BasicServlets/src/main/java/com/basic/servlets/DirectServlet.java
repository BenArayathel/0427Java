package com.basic.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basic.models.Planet;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DirectServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Inside DoGet");
		
		//res.setContentType("text/html"): this is redundant, by default this is the type
		
//		PrintWriter Object? Is an object used to DIRECTLY respond to the client
		PrintWriter pw = res.getWriter();
		pw.write("<h1>We're sending something back now doGet</h1>");
		
//		We have a presentation layer -- <-> servlets <-> 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Inside DoPost");
		
		res.setContentType("application/json");
		
		String db = req.getServletContext().getInitParameter("databaseUrl");
		System.out.println(db);
		                     
//		String name = req.getParameter("name");
//		
//		Planet p = new Planet(name);
		
		ObjectMapper om = new ObjectMapper();
		Planet p1 = om.readValue(req.getReader(), com.basic.models.Planet.class);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(p1));
		
		PrintWriter pw = res.getWriter();
//		pw.write("<h1>We're sending something back now doGet</h1>");
		
	}
}
