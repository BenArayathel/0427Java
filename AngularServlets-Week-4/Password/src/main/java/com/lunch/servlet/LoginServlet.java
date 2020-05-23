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
			
		System.out.println("\nlogin POST");
		
		// VALIDATION ?? ?? 
		 
//		System.out.println("GetParameter Test 4 fun ... .....");
//		String username = req.getParameter("userName");
//		String pass = req.getParameter("password");
//		System.out.println("userName: " + username);
//		System.out.println("password: " + pass);
		
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
		User user11 = mapper2.readValue(req.getReader(), user.cust.account.models.User.class);
		System.out.println("Line 61 : LoginServlet");
		System.out.println("\nPOST VALUES:\nLoginServlet:\nthis is toString \nuser11: " + user11.toString());
		
		int access = -1; 
		access = b.login(user11);		// changed to user11 : this is Sergio's help.. .. .. .. .. .. ... .. . ......
		
		System.out.println("\nLoginServlet:\nafter backend user11: " + user11.toString());
		session.setAttribute("user11", user11);
		
		System.out.println("\n\naccess is " + access + "\n");

		

		
		
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
			    "{ \"userName\" : \"" + user11.getUserName() + "\","
			    + " \"contactPhone\" : \"" + user11.getContactPhone() + "\","
			    + " \"password\" : \"" + user11.getPassword() + "\","
			    + " \"user_id\" : \"" + user11.getUser_id() + "\","
			    + " \"email\" : \"" + user11.getEmail() + "\","
			    + " \"dob\" : \"" + user11.getDob() + "\","
			    + " \"soc\" : \"" + user11.getSoc() + "\","
			    + " \"balance\" : \"" + user11.getBalance() + "\","
			    + " \"a_access\" : \"" + user11.getA_access() + "\" }";

			try {
				// Ben's
			    //User mUser = mapper.readValue(req.getReader(), User.class);
				
				// tut's
				User mUser = mapper.readValue(user_in_Json, User.class);

			    System.out.println("\nObjectMapper: mUser.getUser_id() = " + mUser.getUser_id());
			    System.out.println("ObjectMapper: mUser.getBalance() = " + mUser.getBalance());
			    session.setAttribute("mappedStringUser", mUser);  // sort of works ... ... ...
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			session.setAttribute("myCleanString", user_in_Json);
		
		
			Log.logger("user11 before setting with SESSION: " + user11.getBalance());
		//Log.logger("regular POJO: balance before setting with SESSION: " + user.getBalance());
			
			
//		Log.logger("This USER has: store in session obj... ... ...");
//		Log.logger(user.getSoc());
//		Log.logger("phoneContact: " + user.getContactPhone());
//		Log.logger("email: " + user.getEmail());
//		Log.logger(user.getUser_id());
//		Log.logger("access is: " + user.getA_access());
//		Log.logger("dob: " + user.getDob());
		
		session.setAttribute("user", user11);  // this seemingly does not work ... ... ...................................................................................................
		
		
		session.setAttribute("access", user11.getA_access());
		session.setAttribute("balance", user11.getBalance());
		session.setAttribute("id", user11.getUser_id());
		session.setAttribute("soc", user11.getSoc());
		

		
		
		if(access == 1) {					// soc: your a customer
			//return true;							
			// userName, password, user_id
			Log.logger("Customer privileges:");
			Log.logger("\n\n REDIRECT SHOULD RUN NOW !!:");
			// REDIRECT TO CUSTOMER
			res.getWriter().write("http://localhost:9999/Password/custOptDirectory.html");
			//res.sendRedirect("http://localhost:9999/Password/custOptDirectory.html");
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
			res.sendRedirect("http://localhost:9999/Password/userLogin.html");
			
		} else if (access == 0) {
			// IN THE CASE THAT ACCESS IS NULL
			Log.logger("ACCESS WAS LIKELY NULL:");
			res.sendRedirect("http://localhost:9999/Password/userLogin.html");
		}
		else if (access == -1) {
			// IN THE CASE THAT ACCESS IS NULL
			Log.logger("IMPROPER RESPONSE FROM DAO: the -1 case");
			res.sendRedirect("http://localhost:9999/Password/userLogin.html");
		} else {
			Log.logger("IMPROPER RESPONSE FROM DAO: the else case");
			res.sendRedirect("http://localhost:9999/Password/userLogin.html");
		}
		
//		if (username.equals("lunch") && pass.equals("pass")) {
//			System.out.println("Success !");
//			res.sendRedirect("https://www.google.com/");
//		} else {
//			System.out.println("Invalid");
//		}
	}

}
