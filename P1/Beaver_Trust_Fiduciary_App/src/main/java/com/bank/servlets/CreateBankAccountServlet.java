package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.main.Main;
import com.bank.models.User;
import com.bank.presentation.UserHomeView;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/createbankaccount")
public class CreateBankAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// setup tools
		UserServiceImplementation usi = new UserServiceImplementation();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
		// return this string to JS to notify user of results of attempted deposit
		String returnMessage;

		// take the request body, turn it into object so i can access the variables in it
		ObjectMapper mapper = new ObjectMapper();
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode bodyObj = mapper.readTree(body);
		
		// access session to get user
		HttpSession session = req.getSession(false);
		if (session == null) {
			res.sendRedirect("/index.html");
		} else {
			// access user
			User user = (User) session.getAttribute("user");

			// get values sent from JS request
			String newAccount = bodyObj.get("account").textValue();
			String newAmount = bodyObj.get("amount").textValue();
			
			// main logic, create a new bank account for the user
			try {
				asi.createAccount(user, newAccount, newAmount);
				
				returnMessage = "New account created! Thank you. Redirecting to your Accounts List Page.";
				// send json response
				writer.write(returnMessage);
			} catch (BankException e) {
				Main.myLog.error(e.getMessage() + e.getStackTrace());
				returnMessage = "Something went wrong, please try again later.";
				// send json response
				writer.write(returnMessage);
			}

		}	
	}
}
