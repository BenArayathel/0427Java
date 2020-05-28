package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerController {
	
    private final static BankServiceController bankService = new BankServiceController();

	public static String home(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/*
		 * you should route guard for your controllers. We have to check for http method.
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
		 * you should route guard for your controllers. We have to check for http method.
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

			System.out.println("Account List: " + aList);	
		
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");					

			PrintWriter out=response.getWriter();
		
			out.write(new ObjectMapper().writeValueAsString(aList));				
		
			//destroying the session
			// session.invalidate(); 
		}

		return "";

	}

	
}
	
	
	

