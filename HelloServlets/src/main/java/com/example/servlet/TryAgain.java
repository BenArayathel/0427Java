package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// this line is the connection to my form, via the "action" attribute of the form
// in ben's approach this would be defined in the web.xml file
@WebServlet("/TryAgain")
public class TryAgain extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Redirecting to login again.");
		
		res.sendRedirect("form.html");
		
	}

}
