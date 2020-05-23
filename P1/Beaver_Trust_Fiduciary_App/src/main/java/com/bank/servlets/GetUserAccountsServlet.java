package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/getaccounts")
public class GetUserAccountsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// setup
		ObjectMapper mapper = new ObjectMapper();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		List<Account> userAccounts = new ArrayList<Account>();
		
		// access session to get user
		HttpSession session = req.getSession(false);
		
		if (session == null) {
			res.sendRedirect("/index.html");
		} else {
			// access user
			User user = (User) session.getAttribute("user");
			System.out.println(user);
			
			// prepare response
			res.setContentType("application/json");
			PrintWriter writer = res.getWriter();
			
			// send json response
			try {
				List<Account> userAccountsList = asi.listUserAccounts(user.getUsername());
				writer.write(mapper.writeValueAsString(userAccountsList));
				System.out.println(mapper.writeValueAsString(userAccountsList));
				
//				for(Account i: userAccountsList) {
//					System.out.println("Account: " + i.getAccount_name() + " Balance: $" + i.getBalance());
//				}
				
			} catch (BankException e) {
				Main.myLog.error(e.getMessage() + e.getStackTrace());
			}
		}				
	}
}
		
//		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//			
//			UserServiceImplementation usi = new UserServiceImplementation();
//			UserDAOImplementation udi = new UserDAOImplementation();
//			
//			ObjectMapper mapper = new ObjectMapper();
//			
//			User user = mapper.readValue(req.getReader(), com.bank.models.User.class);
//			
//			System.out.println(user);
//		    System.out.println("username = " + user.getUsername());
//		    System.out.println("password = " + user.getPassword());
//		    
//			String username = user.getUsername();
//			String password = user.getPassword();
//					
//			res.setContentType("application/json");
//			PrintWriter writer = res.getWriter();
//					
//			try {
//				if (username.equalsIgnoreCase("employee") && (password.equalsIgnoreCase("employee")))  {
////				res.sendRedirect("http://localhost:9999/HelloServlets/login_successful.html");
//					// this version is based on ben's code
////				writer.write(mapper.writeValueAsString(user));
//					writer.write("employee_portal.html");
//				} else if (usi.loginUser(username, password)) {
//					System.out.println("login successful");
//					
//					// access entire user object
//					// this is a little clumsy, but it works, just make
//					// sure no duplicate usernames can exist
//					user = udi.accessUserObject(username);
//					
//					// add a cookie of the username
//					Cookie cookie = new Cookie("username", username);
//					res.addCookie(cookie);
//					
//					// start session
//					HttpSession session=req.getSession();
//					session.setAttribute("user", user);
//					
//					// return the redirect URL
//					writer.write("user_home.html");
//					
//				} else {
//					System.out.println("login failed");
//					writer.write("failed_login.html");	
//				}
//			
//			} catch (BankException e) {
//				Main.myLog.error(e.getMessage() + e.getStackTrace());
//			}
//		}
//	}

