package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;

/**
 * Servlet implementation class SummaryServlet
 */
@WebServlet("/summary")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		if (session==null) {
			response.sendRedirect("/session_demo");
		} else {
			User user = (User) session.getAttribute("user");
			
			user.setGraduation(request.getParameter("graduation"));
			user.setYear(Integer.parseInt(request.getParameter("year")));
			user.setMajor(request.getParameter("major"));
			user.setScore(Double.parseDouble(request.getParameter("score")));
			PrintWriter out = response.getWriter();
			out.print("<h3>Hello "+user.getFname()+" "+user.getLname()+"! Here are your details:</h3>");
			out.print("<h3>"+user+"</h3>");
			out.print("<h3>session id = "+session.getId()+"</h3>");
			out.print("<h3>session created at "+new Date(session.getCreationTime()));
			session.invalidate();//destroy the session
//			session.setMaxInactiveInterval(900);// Will invalidate the user's session (log out) after 15 minutes (900 seconds)
		}
	}

}
