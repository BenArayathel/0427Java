package com.lunch.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.transaction.dao.BankDaoImpl;
import log.Log;
import user.cust.account.models.User;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

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
		HttpSession session = req.getSession();

		System.out.println("\nthis is toString User: " + user.toString());
		
		// JACKSON OBJECT MAPPER ?? ??
		// http://tutorials.jenkov.com/java-json/jackson-objectmapper.html#jackson-objectmapper-example
		
		
		/**
		 * 	private String userName;
			private long contactPhone;
			private String password;
		
			private String user_id; // after db generates id
			private String email;
			private Date dob; // register for account
			private String soc; // register to be customer
			private Double balance;
			private int a_access;
		 */
		
		
		ObjectMapper mapper = new ObjectMapper();
		// "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
		String carJson =
			    "{ \"userName\" : \"" + user.getUserName() + "\","
			    + " \"contactPhone\" : \"" + user.getContactPhone() + "\","
			    + " \"password\" : \"" + user.getPassword() + "\","
			    + " \"user_id\" : \"" + user.getUser_id() + "\","
			    + " \"email\" : \"" + user.getEmail() + "\","
			    + " \"dob\" : \"" + user.getDob() + "\","
			    + " \"soc\" : \"" + user.getSoc() + "\","
			    + " \"balance\" : \"" + user.getBalance() + "\","
			    + " \"a_access\" : \"" + user.getA_access() + "\" }";

			try {
				// Ben's
			    //User mUser = mapper.readValue(req.getReader(), User.class);
				
				// tut's
				User mUser = mapper.readValue(carJson, User.class);

			    System.out.println("\nmUser.getUser_id() = " + mUser.getUser_id());
			    System.out.println("mUser.getBalance() = " + mUser.getBalance());
			    session.setAttribute("mappedStringUser", mUser);  // this seemingly does not work ... ... ...
			} catch (IOException e) {
			    e.printStackTrace();
			}
		
		//session.setAttribute("user", user);  // this seemingly does not work ... ... ...
		
		Log.logger("balance before setting with SESSION: " + user.getBalance());
//		Log.logger("This USER has: store in session obj... ... ...");
//		Log.logger(user.getSoc());
//		Log.logger("phoneContact: " + user.getContactPhone());
//		Log.logger("email: " + user.getEmail());
//		Log.logger(user.getUser_id());
//		Log.logger("access is: " + user.getA_access());
//		Log.logger("dob: " + user.getDob());
		
		session.setAttribute("access", user.getA_access());
		session.setAttribute("balance", user.getBalance());
		session.setAttribute("id", user.getUser_id());
		session.setAttribute("soc", user.getSoc());
		

		
		
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
