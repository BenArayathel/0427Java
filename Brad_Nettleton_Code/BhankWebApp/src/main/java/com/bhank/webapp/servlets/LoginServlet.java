package com.bhank.webapp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhank.webapp.model.Login;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login servlet doGet");
		RequestDispatcher view = request.getRequestDispatcher("/login.html");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login servlet doPost");
		ObjectMapper om = new ObjectMapper();
		Login login = om.readValue(request.getReader(), com.bhank.webapp.model.Login.class);
		
		System.out.println(login);
		
		final String LOGIN_NAME = "bilbo";
		final String LOGIN_PASSWORD = "ring";
		
		if(login.getUsername().equals(LOGIN_NAME) && login.getPassword().equals(LOGIN_PASSWORD)) {
			System.out.println("login successful");
			RequestDispatcher view = request.getRequestDispatcher("/CustomerHome");
			view.forward(request, response);
		} else {
			System.out.println("login unsuccessful");
			RequestDispatcher view = request.getRequestDispatcher("/Login");
			view.forward(request, response);
		}
		
//		response.getWriter().write(new ObjectMapper().writeValueAsString(login));
//		doGet(request, response);
	}

}
