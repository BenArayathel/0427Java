package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.Customer;
import com.company.model.Registration;
import com.company.service.ServiceLayer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginController {

	
	/*
	 * Perform business logic 
	 */
	
    private final static BankServiceController bankService = new BankServiceController();


	public static String login(HttpServletRequest request, HttpServletResponse response) throws IOException {

			/*
			 * you should route guard for your controllers. We have to check for http method.
			 * You can also check stuff like, they are an admin. 
			 */
		
		
		if(!request.getMethod().equals("POST")) {
			return "/loginPage.html";
		}	
		
		
		try {
			// 1. load and register JDBC driver for MySQL (
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. then you can connect to oracle db through your usual DAO Implementations
			// response.getWriter().println("Connected to Oracle using thin driver");;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		//doGet(request, response);
		
		System.out.println("Inside LoginController now...");
		
		// Start processing request and convert string response into java object.
		ObjectMapper om = new ObjectMapper();
		Registration registration = new Registration();
		
		registration.setLoginName(request.getParameter("loginName"));
		registration.setLoginPassword(request.getParameter("loginPassword"));

//		registration = om.readValue(request.getReader(),com.company.model.Registration.class);
				
		// check output.
		System.out.println(registration);
		
		System.out.println(registration.getLoginName());
		System.out.println(registration.getLoginPassword());

		// Process the request (now a java object)
		Customer customer = new Customer();
		customer = bankService.validateLogin(registration.getLoginName(), registration.getLoginPassword());
		
		System.out.println("Servelet: " + customer);
		
		if (customer != null) {
			System.out.println("Inside customer is not null and try to redirect...");

			// convert java object to string that can be sent to front end (as a response)
			// set response content type to json format.
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");					
			PrintWriter out = response.getWriter();

			out.write(new ObjectMapper().writeValueAsString(customer));			
			
			// Save session
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			
			
			if (customer.getCustomerId() == 99999) {
				return "/api/admin";
			} else {	
				//response.sendRedirect("/BankWebApp/src/main/webapp/customerPage.html");
        		//return "/api/customer";	
				return "/customerPage.html";		

			}
		} else {
			return "/loginPage.html";
		}
		
			
	}

	
}
