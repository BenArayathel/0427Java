package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.main.Main;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/approve")
public class EmployeeApproveAccount extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// setup
		ObjectMapper mapper = new ObjectMapper();
		UserServiceImplementation usi = new UserServiceImplementation();

		// take the request body, turn it into object so i can access the variables in it
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = mapper.readTree(body);
		
		// prepare response
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
		
		// get values sent from JS request
		String user_id = bodyObj.get("user_id").textValue();
		
		// approve account by user id
		try {
			usi.approve(user_id);
			// send anything back?
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
		}
	}
}
