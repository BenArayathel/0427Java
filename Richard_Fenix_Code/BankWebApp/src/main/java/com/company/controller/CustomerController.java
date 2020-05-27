package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	
//		if(!request.getMethod().equals("GET")) {
//			return "/index.html";
//		}	
			
		System.out.println("Inside Customer Controller now...");
		
		return "/customerPage.html";
	
/*****************		
		try {
			// 1. load and register JDBC driver for MySQL (
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. then you can connect to oracle db through your usual DAO Implementations
			// response.getWriter().println("Connected to Oracle using thin driver");;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		// Start processing request and convert string response into java object.
		ObjectMapper om = new ObjectMapper();
		Customer customer;
	
		customer = om.readValue(request.getReader(),com.company.model.Customer.class);
				
		// check output.
		System.out.println(customer);
		
	//	System.out.println(registration.getLoginName());
	//	System.out.println(registration.getLoginPassword());
	//
	//	// Process the request (now a java object)
	//	Customer customer = new Customer();
	//	customer = bankService.validateLogin(registration.getLoginName(), registration.getLoginPassword());
		
		// set response content type to json format.
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");		
		
		PrintWriter out = response.getWriter();
		
		System.out.println("Customer : " + customer);
	
		if (customer != null) {
			System.out.println("Inside customer is not null and try to redirect...");
			// convert java object to string that can be sent to front end (as a response)
			out.write(new ObjectMapper().writeValueAsString(customer));			
				
			if (customer.getCustomerId() == 99999) {
				return "api/admin/home";
			} else {	
				return "customerPage.html";
			}
		} else {
			return "index.html";
		}
*******************************/
	}
	
}
