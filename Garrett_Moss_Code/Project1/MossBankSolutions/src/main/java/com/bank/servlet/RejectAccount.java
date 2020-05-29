package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.impl.EmployeeDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rejectAccount")
public class RejectAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet");
		response.sendRedirect("Customer.html");
}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost Reject"); 
		  response.setContentType("application/json");
		  EmployeeServiceImpl service = new EmployeeServiceImpl();
		 EmployeeDaoImpl dao = new EmployeeDaoImpl();
		 ObjectMapper mapper = new ObjectMapper();
		 PrintWriter writer = response.getWriter();
		 
		 String rejectDetails = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		 JsonNode newRejectDetails = mapper.readTree(rejectDetails);
		 
		 String accountNumber = newRejectDetails.get("accountNumber").textValue();
		 String rejectAccount = newRejectDetails.get("rejectAccount").textValue();
		 
		
		 
		 try {
			 
			 if((service.rejectAccount(rejectAccount, accountNumber)==null)) {
			 Customer dataCustomer= dao.rejectAccount(rejectAccount, accountNumber);
			 writer.write(mapper.writeValueAsString("Account: " + accountNumber + " Has Been Rejected"));
			
			 MainDriver.logger.info("Account rejected: "+ accountNumber);
			 }
			 else {
				 writer.write(mapper.writeValueAsString("Account Has Already Been Approved or Rejected"));
			 }
		 }catch(BankException e) {
			 MainDriver.logger.error(e.getStackTrace());
			 }
		 }
}