package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		put in the endpoint from the web.xml file in postman, do a get request, and this prints to console
//		remember to restart server after changing a file before using postman
		
	System.out.println("Inside doGet of HelloServlet!");
	
	PrintWriter pw = res.getWriter();
	pw.write("<h1> We're sending something back now, inside of doGet</h1>");
	
	doPost(req,res);
	
}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Inside doPost of HelloServlet!");
		
		PrintWriter pw = res.getWriter();
		pw.write("<h1> We're sending something back now, inside of doPost</h1>");
	}
	

}
