package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.JDBC;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.User;

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
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am in doPost of LoginServelet yea!!!");

		String un = request.getParameter("userID");
		String pw = request.getParameter("password");
		
		User user = new User(un, pw);
		
		//user = new ObjectMapper().readValue(request.getReader(), com.models.User.class);
		System.out.println("User ID: " + user.getUserID());
		String username = user.getUserID();
		String password = user.getPassword();
		
		try {
			JDBC.databaseConnection("accountstable");
			if(JDBC.logIn(Integer.parseInt(username),  Integer.parseInt(password)) ){

				//If the user has successfully logged 
				System.out.println("I have been successfully logged in!");
				request.getSession().setAttribute("loggedusername", username);
				request.getSession().setAttribute("loggedpassword", password);
				//response.sendRediretVal = "true";
			}else {
				System.out.println("Log In was a failure!");
			}
		} catch (SQLException e) {
			System.out.println("There was an err in Login Controller: " + e);
		}
//		response.setContentType("application/json");
//		response.getWriter().write(new ObjectMapper().writeValueAsString(user));
	}

}
