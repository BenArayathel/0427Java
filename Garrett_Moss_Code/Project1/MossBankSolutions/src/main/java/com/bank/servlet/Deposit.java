package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/deposit")
public class Deposit extends HttpServlet {
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
		 
		 String depositDetails = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		 JsonNode newDepositDetails = mapper.readTree(depositDetails);
		 
		 String accountNumber = newDepositDetails.get("accountNumber").textValue();
		 String deposit = newDepositDetails.get("deposit").textValue();
		 
		
		 
		 try {
			 
			 if((service.validDepositAmount(deposit)==true)) {
				 System.out.println("deposited");
			 Customer dataCustomer= dao.deposit(deposit, accountNumber);
			 writer.write(mapper.writeValueAsString(dataCustomer));
			
			 MainDriver.logger.info("Deposited Amount: "+ deposit + " by " + accountNumber);
			 } else if ((service.validDepositAmount(deposit)==false)){
				 writer.write(mapper.writeValueAsString("Invalid Deposit Amount"));
			 }
		 }catch(BankException e) {
			 MainDriver.logger.error(e.getStackTrace());
			 }
		 }
}