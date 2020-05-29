package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/viewBalance")
public class ViewBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet");
		response.sendRedirect("Customer.html");
}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost"); 
		  response.setContentType("application/json");
		  CustomerServiceImpl service = new CustomerServiceImpl();
		 CustomerDaoImpl dao = new CustomerDaoImpl();
		 ObjectMapper mapper = new ObjectMapper();
		 PrintWriter writer = response.getWriter();
		 
		 Customer customer = mapper.readValue(request.getReader(), com.bank.model.Customer.class);
		 
		 String accountNumber = customer.getAccountNumber();
		 
		
		 
		 try {
			 
			 Customer dataCustomer= dao.viewBalance(accountNumber);
			 writer.write(mapper.writeValueAsString(dataCustomer));
			
			 MainDriver.logger.info(("Customer  account Balance: "+ dataCustomer + " from account: " + accountNumber));

		 }catch(BankException e) {
			 MainDriver.logger.error(e.getStackTrace());
			 }
		 }
}
		 