package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao_implementation.UserDAOImplementation;
import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	// suggested by eclipse for Httpsession
	private static final long serialVersionUID = 1L;

	// Won't be using this, don't want password in URL
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Something happened");
		
		PrintWriter pw = res.getWriter();
		pw.write("<h1> We're sending something back now, inside of doGet</h1>");
		
		doPost(req,res);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// setup tools
		UserServiceImplementation usi = new UserServiceImplementation();
		UserDAOImplementation udi = new UserDAOImplementation();
		ObjectMapper mapper = new ObjectMapper();
		
		// map username and password onto a new User object
		User user = mapper.readValue(req.getReader(), com.bank.models.User.class);
		
	    System.out.println("username = " + user.getUsername());
	    System.out.println("password = " + user.getPassword());
	    
		String username = user.getUsername();
		String password = user.getPassword();
				
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
				
		try {
			if (username.equalsIgnoreCase("employee") && (password.equalsIgnoreCase("employee")))  {				
				writer.write("employee_portal.html");
			} else if (usi.loginUser(username, password)) {
				System.out.println("login successful");
				
				// access entire user object
				// this is a little clumsy, but it works, just make
				// sure no duplicate usernames can exist
				user = udi.accessUserObject(username);
				System.out.println(user);
				
				// handle whether they are approved or not
				if (user.getApproved() == 0) {
					writer.write("pending.html");
				} else {
					// add a cookie of the username
					Cookie cookie = new Cookie("username", username);
					res.addCookie(cookie);
					
					// start session
					HttpSession session = req.getSession();
					session.setAttribute("user", user);
					
					// return the redirect URL
					writer.write("user_home.html");					
				}
				
				
			} else {
				System.out.println("login failed");
				writer.write("failed_login.html");	
			}
		
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
		}
	}
}


