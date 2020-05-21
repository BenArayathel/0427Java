package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao_implementation.UserDAOImplementation;
import com.bank.main.Main;
import com.bank.models.User;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	// Won't be using this, don't want password in URL
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Something happened");
		
		PrintWriter pw = res.getWriter();
		pw.write("<h1> We're sending something back now, inside of doGet</h1>");
		
		doPost(req,res);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		UserServiceImplementation usi = new UserServiceImplementation();
		UserDAOImplementation udi = new UserDAOImplementation();
		
//		ObjectMapper mapper = new ObjectMapper();
//		
//		User user = mapper.readValue(req.getReader(), com.bank.models.User.class);
//		
//		System.out.println(user);
//	    System.out.println("username = " + user.getUsername());
//	    System.out.println("password = " + user.getPassword());
//	    
//		String username = user.getUsername();
//		String password = user.getPassword();
		
		String username = "ethan1";
		String password = "ethan1";
		
		System.out.println(username);
		System.out.println(password);
		
//		System.out.println(user);
		
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
		
		// Problem for tomorrow: am i getting a DAO error from this code but not from my console app?
		// it is ojdbc error, but no error for console app, and persists in db from console
		
		try {
			usi.loginUser(username, password);
			System.out.println("worked");
			writer.write("user_home.html");
		} catch (BankException e) {
			System.out.println("nope");
			writer.write("failed_login.html");
			System.out.println(e);
		}
		
//		try {
//			if (username.equalsIgnoreCase("employee") && (password.equalsIgnoreCase("employee")))  {
////			res.sendRedirect("http://localhost:9999/HelloServlets/login_successful.html");
//				// this version is based on ben's code
////			writer.write(mapper.writeValueAsString(user));
//				writer.write("employee_portal.html");
//			} else if (usi.loginUser(username, password)) {
//				System.out.println("it RAN");
//				writer.write("user_home.html");
//			} else {
//				writer.write("failed_login.html");	
//			}
//		
//		} catch (BankException e) {
//			Main.myLog.error(e.getMessage() + e.getStackTrace());
//		}
	}
}


