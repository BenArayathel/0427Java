package com.lunch.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.transaction.dao.BankDaoImpl;
import bank.transaction.dao.TransactionDaoImpl;
import log.Log;
import user.cust.account.models.Transaction;
import user.cust.account.models.User;

@WebServlet("/custbalance")
public class CustBalanceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
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
			
		 System.out.println("balance servlet doGET was called ... ...");
		 HttpSession session = req.getSession(false);
		 
		 // the way this line looks, it is as if you can just go an use the POJO but it's NULL
		 //User user1 = (User) session.getAttribute("user");
		 String user2 =  session.getAttribute("mappedStringUser").toString();
		 
		 
		 //String bal = session.getAttribute("balance").toString();
		 //System.out.println("user balance via SESSION: " + bal);
		 
		 //System.out.println("user balance via SESSION: " + (User) session.getAttribute("balance").toString());
		 //System.out.println("user balance via SESSION: " + ((ServletRequest) session).getAttribute("balance").toString());
		 
//			"access"
//			"balance"
//			"user"
//			"id"
//			"soc"
		 
		 
		 //User user = new User("fake", "fake");
		 //user.setBalance(120.00);
		 //user.setBalance(Double.parseDouble(bal));
		 
		 res.setContentType("application/json");
		 res.getWriter().write(new ObjectMapper().writeValueAsString(user1));
		 //doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		 System.out.println("cust balance doPost is running ... ... ...");
		 
		 //req.getSession().getAttribute("user");  the instantiation of user is stumping me .. look at Dr V's code.. .. ..
		 HttpSession session = req.getSession(false); // there was false in there ... ...
		 User user = (User) session.getAttribute("user");
		 System.out.println("user to string: ");
		 System.out.println(user.toString());
		 
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
				//cd.select(user);
				//req.getRequestDispatcher("http://localhost:9999/Password/balance.html").forward(req, res);
				
			} else {
				Log.logger("Sorry you do not have access");
				//cd.select(user);
			}
	}

}
