package com.bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.main.Main;
import com.bank.models.Account;
import com.bank.models.Transaction;
import com.bank.models.User;
import com.bank.presentation.EmployeeView;
import com.bank.service_implementation.AccountServiceImplementation;
import com.bank.service_implementation.UserServiceImplementation;
import com.bank.tools.BankException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/getalltransactions")
public class EmployeeViewAllTransactionsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// setup
		ObjectMapper mapper = new ObjectMapper();
		AccountServiceImplementation asi = new AccountServiceImplementation();
		
		// prepare response
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
			
		// send json response
		try {
			List<Transaction> allTransactionsList = asi.listAllTransactions();
			writer.write(mapper.writeValueAsString(allTransactionsList));
			System.out.println(mapper.writeValueAsString(allTransactionsList));
		} catch (BankException e) {
			Main.myLog.error(e.getMessage() + e.getStackTrace());
			Main.myLog.info("Unable to print logs.");
		}
	}
}
