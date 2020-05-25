package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.presentation.AccountsView;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/withdraw")
public class WithdrawalServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// POST for now, but updating account should probably be PUT
	
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
			String withdrawalAccount = bodyObj.get("account").textValue();
			String withdrawalAmount = bodyObj.get("amount").textValue();
			
			// main logic to try withdraw
			try {
				// first check that the amount entered is in a valid format
				if (asi.validTransactionFormat(withdrawalAmount)) {
					// then that they aren't withdrawing negative money
					if (Double.parseDouble(withdrawalAmount) > 0) {
						// use this account object for overdraft check...
						Account account = asi.listAccountByNameAndUserID(withdrawalAccount, user.getUser_id());
						
						// balance is greater than or equal to...yet it doesn't do it
						// if they withdraw the entire amount...
						
						// then that they aren't overdrafting
						if (account.getBalance() >= Double.parseDouble(withdrawalAmount)) {
							asi.withdraw(user, withdrawalAccount, withdrawalAmount);
							returnMessage = "Withdrawal Successful";
							// send json response
							writer.write(returnMessage);	
						} else {
							returnMessage = "Invalid transaction (account name incorrect or insufficient funds).";
							// send json response
							writer.write(returnMessage);
						}
					} else {
						returnMessage = "Enter an amount greater than $0.";
						// send json response
						writer.write(returnMessage);
					}			
				} else {
					returnMessage = "Please format your input as either dollars or dollars and cents";
					// send json response
					writer.write(returnMessage);
				}
			}  catch (BankException e) {
				Main.myLog.error(e.getMessage() + e.getStackTrace());
				returnMessage = "Something went wrong, unable to process withdrawal. Please try again later.";
				// send json response
				writer.write(returnMessage);
			}
		}
	}
}




