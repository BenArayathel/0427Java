package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SummaryServlet
 */
public class SummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SummaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Cookie cookies[]=request.getCookies();
		out.print("<h3>Here are the details</h3>");
		out.print("<table border='1px'");
		for(Cookie cookie:cookies) {
			out.print("<tr>");
			out.print("<th>"+cookie.getName()+"</th>");
			out.print("<td>"+cookie.getValue()+"</td>");
			out.print("</tr>");
		}
		out.print("<tr>");
		out.print("<th>Graduation Completed</th>");
		out.print("<td>"+request.getParameter("graduation")+"</td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th>Graduation Year</th>");
		out.print("<td>"+request.getParameter("year")+"</td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th>Score</th>");
		out.print("<td>"+request.getParameter("score")+"</td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<th>Major</th>");
		out.print("<td>"+request.getParameter("major")+"</td>");
		out.print("</tr>");
		
		out.print("</table>");
	}

}
