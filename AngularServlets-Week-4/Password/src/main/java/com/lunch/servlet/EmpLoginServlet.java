package com.lunch.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/emplogin")
public class EmpLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
     
    public EmpLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("password was: " + request.getParameter("password"));
		String val = "987";
		
		if (val.equals(request.getParameter("password"))) {
			
			response.sendRedirect("http://localhost:9999/Password/empOptDirectory.html");
			
		} else {
			
			response.sendRedirect("http://localhost:9999/Password/empLogin.html");

		}
	}

}
