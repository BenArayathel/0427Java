package com.example.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

// this line is the connection to my form, via the "action" attribute of the form
// in ben's approach this would be defined in the web.xml file
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// servlets should return information or redirect
		// server side session HttpSession or use localstorage
		
		// read the form fields
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//		
//		System.out.println("username: " + username);
//		System.out.println("password: " + password);
				
		// Example Code
				
		// hardcoded
//		String userJson =
//		    "{ \"username\" : \"ethan\", \"password\" : \"pass\" }";

		try {
			// this was the right format, below is what to enter specifically

			ObjectMapper mapper = new ObjectMapper();
			
			User user = mapper.readValue(req.getReader(), com.example.models.User.class);
			
			// example from ben's project
//			ObjectMapper om = new ObjectMapper();
//			Planet p1 = om.readValue(req.getReader(), com.example.models.Planet.class);
//			
//			System.out.println(p1);
//			
//			res.getWriter().write(new ObjectMapper().writeValueAsString(p1));
			
			// THIS DOES IT!!!
			System.out.println(user);
		    System.out.println("username = " + user.getUsername());
		    System.out.println("password = " + user.getPassword());
		    
			String username = user.getUsername();
			String password = user.getPassword();
						
			res.setContentType("application/json");
			
			PrintWriter wr = res.getWriter();
			
			// how to return values?
			if (username.equals("employee") && (password.equals("employee")))  {
				// use full url
//				res.sendRedirect("http://localhost:9999/HelloServlets/login_successful.html");
				// this version is based on ben's code
				wr.write(mapper.writeValueAsString(user));
//				wr.write("login_successful.html");
			} else {
//				res.sendRedirect("try_again.html");
				wr.write("try_again.html");

			}
			
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		// 
		
				
	}
}
