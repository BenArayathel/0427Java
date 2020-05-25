package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.controllers.RequestHelper;
import com.exceptions.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Account;
import com.models.CurrentUser;
import com.models.User;
import com.dao.impl.UserDaoImpl;
import com.dao.impl.AccountDaoImpl;
import com.services.impl.UserServiceImpl;

public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger loggy = Logger.getLogger(User.class);
	private static UserServiceImpl uSI = new UserServiceImpl();
	private static UserDaoImpl uDI = new UserDaoImpl();
	private static AccountDaoImpl aDI = new AccountDaoImpl();
    private ObjectMapper mapper = new ObjectMapper();
	
	
    public MasterServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if("GET".equalsIgnoreCase(req.getMethod())) {
			req.getQueryString();
			String direction = req.getParameter("direction");
			
			if(direction.equalsIgnoreCase("user")) {
				String userRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				User user = mapper.readValue(userRequest, User.class);
				
				User foundUser = uSI.findUser(user.getEmail());
				mapper.writeValue(res.getWriter(), foundUser);
				
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();

		if ("POST".equalsIgnoreCase(req.getMethod())) 
		{
			req.getQueryString();
			String direction = req.getParameter("direction");
			
			if(direction.equalsIgnoreCase("user")) {
				String userRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			    //ObjectMapper mapper = new ObjectMapper();
				User newUser = mapper.readValue(userRequest, User.class);
				try {
					String encPass = uSI.passwordEncryption(newUser.getPassword());
					newUser.setPassword(encPass);
					if(uSI.createNewUser(newUser)) {
						CurrentUser currentUser = new CurrentUser(newUser.getEmail(), newUser.getName(), "0.00", "0.00", "customer");
						loggy.debug("New current user created. Email- " + currentUser.getEmail());
						mapper.writeValue(res.getWriter(), currentUser);
					};
				} catch (BusinessException e) {
					res.getWriter().append("Failed to create a new user with email " + newUser.getEmail());
					loggy.info("Failed to create a new user with email " + newUser.getEmail());
					doGet(req, res);
					e.printStackTrace();
				}
			}
			
			else if (direction.equalsIgnoreCase("account")) {
				String accountRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			    //ObjectMapper mapper = new ObjectMapper();
				Account newAccount = mapper.readValue(accountRequest, Account.class);
				
				try {
					uSI.createNewAccount(newAccount.getEmail(), newAccount.getCheckingBalance());
					res.getWriter().append("Successfully created account associated with email " + newAccount.getEmail() + " and starting balance of " + newAccount.getCheckingBalance());
					doGet(req, res);
				} catch (BusinessException e) {
					loggy.info("Failed to create a new account with email " + newAccount.getEmail());
					res.getWriter().append("Failed to create account for email " + newAccount.getEmail());
					doGet(req, res);
					e.printStackTrace();
				}
			}
			
			pw.append("Successfully sent post action. And now we wait");
		}
		
		else {
			pw.append("Error. Please use the post action next time");
		}
		
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if("PUT".equalsIgnoreCase(req.getMethod())) {
			req.getQueryString();
			String direction = req.getParameter("direction");
			String whichAccount = req.getParameter("whichAccount");
			
			if(direction.equalsIgnoreCase("account")) {
				String accountRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				CurrentUser currentUser = mapper.readValue(accountRequest,  CurrentUser.class);
				
				if(whichAccount.equalsIgnoreCase("savings")) {
					try {
						if(aDI.updateAccount(currentUser.getEmail(), "savingsBalance", currentUser.getSavingsBalance())) {
							loggy.debug("Updated savings balance for email " + currentUser.getEmail());
						}
					} catch (BusinessException e) {
						loggy.error("Error trying to update savings account with email " + currentUser.getEmail());
						e.printStackTrace();
					}
				}
				
				else if(whichAccount.equalsIgnoreCase("checking")) {					
					try {
						if(aDI.updateAccount(currentUser.getEmail(), "checkingBalance", currentUser.getCheckingBalance())) {
							loggy.debug("Updated checking balance for email " + currentUser.getEmail());
						}
					} catch (BusinessException e) {
						loggy.error("Error trying to update checking account for email " + currentUser.getEmail());
						e.printStackTrace();
					}
					
				}
				
				CurrentUser sendingBackUser = new CurrentUser(currentUser.getEmail(), currentUser.getName(), currentUser.getCheckingBalance(), currentUser.getSavingsBalance(), "customer");
				loggy.debug("Sending user back to client-side. Email- " + currentUser.getEmail());
				mapper.writeValue(res.getWriter(), sendingBackUser);
				
			}// end of if "account"
			
			else if (direction.equalsIgnoreCase("transfer")) {
				String transferRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				Account transferInfo = mapper.readValue(transferRequest,  Account.class);
				
				String senderEmail = transferInfo.getEmail();
				String receiverAccountNumber = transferInfo.getCheckingAccountNumber();
				String transferAmount = transferInfo.getCheckingBalance();
				
				try {
					if(uSI.transferFunds(senderEmail, "checkingBalance", receiverAccountNumber, transferAmount)) {
						
					}
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				loggy.debug("Transferring money from account with Email- " + senderEmail + " to account with number- " + receiverAccountNumber);
				res.getWriter().append("Transfer complete.");
			}
		}
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if("DELETE".equalsIgnoreCase(req.getMethod())) {
			req.getQueryString();
			String direction = req.getParameter("direction");
			
			if(direction.equalsIgnoreCase("user")) {
				String userRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			   // ObjectMapper mapper = new ObjectMapper();
				User user = mapper.readValue(userRequest, User.class);
				
				try {
					uSI.removeUserProfile(user);
					loggy.info("User with email: " + user.getEmail() + " deleted");
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	
	

}
