//package com.bank.servlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.bank.dao_implementation.UserDAOImplementation;
//import com.bank.main.Main;
//import com.bank.models.Account;
//import com.bank.models.User;
//import com.bank.presentation.AccountDeposit;
//import com.bank.presentation.AccountsView;
//import com.bank.service_implementation.AccountServiceImplementation;
//import com.bank.service_implementation.UserServiceImplementation;
//import com.bank.tools.BankException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@WebServlet("/deposit")
//public class DepositServlet {
//	
//	// POST for now, but updating account should probably be PUT
//	
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		// setup tools
//		UserServiceImplementation usi = new UserServiceImplementation();
//		AccountServiceImplementation asi = new AccountServiceImplementation();
//		ObjectMapper mapper = new ObjectMapper();
//		
//		// access session to get user
//		HttpSession session = req.getSession(false);
//		
//		// hardcoded values for now
//		String depositAccount = "test";
//		String depositAmount = "200";
//		
//		// check that the amount is in money format...
//				if (asi.validTransactionFormat(depositAmount)) {
//					// ...then that it isn't negative
//					if (Double.parseDouble(depositAmount) >= 0) {
//						// instantiate the account
////						Account account = asi.listAccountByNameAndUserID(account_name, user_id);
////						// create list of all the user's accounts...
//						List<Account> account_list = asi.listUserAccounts(username);
//						// ...then a list of all the names from those accounts
//						List<String> accountNames = new ArrayList<String>();
//						for (Account i : account_list) {
//							accountNames.add(i.getAccount_name());
//						}
//						// check if the name the user entered is in their accounts list
//						if (accountNames.contains(account_name)) {
//							asi.deposit(user, account_name, depositAmount);
//							Main.myLog.info("\nDeposit of $" + depositAmount + " complete!");
//							Main.myLog.info("-----------------------------------------------------");
//							AccountsView.view(user);							
//						} else {
//							Main.myLog.info("You don't have an account with that name.");
//							AccountDeposit.deposit(user);
//						}
//					} else {
//						Main.myLog.info("Enter an amount greater than $0.");
//						AccountDeposit.deposit(user);
//					}			
//				} else {
//					Main.myLog.info("Please format your input as either dollars or dollars and cents");
//					AccountDeposit.deposit(user);
//				}
//			}
//		
//		if (session == null) {
//			res.sendRedirect("/index.html");
//		} else {
//			// access user
//			User user = (User) session.getAttribute("user");
//			System.out.println(user);
//			
//			// prepare response
//			res.setContentType("application/json");
//			PrintWriter writer = res.getWriter();
//			
//			// send json response
//			try {
//				List<Account> userAccountsList = asi.listUserAccounts(user.getUsername());
//				writer.write(mapper.writeValueAsString(userAccountsList));
//				System.out.println(mapper.writeValueAsString(userAccountsList));
//				
//			} catch (BankException e) {
//				Main.myLog.error(e.getMessage() + e.getStackTrace());
//			}
//		}		
//		
//		
//	
//
////		
////		// map username and password onto a new User object
////		User user = mapper.readValue(req.getReader(), com.bank.models.User.class);
////		
////		System.out.println(user);
////	    System.out.println("username = " + user.getUsername());
////	    System.out.println("password = " + user.getPassword());
////	    
////		String username = user.getUsername();
////		String password = user.getPassword();
////				
////		res.setContentType("application/json");
////		PrintWriter writer = res.getWriter();
////				
////		try {
////			if (username.equalsIgnoreCase("employee") && (password.equalsIgnoreCase("employee")))  {
//////			res.sendRedirect("http://localhost:9999/HelloServlets/login_successful.html");
////				// this version is based on ben's code
//////			writer.write(mapper.writeValueAsString(user));
////				writer.write("employee_portal.html");
////			} else if (usi.loginUser(username, password)) {
////				System.out.println("login successful");
////				
////				// access entire user object
////				// this is a little clumsy, but it works, just make
////				// sure no duplicate usernames can exist
////				user = udi.accessUserObject(username);
////				
////				// add a cookie of the username
////				Cookie cookie = new Cookie("username", username);
////				res.addCookie(cookie);
////				
////				// start session
////				HttpSession session=req.getSession();
////				session.setAttribute("user", user);
////				
////				// return the redirect URL
////				writer.write("user_home.html");
////				
////			} else {
////				System.out.println("login failed");
////				writer.write("failed_login.html");	
////			}
////		
////		} catch (BankException e) {
////			Main.myLog.error(e.getMessage() + e.getStackTrace());
////		}
//	}
//
//}
