package com.bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bank.dao.impl.EmployeeDaoImpl;
import com.bank.exception.BankException;
import com.bank.main.MainDriver;
import com.bank.model.Employee;
import com.bank.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/employeeLogin")
public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	System.out.println("doGet");
		response.sendRedirect("employeeLogin.html");
}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost"); 
		  response.setContentType("application/json");
		 EmployeeDaoImpl dao = new EmployeeDaoImpl();
		 ObjectMapper mapper = new ObjectMapper();
		 PrintWriter writer = response.getWriter();
		 
		 Employee employee = mapper.readValue(request.getReader(), com.bank.model.Employee.class);
		 
		 String employeeUsername = employee.getUsername();
		 String employeePassword = employee.getPassword();
		 
		
		 
		 try {
			 
			 Employee dataEmployee = dao.loginVerification(employeeUsername);
			 
			 String dataUsername = dataEmployee.getUsername();
			 String dataPassword = dataEmployee.getPassword();
			 
			 if(dataUsername.equals(employeeUsername)&& dataPassword.equals(employeePassword)) {
				 
				 //writer.write("Employee.html");
				 MainDriver.logger.info("Employee successfully logged in: "+ employeeUsername);
				 writer.write("Employee.html");
			 } else {
				 MainDriver.logger.info("Credentials DO NOT match DB!");
				 writer.write("employeeLogin.html");
			 } 
		 }catch(BankException e) {
			 MainDriver.logger.error(e.getStackTrace());
			 }
		 }
}
		 