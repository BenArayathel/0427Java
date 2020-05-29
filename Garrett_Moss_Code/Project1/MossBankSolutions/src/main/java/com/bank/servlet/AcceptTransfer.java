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

import com.bank.dao.impl.CustomerDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Customer;
import com.bank.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/acceptTransfer")
public class AcceptTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(MainDriver.class);
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
		 
		 String transferDetails = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		 JsonNode newTransferDetails = mapper.readTree(transferDetails);
		 
		 String fromAccountNumber = newTransferDetails.get("fromAccountNumber").textValue();
		 String transferAmount = newTransferDetails.get("transfer").textValue();
		 String toAccountNumber = newTransferDetails.get("toAccountNumber").textValue();
		 
		
		 
		 try {
			 
			 if((service.validDepositAmount(transferAmount)==true)) {
			 Customer dataCustomer= dao.acceptTransfer(toAccountNumber, transferAmount, fromAccountNumber);
			 writer.write(mapper.writeValueAsString(dataCustomer));
			
				 logger.info("Customer transfer from "+ fromAccountNumber + " to "+ toAccountNumber);
			 }
			 else {
				 writer.write(mapper.writeValueAsString("Invalid Transfer Amount"));
			 }
		 }catch(BankException e) {
				logger.error( e.getStackTrace());
			 }
		 }
}