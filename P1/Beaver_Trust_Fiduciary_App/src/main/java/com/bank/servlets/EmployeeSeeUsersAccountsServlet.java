package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.Transaction;
import com.bank.presentation.EmployeeView;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/getuseraccounts")
public class EmployeeSeeUsersAccountsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// setup
		ObjectMapper mapper = new ObjectMapper();
		AccountServiceImplementation asi = new AccountServiceImplementation();

		// take the request body, turn it into object so i can access the variables in it
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = mapper.readTree(body);
		
		// prepare response
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
		
		// get values sent from JS request
		String username = bodyObj.get("username").textValue();
			
		// LIST ACCOUNTS by the selected USERNAME
		try {
			List<Account> userAccountsList = asi.listUserAccounts(username);
			// send json response
			writer.write(mapper.writeValueAsString(userAccountsList));
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
		}
	}
}
