package com.lunch.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/custdirectory")
public class CustDirectoryServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		 System.out.println("Cust-Directory-Servlet GET ... is called");
		 System.out.println("id is : " + req.getParameter("id"));
//		res.sendRedirect("http://localhost:9999/Password/custOptDirectory.html");
		 res.sendRedirect("http://localhost:9999/Password/custOptDirectory.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		 System.out.println("Cust Directory Post ... ");
	}

}
