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
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
	

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
			String depositAccount = bodyObj.get("account").textValue();
			String depositAmount = bodyObj.get("amount").textValue();
						
			// main logic to try deposit
			try {
				// check that the amount is in money format...
				if (asi.validTransactionFormat(depositAmount)) {
					// ...then that it isn't negative
					if (Double.parseDouble(depositAmount) > 0) {
						// instantiate the account
//						Account account = asi.listAccountByNameAndUserID(account_name, user_id);
//						// create list of all the user's accounts...
						List<Account> account_list = asi.listUserAccounts(user.getUsername());
						// ...then a list of all the names from those accounts
						List<String> accountNames = new ArrayList<String>();
						for (Account i : account_list) {
							accountNames.add(i.getAccount_name());
						}
						// check if the name the user entered is in their accounts list
						if (accountNames.contains(depositAccount)) {
							asi.deposit(user, depositAccount, depositAmount);
							
							returnMessage = "Deposit Successful";
							// send json response
							writer.write(returnMessage);
						} else {
							returnMessage = "You don't have an account with that name.";
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
			} catch (BankException e) {
				Main.myLog.error(e.getMessage() + e.getStackTrace());
				returnMessage = "Something went wrong, unable to process deposit. Please try again later.";
				// send json response
				writer.write(returnMessage);
			}
			
			System.out.println(returnMessage + "HERE ILT IS");
			// list transactions from employee, temporary, to see if it worked
			// from inside eclipse
//			try {
//				System.out.println(asi.listAllTransactions());
//			} catch (BankException e1) {
//				
//			}
			
		}
	}		
}

