package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.dao.impl.EmployeeDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.service.impl.CustomerServiceImpl;
import com.bank.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/viewAccount")
public class ViewCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet");
		response.sendRedirect("Employee.html");
}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost"); 
		  response.setContentType("application/json");
		  EmployeeServiceImpl service = new EmployeeServiceImpl();
		  EmployeeDaoImpl dao = new EmployeeDaoImpl();
		 ObjectMapper mapper = new ObjectMapper();
		 PrintWriter writer = response.getWriter();
		 
		 Customer customer = mapper.readValue(request.getReader(), com.bank.model.Customer.class);
		 
		 String accountNumber = customer.getAccountNumber();
		 
		
		 
		 try {
			 
			 Customer dataCustomer= dao.viewCustomerAccount(accountNumber);
			 writer.write(mapper.writeValueAsString(dataCustomer));
			
			 MainDriver.logger.info("Customer viewed "+ accountNumber);

		 }catch(BankException e) {
			 MainDriver.logger.error(e.getStackTrace());
			 }
		 }
}
