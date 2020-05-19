package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class LoginServlet extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		pw.append("Served at: ").append(req.getContextPath());
		System.out.println(req.getQueryString());
		
		System.out.println("Posted and returned data");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println(req);
		String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		System.out.println(test);
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		System.out.println("Email2- " + email + " Password- " + password);
		doGet(req, res);
	}

}
