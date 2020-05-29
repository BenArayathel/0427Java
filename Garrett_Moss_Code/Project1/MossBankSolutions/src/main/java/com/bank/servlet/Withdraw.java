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

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
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
		 
		 String withdrawDetails = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		 JsonNode newWithdrawDetails = mapper.readTree(withdrawDetails);
		 
		 String accountNumber = newWithdrawDetails.get("accountNumber").textValue();
		 String withdraw = newWithdrawDetails.get("withdraw").textValue();
		 String accountBalance = newWithdrawDetails.get("accountBalance").textValue();
		 
		
		 
		 try {
			 
			 if((service.validWithdrawAmount(withdraw, accountBalance)==true) && (Double.parseDouble(withdraw)>0)) {
			 Customer dataCustomer= dao.withdraw(accountNumber, withdraw);
			 writer.write(mapper.writeValueAsString(dataCustomer));
			
			 MainDriver.logger.info("Withdrawl from: "+ accountNumber);
			 } else if ((service.validWithdrawAmount(withdraw, accountBalance)==false) && (Double.parseDouble(withdraw)>0)){
				 writer.write(mapper.writeValueAsString("Invalid Withdraw Amount"));
			 }
			 else {
				 writer.write(mapper.writeValueAsString("Withdraw amount must be greater than 0"));
			 }
		 }catch(BankException e) {
			 MainDriver.logger.error(e.getStackTrace());
			 }
		 }
}
	
