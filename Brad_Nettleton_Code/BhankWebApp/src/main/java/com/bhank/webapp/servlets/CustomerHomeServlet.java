package com.bhank.webapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bhank.webapp.exception.BusinessException;
import com.bhank.webapp.model.Account;
import com.bhank.webapp.model.Customer;
import com.bhank.webapp.service.impl.AccountServiceImpl;
import com.bhank.webapp.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class CustomerHomeServlet
 */
public class CustomerHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerServiceImpl customerService = new CustomerServiceImpl();
	private AccountServiceImpl accountService = new AccountServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerHomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * System.out.println("Customer Menu"); System.out.println("Logged in as: " +
	 * customer.getName()); System.out.println("-------------");
	 * System.out.println("1)Apply for new account");
	 * System.out.println("2)View balance of an account");
	 * System.out.println("3)Withdrawal"); System.out.println("4)Deposit");
	 * System.out.println("5)Post money transfer");
	 * System.out.println("6)Accept money transfer");
	 * System.out.println("7)Return to main menu");
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("customerhome servlet doGet");
		String cId = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("customerId")) {
				cId = cookie.getValue();
				break;
			}
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("/index.html");
		} else {
			Customer customer = (Customer) session.getAttribute("currentCustomer");
			try {
				List<Account> accounts = accountService.selectAllAccountsByCustomer(customer.getId());
//				List<Account> accounts = accountService.selectAllAccountsByCustomer(cId);
				List<Account> activeAccounts = new ArrayList<>();
				for(Account account:accounts) {
					if(!account.isPending() && account.isApproved()) {
						activeAccounts.add(account);
					}
				}
				response.setContentType("application/json");
				PrintWriter writer = response.getWriter();
				ObjectMapper om = new ObjectMapper();
//				writer.write(om.writeValueAsString(accounts));
				System.out.println(om.writeValueAsString(activeAccounts));
				om.writeValue(response.getWriter(), activeAccounts);

			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		RequestDispatcher view = request.getRequestDispatcher("/customer_home.html");
//		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("/index.html");
		} else {
			Customer customer = (Customer) session.getAttribute("currentCustomer");
			} try {
				ObjectMapper om = new ObjectMapper();
				Account account = om.readValue(request.getReader(), com.bhank.webapp.model.Account.class);
				account = accountService.createAccount(account);
//				List<Account> accounts = accountService.selectAllAccountsByCustomer(cId);
				response.setContentType("application/json");
				PrintWriter writer = response.getWriter();
				
//				writer.write(om.writeValueAsString(account));
				System.out.println(om.writeValueAsString(account));
				om.writeValue(response.getWriter(), account);

			} catch (BusinessException e) {
				e.printStackTrace();
			}
	}

}
