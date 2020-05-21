package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecondServlet
 */
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie1=new Cookie("PreviousJobTitle", request.getParameter("job"));
		Cookie cookie2=new Cookie("PreviousRole", request.getParameter("role"));
		Cookie cookie3=new Cookie("PreviousOrganization", request.getParameter("org"));
		
		
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		
		
		response.sendRedirect("three.html");
	}

}
