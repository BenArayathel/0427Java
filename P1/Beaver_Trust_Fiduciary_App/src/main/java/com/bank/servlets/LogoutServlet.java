package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	// suggested by eclipse for Httpsession
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Inside logout servlet");
		
		// access session to get user
		HttpSession session = req.getSession(false);
		
		if (session == null) {			
			System.out.println("there wasn't a session");
			res.sendRedirect("/index.html");
		} else {
			// close the session			
			System.out.println("destroying the session");
			session.invalidate();
		}
	}
}
