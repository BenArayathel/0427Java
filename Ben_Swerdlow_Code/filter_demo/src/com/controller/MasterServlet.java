package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MasterServlet
 */
@WebServlet(name="MasterServlet", urlPatterns= {"/MasterServlet"})
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("hello from servlet");
//		System.out.println("byebye from servlet");

		PrintWriter out = response.getWriter();
		out.print("<h2>Hello "+request.getParameter("name")+" from master servlet</h2>");
	}

}
