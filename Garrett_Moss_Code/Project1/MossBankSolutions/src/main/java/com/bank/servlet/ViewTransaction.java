package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import com.bank.model.Employee;
import com.bank.model.Transactions;
import com.bank.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/viewTransaction")
public class ViewTransaction extends HttpServlet {
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
		 
		 String transactionDetails = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		 JsonNode newTransactionDetails = mapper.readTree(transactionDetails);
		 
		 String choice = newTransactionDetails.get("choice").textValue();
		 
		
		 
		 try {
			 if(service.validTransactionChoice(choice)==true) {
			 List<Transactions> dataTransaction= dao.viewTransactionLogs(choice);
			 writer.write(mapper.writeValueAsString(dataTransaction));
			
			 MainDriver.logger.info("Transaction Log accessed");
			 } else {
				 writer.write(mapper.writeValueAsString("Invalid Choice"));
			 }
		 }catch(BankException e) {
			 MainDriver.logger.error(e.getStackTrace());
			 }
		 }
}