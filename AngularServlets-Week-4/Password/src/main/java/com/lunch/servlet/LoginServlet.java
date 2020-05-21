package com.lunch.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.transaction.dao.BankDAO;
import bank.transaction.dao.BankDaoImpl;
import connection.utilities.DAOUtilites;
import log.Log;
import user.cust.account.models.User;

public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		 System.out.println("login GET");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		System.out.println("login POST");
		
		// VALIDATION ?? ?? 
		 
		String username = req.getParameter("username");
		String pass = req.getParameter("pass");
		
		// call the db
		BankDaoImpl b = new BankDaoImpl();
		//BankDAO b = DAOUtilites.getBankDAO();
		User user = new User(req.getParameter("username"), req.getParameter("pass"));
		//user.setUserName(username);
		//user.setPassword(password);
		
		System.out.println("username: " + username);
		System.out.println("pass is: " + pass);
		
		int access = b.login(user);
		req.getSession().setAttribute("user", user);
		
//		Log.logger("This USER has: store in session obj... ... ...");
//		Log.logger(user.getSoc());
//		Log.logger("phoneContact: " + user.getContactPhone());
//		Log.logger("email: " + user.getEmail());
//		Log.logger(user.getUser_id());
//		Log.logger("access is: " + user.getA_access());
//		Log.logger("balance: " + user.getBalance());
//		Log.logger("dob: " + user.getDob());
		
		
		if(access == 1) {					// soc: your a customer
			//return true;							
			// userName, password, user_id
			Log.logger("Customer privileges:");
			
			// REDIRECT TO CUSTOMER
			res.sendRedirect("http://localhost:9999/Password/custOptDirectory.html");
		}
		
		else if(access == 2) {		// no soc: not a customer
			//return false;
			Log.logger("User privileges:");
//			req.getRequestDispatcher("http://localhost:9999/Password/userOptDirectory.html").forward(req, res);  // I was contemplating this
			//REDIRECT TO USER
			res.sendRedirect("http://localhost:9999/Password/userOptDirectory.html");
		}
		else if (access == 3) {
			Log.logger("Invalid");
			Log.logger("Please check credentials..");
			Log.logger("You may need to register if you have not");
			Log.logger("Redirecting back to Login...\n\n");
			
			// REDIRECT back to login
			res.sendRedirect("userLogin.html");
			
		} else {
			// I AM NOT SURE 0 IS POSSIBLE
		}
		
//		if (username.equals("lunch") && pass.equals("pass")) {
//			System.out.println("Success !");
//			res.sendRedirect("https://www.google.com/");
//		} else {
//			System.out.println("Invalid");
//		}
	}

}
