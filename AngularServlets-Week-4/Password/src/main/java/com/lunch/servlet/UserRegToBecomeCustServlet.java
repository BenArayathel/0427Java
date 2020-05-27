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

@WebServlet("/uregtobecust")
public class UserRegToBecomeCustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserRegToBecomeCustServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("\n\nCONGRATULATIONS...............");
		System.out.println("UserRegToBecomeCustServlet running...............");
		System.out.println("UserRegToBecomeCustServlet running...............\n\n");
		HttpSession session = request.getSession(false);

		if (session == null) {

			System.out.println("\nSession was null");

		} else {

			BankDaoImpl bankDaoImpl = new BankDaoImpl();
			User user = (User) session.getAttribute("user");

			System.out.println("social is: " + request.getParameter("social"));

			user.setSoc(request.getParameter("social"));
			boolean success = bankDaoImpl.userRegistrationToBecomeCustomer(user);

			if (success) {

				System.out.println("\nBefore setting session:  ");
				System.out.println("USER in UserRegToBecomeCustServlet : \n" + user.toString());
				session.setAttribute("user", user);
				response.sendRedirect("http://localhost:9999/Password/userLogin.html");

			} else {
				
				// something went wrong at db
				response.sendRedirect("http://localhost:9999/Password/appBecomeCust.html");
			}


		}

	}

}
