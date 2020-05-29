package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/newAccount")
public class NewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet");
		response.sendRedirect("newCustomer.html");
}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost"); 
		  response.setContentType("application/json");
		  
		 CustomerDaoImpl dao = new CustomerDaoImpl();
		 ObjectMapper mapper = new ObjectMapper();
		 PrintWriter writer = response.getWriter();
		 
		 Customer customer = mapper.readValue(request.getReader(), com.bank.model.Customer.class);
		 
		 String customerUsername = customer.getUsername();
		 String customerPassword = customer.getPassword();
		 String customerBalance = customer.getAccountBalance();
		
		 
		 try {
			 
			 Customer dataCustomer= dao.createCustomerAccount(customer);

			 MainDriver.logger.info("New Account applied to: "+ customerUsername);
				 writer.write("Here is your new Account Number" + dataCustomer);
 
		 }catch(BankException e) {
			 MainDriver.logger.error(e.getStackTrace());
			 }
		 }
}
		 