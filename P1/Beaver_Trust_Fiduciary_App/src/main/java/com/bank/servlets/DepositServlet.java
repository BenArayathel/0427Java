package com.bank.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao_implementation.UserDAOImplementation;
import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.User;
import com.bank.presentation.AccountDeposit;
import com.bank.presentation.AccountsView;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
	
	// POST for now, but updating account should probably be PUT
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// goal: take in the account, amount, and user and run deposit, then add in all the 
		// logic for testing if the input is the correct type etc.
		
		// setup tools
		UserServiceImplementation usi = new UserServiceImplementation();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		ObjectMapper mapper = new ObjectMapper();
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
//		InputStream requestBodyInput = req.getInputStream();
		
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JsonNode actualObj = mapper.readTree(body);
		
		// access session to get user
		HttpSession session = req.getSession(false);
		if (session == null) {
			res.sendRedirect("/index.html");
		} else {
			// access user
			User user = (User) session.getAttribute("user");

			// get values sent from JS request
			System.out.println(actualObj + " INPUT BODY");
			String depositAccount = actualObj.get("account").textValue();
			String depositAmount = actualObj.get("amount").textValue();
			
			System.out.println(depositAccount);
			System.out.println(depositAmount);
			
			
			// main logic to try deposit
			try {
				asi.deposit(user, depositAccount, depositAmount);
				Main.myLog.info("\nDeposit of $" + depositAmount + " complete!");
				Main.myLog.info("-----------------------------------------------------");
			} catch (BankException e) {
				// do something useful with error
			}
			
			// list transactions from employee, temporary, to see if it worked
			try {
				System.out.println(asi.listAllTransactions());
			} catch (BankException e1) {
				
			}
			
			// send json response
			writer.write("it worked");
		}		
	}		
}

