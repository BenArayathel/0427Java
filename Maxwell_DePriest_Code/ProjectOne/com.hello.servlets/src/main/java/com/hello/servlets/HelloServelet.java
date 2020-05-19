package com.hello.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServelet extends HttpServlet {
	
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside doGet request of HelloServlet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside doPost of HelloServlet");
		
		PrintWriter pw = res.getWriter();
		pw.write("<h1>We're sending something back now</h1>");
	}
	
}
