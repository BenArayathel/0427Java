package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;

/**
 * Servlet implementation class SecondServlet
 */
@WebServlet("/second")
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
		// Will get the current session, if one doesn't exist it will NOT create a new session (will be null)
		// This line is what allows people to access the same URL with different information
		HttpSession session = request.getSession(false);
		if (session==null) {
			response.sendRedirect("/session_demo");
		} else {
			// session.getAttribute("name") stores an Object, so need to downcast it
			User user = (User) session.getAttribute("user");
			user.setJob(request.getParameter("job"));
			user.setRole(request.getParameter("role"));
			user.setOrg(request.getParameter("org"));
			session.setAttribute("user", user);
			response.sendRedirect("three.html");
		}
	}

}
