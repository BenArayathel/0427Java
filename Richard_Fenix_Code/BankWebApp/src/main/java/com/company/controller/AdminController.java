package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.Account;
import com.company.model.Customer;
import com.company.model.Transaction;
import com.company.view.BankApp;
import com.company.viewModel.AccountViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminController {
	
    private final static BankServiceController bankService = new BankServiceController();

	public static String home(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/*
		 *  Check for valid http method.
		 * You can also check stuff like, they are an admin. 
		 */
		if(!request.getMethod().equals("GET")) {
			return "/loginPage.html";
		}	
			
		System.out.println("Inside Admin Controller.");
				
		HttpSession session=request.getSession(false);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");					

		//response.setContentType("text/html");
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		}else {
			
			Customer customer=(Customer) session.getAttribute("customer");
			
			System.out.println("customerPage: " + customer);
			PrintWriter out=response.getWriter();
			
			out.write(new ObjectMapper().writeValueAsString(customer));				
			
		}
		return "";
	}
	
	public static String viewCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//  Check for valid http method.
		if(!request.getMethod().equals("GET")) {
			return "/loginPage.html";
		}	
					
		HttpSession session=request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		} else {

			String accountId = request.getParameter("accountId");
			
	        AccountViewModel avm = bankService.getCustomerAccountDetail(accountId);
	        
			if (avm != null) {
				
				// *** Save Account View Model in SESSION *** //
				session.setAttribute("avm", avm);

				// convert java object to string that can be sent to front end (as a response)
				// set response content type to json format.
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");	
				
				System.out.println("customer details: " + avm);
				
				PrintWriter out = response.getWriter();
				out.write(new ObjectMapper().writeValueAsString(avm));			
				
			}
			else {
			    System.out.println("Account Detail not found.");
				return "/adminPage.html";
			}
	       		
		}
		return "";
	}

	public static String approveAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {


		 // Check for valid http method.		
		if(!request.getMethod().equals("POST")) {
			return "/loginPage.html";
		}	
					
		HttpSession session=request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		} else {

			String accountId = request.getParameter("custAccountId");
			
	        AccountViewModel avm = bankService.approveAccount(accountId);
	        
			if (avm != null) {
				
				// *** Save Account View Model in SESSION *** //
				session.setAttribute("avm", avm);

				/* No need to return
				// convert java object to string that can be sent to front end (as a response)
				// set response content type to json format.
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");	
				
				System.out.println("customer details: " + avm);
				
				PrintWriter out = response.getWriter();
				out.write(new ObjectMapper().writeValueAsString(avm));
				*/
				
				return "/adminPage.html";
				
			}
			else {
			    System.out.println("Account Detail not found.");
				return "/adminPage.html";
			}
	       		
		}
		return "";
	}
	
	public static String getTransactions(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		 //  Check for valid http method.
		if(!request.getMethod().equals("GET")) {
			return "/loginPage.html";
		}	
			
		System.out.println("Inside Admin Controller to get transaction list...");

		HttpSession session=request.getSession(false);
		
		if(session==null) {
			response.sendRedirect("/loginPage.html");
		}else {					

			try {
				// 1. load and register JDBC driver for MySQL (
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//2. then you can connect to oracle db through your usual DAO Implementations
				// response.getWriter().println("Connected to Oracle using thin driver");;
			} catch (Exception ex) {
				System.out.println(ex);
			}
		
			Transaction transaction = new Transaction();
			List<Transaction> tList = new ArrayList<Transaction>();
			tList = bankService.getLogList();
			
			System.out.println("Account List: " + tList);	
		
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");					

			PrintWriter out=response.getWriter();
		
			out.write(new ObjectMapper().writeValueAsString(tList));				
		
		}

		return "";

	}
	

}
