package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.Account;
import com.company.model.Customer;
import com.company.model.Registration;
import com.company.view.BankApp;
import com.company.viewModel.AccountViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerController {
	
    private final static BankServiceController bankService = new BankServiceController();

	public static String home(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/*
		 *  Check for valid http method.
		 * You can also check stuff like, they are an admin. 
		 */
		if(!request.getMethod().equals("GET")) {
			return "/loginPage.html";
		}	
			
		System.out.println("Inside Customer Controller now...");
				
		HttpSession session=request.getSession(false);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");					

		//response.setContentType("text/html");
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		}else {
			
			
			Customer customer=(Customer) session.getAttribute("customer");

			/*
			customer.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
			customer.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
			customer.setFirstName(request.getParameter("firstName"));
			customer.setLastName(request.getParameter("lastName"));
			//customer.setBirthday(Double.parseDouble(request.getParameter("score"));
			customer.setBirthday(Date.valueOf(request.getParameter("birthday")));
			customer.setState(request.getParameter("state"));
			*/

			System.out.println("customerPage: " + customer);
			PrintWriter out=response.getWriter();
			
			out.write(new ObjectMapper().writeValueAsString(customer));				
			
			//destroying the session
			// session.invalidate(); 
						
		}
		return "";
	}
	
	
	public static String getAccountList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/*
		 *  Check for valid http method.
		 * You can also check stuff like, they are an admin. 
		 */
		if(!request.getMethod().equals("GET")) {
			return "/loginPage.html";
		}	
			
		System.out.println("Inside Customer Controller to get account list...");

		HttpSession session=request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		}else {			
			Customer customer=(Customer) session.getAttribute("customer");
			
			String custId = request.getParameter("customerId");
			System.out.println("custId : " + custId);
			
		
			try {
				// 1. load and register JDBC driver for MySQL (
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//2. then you can connect to oracle db through your usual DAO Implementations
				// response.getWriter().println("Connected to Oracle using thin driver");;
			} catch (Exception ex) {
				System.out.println(ex);
			}
		
			Account account = new Account();
			List<Account> aList = new ArrayList<Account>();
			aList = bankService.getAccountListByCustomerId(customer.getCustomerId());

			// *** Save List of User Accounts in SESSION *** //
			session.setAttribute("aList", aList);
			
			System.out.println("Account List: " + aList);	
		
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");					

			PrintWriter out=response.getWriter();
		
			out.write(new ObjectMapper().writeValueAsString(aList));				
		
		}

		return "";

	}

	public static String deposit(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/*
		 *  Check for valid http method.
		 * You can also check stuff like, they are an admin. 
		 */
		if(!request.getMethod().equals("POST")) {
			return "/loginPage.html";
		}	
			
		System.out.println("Inside Customer Controller to deposit amount...");

		HttpSession session=request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		}else {
			
			Customer customer=(Customer) session.getAttribute("customer");
			List<Account> aList = (ArrayList<Account>) session.getAttribute("aList");
		
			String accountId = request.getParameter("accountId");
//			registration.setLoginPassword(request.getParameter("loginPassword"));

			Account account = findAccountFromList(accountId, aList);

			// Move below to front-end validation.
			/*
			String warningMessage = "";
			
			if (account == null) {
				warningMessage = "WARNING: Invalid account ID. No deposit made.";
			}
			
			try {
				BigDecimal bigDecimalDeposit = new BigDecimal(request.getParameter("depositAmount"));
				// if input is negative...
				if (bigDecimalDeposit.compareTo(BigDecimal.ZERO) < 1) {
					warningMessage = "WARNING: Amount is not valid. No deposit made.";
				};
			} catch (Exception e) {
				warningMessage = "WARNING: Amount is not valid. No deposit made.";
			} 

			
			// If there is a warning message, it means data input is not valid.
			if (!warningMessage.isEmpty()) {
				return "";				
			}
			*/
			
			
//			try {
//				// 1. load and register JDBC driver for MySQL (
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//			} catch (Exception ex) {
//				System.out.println(ex);
//			}
		
			BigDecimal bigDecimalDeposit = new BigDecimal(request.getParameter("depositAmount"));
			account = bankService.depositAmount(account, bigDecimalDeposit);

			aList = bankService.getAccountListByCustomerId(customer.getCustomerId());

			System.out.println("Account List: " + aList);	
		
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");					

			PrintWriter out=response.getWriter();
		
			out.write(new ObjectMapper().writeValueAsString(aList));
			
			return "/customerPage.html";		

		}

		return "";

	}
	
	
	public static String withdraw(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/*
		 *  Check for valid http method.
		 * You can also check stuff like, they are an admin. 
		 */
		if(!request.getMethod().equals("POST")) {
			return "/loginPage.html";
		}	
			
		System.out.println("Inside Customer Controller to withdwraw amount...");

		HttpSession session=request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		}else {
			
			Customer customer=(Customer) session.getAttribute("customer");
			List<Account> aList = (ArrayList<Account>) session.getAttribute("aList");
		
			String accountId = request.getParameter("accountId");

			Account account = findAccountFromList(accountId, aList);
		
			BigDecimal bigDecimalWithdraw = new BigDecimal(request.getParameter("withdrawAmount"));
			
			// Update chosen account
			account = bankService.withdrawAmount(account, bigDecimalWithdraw);

//			No need returning any data.
//			aList = bankService.getAccountListByCustomerId(customer.getCustomerId());
//
//			System.out.println("Account List: " + aList);	
//		
//			response.setContentType("application/json");
//			response.setCharacterEncoding("UTF-8");					
//
//			PrintWriter out=response.getWriter();
//		
//			out.write(new ObjectMapper().writeValueAsString(aList));
			
			return "/customerPage.html";		

		}

		return "";

	}

	public static String transfer(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// Check for valid http method.
		if(!request.getMethod().equals("POST")) {
			return "/loginPage.html";
		}	
			
		System.out.println("Inside Customer Controller to transfer amount...");

		HttpSession session=request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		}else {
			
			// get customer from session
			Customer customer=(Customer) session.getAttribute("customer");
			
			// get list of accounts from session
			List<Account> aList = (ArrayList<Account>) session.getAttribute("aList");
		
			// From Source
			String fromAccountId = request.getParameter("fromAccount");
			Account fromAccount = findAccountFromList(fromAccountId, aList);
			BigDecimal bigDecimalTransfer = new BigDecimal(request.getParameter("transferAmount"));
		
			// To Destination Account
			String toAccountId = request.getParameter("toAccount");

			fromAccount = bankService.transferAmount(fromAccount, toAccountId, bigDecimalTransfer);
	
			return "/customerPage.html";		

		}

		return "";

	}

	
	public static String signOff(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/*
		 *  Check for valid http method.
		 * You can also check stuff like, they are an admin. 
		 */
		if(!request.getMethod().equals("GET")) {
			return "/loginPage.html";
		}	
			
		System.out.println("Inside Customer Controller to signOff...");
		
		HttpSession session=request.getSession(false);
	
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		}else {
			// Destroy session
			session.invalidate(); 

			// Destroy session
			session.invalidate(); 

			return "/loginPage.html";		
		}

		return "";

	}	

	
    // Helper method
    public static Account findAccountFromList(
    		  String accountId, List<Account> aList) {
    	for (Account account : aList) {
    		if (account.getAccountId().equals(accountId)) {
    			return account;
    		}
    	}
    	return null;		
    }

}
	
	
	

