package com.lunch.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.transaction.dao.BankDaoImpl;
import bank.transaction.dao.TransactionDaoImpl;
import log.Log;
import user.cust.account.controller.CustOptionsDirectory;
import user.cust.account.models.Transaction;

public class CustBalanceServlet extends HttpServlet {
	
	//CustOptionsDirectory cd = new CustOptionsDirectory();
	BankDaoImpl b = new BankDaoImpl();
	//private static double transferFunds;
	Transaction t = new Transaction();
	TransactionDaoImpl tDao = new TransactionDaoImpl();
	
	//Customer c = new Customer();
	//Account a = new Account();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		 System.out.println("balance servlet GET was called ... ...");
		 //req.getSession().getAttribute("user");  the instantiation of user is stumping me .. look at Dr V's code.. .. ..
		 
			if (user.getA_access() == 1) {
				
				Log.logger("\nYour Balance " + user.getBalance());
				//Log.logger("... should be todays date here: " + now.toString());
				//c.setUser(user);
				t.setUser_id(user.getUser_id());
				t.setDate(formatter.format(date).toString());
				t.setDescription("Balance Inquery");
				t.setTransactionValue(0.00);
				t.setDestination_id("Balance Inquery");
				tDao.createTransaction(user, t.toString());
				cd.select(user);
				
			} else {
				Log.logger("Sorry you do not have access");
				cd.select(user);
			}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		 System.out.println("login GET");
	}

}
