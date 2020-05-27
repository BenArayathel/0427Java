package com.lunch.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.cust.account.models.User;

import bank.transaction.dao.BankDaoImpl;
import log.Log;

@WebServlet("/ureg")
public class UserRegistration_Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public UserRegistration_Servlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("\n\nUserRegistration_Servlet running...............");
		BankDaoImpl b = new BankDaoImpl();
		HttpSession session = request.getSession();
		
		if (session == null) {
			System.out.println("\nSession was null");
		} else {
			
			User user = new User(request.getParameter("username"), request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			user.setContactPhone(Integer.parseInt(request.getParameter("contact")));
			
			
			System.out.println("username is: " + request.getParameter("username"));
			System.out.println("password is: " + request.getParameter("password"));
			System.out.println("email is: " + request.getParameter("email"));
			System.out.println("contact is: " + request.getParameter("contact"));
			
			
			boolean success = b.createUser(user);
			
			if (success) {
				
				
				System.out.println("\nUser after backend");
				System.out.println(user.toString());
				session.setAttribute("user", user);
				
				//System.out.println("\n\naccess is " + access + "\n");
				System.out.println("Proof attribute was set in session: \n" + session.getAttribute("user"));
				
				response.sendRedirect("http://localhost:9999/Password/userLogin.html");
				
			} else {
				
				response.sendRedirect("http://localhost:9999/Password/userReg.html");
				
			}
			
		}
		
		

		

		

		
		
//		if(access == 1) {					// soc: your a customer
//			//return true;							
//			// userName, password, user_id
//			Log.logger("Customer privileges:");
//			Log.logger("\n\n REDIRECT SHOULD RUN NOW !!:");
//			// REDIRECT TO CUSTOMER
//			response.sendRedirect("http://localhost:9999/Password/custOptDirectory.html");
//			//res.sendRedirect("custdirectory?id=" + user.getUser_id());
//			//res.getWriter().write("http://localhost:9999/Password/custOptDirectory.html");
//		}
//		
//		else if(access == 2) {		// no soc: not a customer
//			//return false;
//			Log.logger("User privileges:");
////			req.getRequestDispatcher("http://localhost:9999/Password/userOptDirectory.html").forward(req, res);  // I was contemplating this
//			//REDIRECT TO USER
//			//res.getWriter().write("http://localhost:9999/Password/userOptDirectory.html");
//			response.sendRedirect("http://localhost:9999/Password/userOptDirectory.html");
//		}
//		else if (access == 3) {
//			Log.logger("Invalid");
//			Log.logger("Please check credentials..");
//			Log.logger("You may need to register if you have not");
//			Log.logger("Redirecting back to Login...\n\n");
//			
//			// REDIRECT back to login
//			//res.getWriter().write("http://localhost:9999/Password/userLogin.html");
//			response.sendRedirect("http://localhost:9999/Password/userLogin.html");
//			
//		} else if (access == 0) {
//			// IN THE CASE THAT ACCESS IS NULL
//			Log.logger("ACCESS WAS LIKELY NULL:");
//			//res.getWriter().write("http://localhost:9999/Password/userLogin.html");
//			response.sendRedirect("http://localhost:9999/Password/userLogin.html");
//		}
//		else if (access == -1) {
//			// IN THE CASE THAT ACCESS IS NULL
//			Log.logger("IMPROPER RESPONSE FROM DAO: the -1 case");
//			//res.getWriter().write("http://localhost:9999/Password/userLogin.html");
//			response.sendRedirect("http://localhost:9999/Password/userLogin.html");
//		} else {
//			Log.logger("IMPROPER RESPONSE FROM DAO: the else case");
//			//res.getWriter().write("http://localhost:9999/Password/userLogin.html");
//			response.sendRedirect("http://localhost:9999/Password/userLogin.html");
//		}
		
		
	}

}
