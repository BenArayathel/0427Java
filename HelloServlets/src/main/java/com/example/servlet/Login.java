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
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// servlets should return information or redirect
		// server side session HttpSession or use localstorage
		
		// read the form fields
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		
		// using js instead of the form
		res.setContentType("application/json");
		
		
		// for class assignment, conditional:
		if (username.equals("employee") && (password.equals("employee")))  {
			res.sendRedirect("login_successful.html");
		} else {
			res.sendRedirect("try_again.html");
		}
		
	}

}
