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
			
		System.out.println("\nLogin1 running doPOST");
		
		// VALIDATION ?? ?? 
		 

		
		// call the db
		BankDaoImpl b = new BankDaoImpl();
		//BankDAO b = DAOUtilites.getBankDAO();
		// TRADING OUT USER FOR USER11
		//User user = new User(req.getParameter("username"), req.getParameter("pass"));
		//user.setUserName(username);
		//user.setPassword(password);
		
		/**
		 * user: 			regular POJO
		 * mUser: 			stringified Jackson OM
		 * balance: 		regular key:value pair
		 * 
		 * get info through the BODY not params : user11 : Sergio's help..................
		 */
		HttpSession session = req.getSession();
		
		ObjectMapper mapper2 = new ObjectMapper();
		// only once : req.getReader()
		User user = mapper2.readValue(req.getReader(), user.cust.account.models.User.class);
		

//		String userName = req.getParameter("userName");
//		String password = req.getParameter("password");
//		System.out.println("userName: " + userName);
//		System.out.println("password: " + password);
		//User user = new User(userName, password);
		
		
		//System.out.println("Line 61 : LoginServlet");
		//System.out.println("\nPOST VALUES:\nLoginServlet:\nthis is toString \nuser: " + user.toString());
		
		int access = -1; 
		access = b.login(user);		// changed to user11 : this is Sergio's help.. .. .. .. .. .. ... .. . ......
		
		System.out.println("\nLoginServlet:\nafter backend USER IS: " + user.toString());
		session.setAttribute("user", user);
		
		System.out.println("\n\naccess is " + access + "\n");

		System.out.println("line 77: here is a little proof: " + session.getAttribute("user"));

		
		
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
		String user_in_Json =
			    "{ \"userName\" : \"" + user.getUserName() + "\","
			    + " \"contactPhone\" : \"" + user.getContactPhone() + "\","
			    + " \"password\" : \"" + user.getPassword() + "\","
			    + " \"user_id\" : \"" + user.getUser_id() + "\","
			    + " \"email\" : \"" + user.getEmail() + "\","
			    + " \"dob\" : \"" + user.getDob() + "\","
			    + " \"soc\" : \"" + user.getSoc() + "\","
			    + " \"balance\" : \"" + user.getBalance() + "\","
			    + " \"a_access\" : \"" + user.getA_access() + "\" }";

//			try {
//				// Ben's
//			    //User mUser = mapper.readValue(req.getReader(), User.class);
//				
//				// tut's
//				//User mUser = mapper.readValue(user_in_Json, User.class);
//
//			    //System.out.println("\nObjectMapper: mUser.getUser_id() = " + mUser.getUser_id());
//			    //System.out.println("ObjectMapper: mUser.getBalance() = " + mUser.getBalance());
//			    //session.setAttribute("mappedStringUser", mUser);  // sort of works ... ... ...
//			} catch (IOException e) {
//			    e.printStackTrace();
//			}
			
			session.setAttribute("myCleanString", user_in_Json);
		
		
			Log.logger("user before setting with SESSION: " + user.getBalance());
		//Log.logger("regular POJO: balance before setting with SESSION: " + user.getBalance());
			
			
//		Log.logger("This USER has: store in session obj... ... ...");
//		Log.logger(user.getSoc());
//		Log.logger("phoneContact: " + user.getContactPhone());
//		Log.logger("email: " + user.getEmail());
//		Log.logger(user.getUser_id());
//		Log.logger("access is: " + user.getA_access());
//		Log.logger("dob: " + user.getDob());
		
		session.setAttribute("user", user);  // this seemingly does not work ... ... ...................................................................................................
		

		// I DONT THINK I NEED THESE ANYMORE
//		session.setAttribute("access", user.getA_access());
//		session.setAttribute("balance", user.getBalance());
//		session.setAttribute("id", user.getUser_id());
//		session.setAttribute("soc", user.getSoc());
		
		if (session == null) {
			System.out.println("\nSession was null");
		}
		

		
		
		if(access == 1) {					// soc: your a customer
			//return true;							
			// userName, password, user_id
			Log.logger("Customer privileges:");
			Log.logger("\n\n REDIRECT SHOULD RUN NOW !!:");
			// REDIRECT TO CUSTOMER
			//res.sendRedirect("http://localhost:9999/Password/custOptDirectory.html");
			//res.sendRedirect("custdirectory?id=" + user.getUser_id());
			res.getWriter().write("http://localhost:9999/Password/custOptDirectory.html");
		}
		
		else if(access == 2) {		// no soc: not a customer
			//return false;
			Log.logger("User privileges:");
//			req.getRequestDispatcher("http://localhost:9999/Password/userOptDirectory.html").forward(req, res);  // I was contemplating this
			//REDIRECT TO USER
			res.getWriter().write("http://localhost:9999/Password/userOptDirectory.html");
			//res.sendRedirect("http://localhost:9999/Password/userOptDirectory.html");
		}
		else if (access == 3) {
			Log.logger("Invalid");
			Log.logger("Please check credentials..");
			Log.logger("You may need to register if you have not");
			Log.logger("Redirecting back to Login...\n\n");
			
			// REDIRECT back to login
			res.getWriter().write("http://localhost:9999/Password/userLogin.html");
			//res.sendRedirect("http://localhost:9999/Password/userLogin.html");
			
		} else if (access == 0) {
			// IN THE CASE THAT ACCESS IS NULL
			Log.logger("ACCESS WAS LIKELY NULL:");
			res.getWriter().write("http://localhost:9999/Password/userLogin.html");
			//res.sendRedirect("http://localhost:9999/Password/userLogin.html");
		}
		else if (access == -1) {
			// IN THE CASE THAT ACCESS IS NULL
			Log.logger("IMPROPER RESPONSE FROM DAO: the -1 case");
			res.getWriter().write("http://localhost:9999/Password/userLogin.html");
			//res.sendRedirect("http://localhost:9999/Password/userLogin.html");
		} else {
			Log.logger("IMPROPER RESPONSE FROM DAO: the else case");
			res.getWriter().write("http://localhost:9999/Password/userLogin.html");
			//res.sendRedirect("http://localhost:9999/Password/userLogin.html");
		}
		
//		if (username.equals("lunch") && pass.equals("pass")) {
//			System.out.println("Success !");
//			res.sendRedirect("https://www.google.com/");
//		} else {
//			System.out.println("Invalid");
//		}
	}

}
