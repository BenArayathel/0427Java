package com.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.impl.AccountDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.exceptions.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Account;
import com.models.CurrentUser;
import com.models.User;
import com.services.impl.UserServiceImpl;

public class PutController {
	private static ObjectMapper mapper = new ObjectMapper();
	final static Logger loggy = Logger.getLogger(User.class);
	private static UserDaoImpl uDI = new UserDaoImpl();
	private static AccountDaoImpl aDI = new AccountDaoImpl();
	private static UserServiceImpl uSI = new UserServiceImpl();

	public PutController() {
		
	}
	
	public static HttpServletResponse updateInfo(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		req.getQueryString();
		String direction = req.getParameter("direction");
		String whichAccount = req.getParameter("whichAccount");
		Account acc = new Account();
		
		if(direction.equalsIgnoreCase("account")) {
			String accountRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			CurrentUser currentUser = mapper.readValue(accountRequest,  CurrentUser.class);
			try {
				acc = aDI.selectAccountByEmail(currentUser.getEmail());
			} catch (BusinessException e1) {
				res = null;
				e1.printStackTrace();
			}
			if(acc.getActive().equals("true")) {
				if(whichAccount.equalsIgnoreCase("savings")) {
					try {
						if(aDI.updateAccount(currentUser.getEmail(), "savingsBalance", currentUser.getSavingsBalance())) {
							loggy.debug("Updated savings balance for email " + currentUser.getEmail());
						}
					} catch (BusinessException e) {
						res = null;
						loggy.error("Error trying to update savings account with email " + currentUser.getEmail());
						e.printStackTrace();
					}
				}
				
				else if(whichAccount.equalsIgnoreCase("checking")) {					
					try {
						if(aDI.updateAccount(currentUser.getEmail(), "checkingBalance", currentUser.getCheckingBalance())) {
							loggy.debug("Updated checking balance for email " + currentUser.getEmail());
							CurrentUser sendingBackUser = new CurrentUser(currentUser.getEmail(), currentUser.getName(), currentUser.getCheckingBalance(), currentUser.getSavingsBalance(), "customer");
							loggy.debug("Sending user back to client-side. Email- " + currentUser.getEmail());
							mapper.writeValue(res.getWriter(), sendingBackUser);
						}
					} catch (BusinessException e) {
						loggy.error("Error trying to update checking account for email " + currentUser.getEmail());
						res = null;
						e.printStackTrace();
					}
				}// end of checking/saving if block
				
				
			}
			
			else {
				res.sendError(404, "Account must be activated first. Please be patient with us. Thank you.");
			}// end of active == 'true' if block
			
		}// end of if "account"
		
		else if (direction.equalsIgnoreCase("transfer")) {
			String transferRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			Account transferInfo = mapper.readValue(transferRequest,  Account.class);
			
			String senderEmail = transferInfo.getEmail();
			String receiverAccountNumber = transferInfo.getCheckingAccountNumber();
			String transferAmount = transferInfo.getCheckingBalance();
			
			try {
				if(uSI.transferFunds(senderEmail, "checkingBalance", receiverAccountNumber, transferAmount)) {
					loggy.debug("Transferring money from account with Email- " + senderEmail + " to account with number- " + receiverAccountNumber);
					User u = uDI.selectUserByEmail(senderEmail);
					Account a = aDI.selectAccountByEmail(senderEmail);
					CurrentUser cU = new CurrentUser(u.getEmail(), u.getName(), a.getCheckingBalance(), a.getSavingsBalance(), "customer");
					loggy.debug("Sending user back to client-side. Email- " + u.getEmail());
					mapper.writeValue(res.getWriter(), cU);
				}
			} catch (BusinessException e) {
				res = null;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		else if (direction.equalsIgnoreCase("activate")) {
			String activateRequest = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			Account activationInfo = mapper.readValue(activateRequest,  Account.class);
			
			try {
				aDI.updateAccount(activationInfo.getEmail(), "active", activationInfo.getActive());
				try {
					User newUser = uDI.selectUserByEmail(activationInfo.getEmail());
					Account foundAccount = aDI.selectAccountByEmail(activationInfo.getEmail());
					CurrentUser foundUser = new CurrentUser(newUser.getEmail(), newUser.getName(), foundAccount.getCheckingAccountNumber(), foundAccount.getSavingsAccountNumber(), foundAccount.getActive());
					mapper.writeValue(res.getWriter(), foundUser);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			} catch (BusinessException e) {
				res = null;
				loggy.error("Error while activating account with email " + activationInfo.getEmail());
				e.printStackTrace();
			}
		}
		
		return res;
	}

}
