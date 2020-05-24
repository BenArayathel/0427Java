package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.presentation.CreateUserAccountView;
import com.bank.presentation.WelcomeView;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/createuseraccount")
public class CreateUserAccountServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// set up tools
		UserServiceImplementation usi = new UserServiceImplementation();
		ObjectMapper mapper = new ObjectMapper();
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
		
		// map the requested username and password to a user object
		// (for some reason, just getting parameters wasn't working)
		User user = mapper.readValue(req.getReader(), com.bank.models.User.class);
		
		// fill the local variables with that data
		String username = user.getUsername();
		String password = user.getPassword();

		// see something in eclipse when it works
		System.out.println(username);
		System.out.println(password);
				
		
		//check if they are already in the db
		try {
			if (usi.loginUser(user.getUsername(), user.getPassword())) {
				writer.write("account_exists.html");
			} else {
				// let them create account
				user = usi.createUser(user);
				writer.write("account_create_successful.html");		
			}
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
		}
	}
}
			
//		try {
//			if (username.equalsIgnoreCase("employee") && (password.equalsIgnoreCase("employee")))  {
////				res.sendRedirect("http://localhost:9999/HelloServlets/login_successful.html");
//				// this version is based on ben's code
////				writer.write(mapper.writeValueAsString(user));
//				writer.write("employee_portal.html");
//			} else if (usi.loginUser(username, password)) {
//				System.out.println("login successful");
//				
//				// access entire user object
//				// this is a little clumsy, but it works, just make
//				// sure no duplicate usernames can exist
//				user = udi.accessUserObject(username);
//				
//				// add a cookie of the username
//				Cookie cookie = new Cookie("username", username);
//				res.addCookie(cookie);
//				
//				// start session
//				HttpSession session=req.getSession();
//				session.setAttribute("user", user);
//				
//				// return the redirect URL
//				writer.write("user_home.html");
//				
//			} else {
//				System.out.println("login failed");
//				writer.write("failed_login.html");	
//			}
//		
//		} catch (BankException e) {
//			Main.myLog.error(e.getMessage() + e.getStackTrace());
//		}



