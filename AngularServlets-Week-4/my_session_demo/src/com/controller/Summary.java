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
 * Servlet implementation class Summary
 */
@WebServlet("/summary")
public class Summary extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
    public Summary() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		
		response.setContentType("text/html");
		if(session==null) {
			response.sendRedirect("/session_demo");
		}else {
			User user=(User) session.getAttribute("user");
			user.setGraduation(request.getParameter("graduation"));
			user.setYear(Integer.parseInt(request.getParameter("year")));
			user.setMajor(request.getParameter("major"));
			user.setScore(Double.parseDouble(request.getParameter("score")));
			PrintWriter out=response.getWriter();
			out.print("<h3>Hello "+user.getFname()+" ...  Here are your details</h3>");
			out.print("<h3>"+user+"</h3>");
			out.print("<h3>session id = "+session.getId()+"</h3>");
			out.print("<h3>session created at "+new Date(session.getCreationTime())+"</h3>");
		//	session.setMaxInactiveInterval(interval);\ destroys session after some inactive internal
			session.invalidate(); //destroying the session
					
		}
		
	}

}
