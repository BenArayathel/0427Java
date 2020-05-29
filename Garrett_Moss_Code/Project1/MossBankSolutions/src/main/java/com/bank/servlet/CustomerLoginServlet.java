package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.dao.impl.EmployeeDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.impl.CustomerServiceImpl;
import com.bank.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/login")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet");
		response.sendRedirect("login.html");
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
		 
		
		 
		 try {
			 
			 Customer dataCustomer= dao.loginVerification(customerUsername);
			 
			 String dataUsername = dataCustomer.getUsername();
			 String dataPassword = dataCustomer.getPassword();
			 
			 if(dataUsername.equals(customerUsername)&& dataPassword.equals(customerPassword)) {
				 
				 //writer.write("Employee.html");
				 MainDriver.logger.info("Customer logged in: " + customerUsername);
				 writer.write("Customer.html");
			 } else {
				 MainDriver.logger.error("Wrong Login Information");
				 writer.write("login.html");
			 } 
		 }catch(BankException e) {
				 MainDriver.logger.error(e.getStackTrace());
			 }
		 }
}
		 