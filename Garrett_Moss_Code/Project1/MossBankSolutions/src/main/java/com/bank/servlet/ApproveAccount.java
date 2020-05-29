package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bank.dao.impl.EmployeeDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.service.impl.CustomerServiceImpl;
import com.bank.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/approveAccount")
public class ApproveAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = Logger.getLogger(MainDriver.class);
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet");
		response.sendRedirect("Customer.html");
}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost"); 
		  response.setContentType("application/json");
		  EmployeeServiceImpl service = new EmployeeServiceImpl();
		 EmployeeDaoImpl dao = new EmployeeDaoImpl();
		 ObjectMapper mapper = new ObjectMapper();
		 PrintWriter writer = response.getWriter();
		 
		 String approveDetails = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		 JsonNode newApproveDetails = mapper.readTree(approveDetails);
		 
		 String accountNumber = newApproveDetails.get("accountNumber").textValue();
		 String approveAccount = newApproveDetails.get("approveAccount").textValue();
		 
		
		 
		 try {
			 
			 if((service.approveAccount(approveAccount, accountNumber)==null)) {
			 Customer dataCustomer= dao.approveAccount(approveAccount, accountNumber);
			 writer.write(mapper.writeValueAsString("Account: " + accountNumber + " Has Been Approved"));
			
				 logger.info("Account Approved: "+ accountNumber);
			 }
			 else {
				 writer.write(mapper.writeValueAsString("Account Has Already Been Approved or Rejected"));
			 }
		 }catch(BankException e) {
				 logger.error(e.getStackTrace());
			 }
		 }
}
