package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
//		try {
//			// 1. load and register JDBC driver for MySQL (
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			//2. then you can connect to oracle db through your usual DAO Implementations
//			// response.getWriter().println("Connected to Oracle using thin driver");;
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
		
		
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
			
//			out.print("<h3>Hello "+user.getFname()+" ...  Here are your details</h3>");
//			out.print("<h3>"+user+"</h3>");
//			out.print("<h3>session id = "+session.getId()+"</h3>");
//			out.print("<h3>session created at "+new Date(session.getCreationTime())+"</h3>");
		//	session.setMaxInactiveInterval(interval);\ destroys session after some inactive internal
			session.invalidate(); //destroying the session
						
		}
		return "";
	}
}
	
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
	
	

