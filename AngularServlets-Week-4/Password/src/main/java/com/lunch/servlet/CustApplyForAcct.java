package com.lunch.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.transaction.dao.BankDaoImpl;
import user.cust.account.models.User;

@WebServlet("/acctapply")
public class CustApplyForAcct extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public CustApplyForAcct() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("\n\nCustApplyForAcct_SERVLET running...............");

		HttpSession session = request.getSession(false);

		if (session == null) {

			System.out.println("\nSession was null");

		} else {
			
			// GETTING BDAY AND STARTING BALANCE

			BankDaoImpl bankDaoImpl = new BankDaoImpl();
			User user = (User) session.getAttribute("user");

			System.out.println("bday is: " + request.getParameter("bday"));
			System.out.println("startbal is: " + request.getParameter("startbal"));

			String bday = request.getParameter("bday");
			user.setBalance(Double.parseDouble(request.getParameter("startbal")));
			
			
			// customerApplicationForAccount(User user, String dob, double balance)
			/**
			 * not handling input of string and what not on balance ??
			 */
			boolean success = bankDaoImpl.customerApplicationForAccount(user, bday, user.getBalance());
			System.out.println("\n\nsuccess value back from DB in CustApplyForAcct_SERVLET is: " + success);
			

			if (success) {
				
				System.out.println("inside true-if-block !!");
				System.out.println("Before setting session:  ");
				System.out.println("USER in CustApplyForAcct_SERVLET : \n" + user.toString());		// user gets fields populated at backend

				session.setAttribute("user", user);
				response.sendRedirect("http://localhost:9999/Password/userLogin.html"); // actually send them to login because they have get approved

			} else {
				
				// something went wrong at db
				System.out.println("\nELSE CONDITION:false OF CustApplyForAcct_SERVLET !!:  ");
				response.sendRedirect("http://localhost:9999/Password/appAccount.html");
			}


		}
		
	}

}
