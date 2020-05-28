package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.controllers.GetController;
import com.controllers.PostController;
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
			
				if (res == null) {
					res.sendError(404, "User could not be found");
				}
				else {
					res = GetController.getUserInfo(req, res);
				}
			}
		else {
			res.sendError(404, "Error. Please use the get action next time");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if ("POST".equalsIgnoreCase(req.getMethod())) 
		{
			res = PostController.postInfo(req, res);
		}
		else {
			res.sendError(404, "Error. Please use the post action next time");
		}
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if("PUT".equalsIgnoreCase(req.getMethod())) {
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
					e1.printStackTrace();
				}
				if(acc.getActive().equals("true")) {
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
					}// end of checking/saving if block
					CurrentUser sendingBackUser = new CurrentUser(currentUser.getEmail(), currentUser.getName(), currentUser.getCheckingBalance(), currentUser.getSavingsBalance(), "customer");
					loggy.debug("Sending user back to client-side. Email- " + currentUser.getEmail());
					mapper.writeValue(res.getWriter(), sendingBackUser);
					
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
					loggy.error("Error while activating account with email " + activationInfo.getEmail());
					e.printStackTrace();
				}
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
					uSI.removeUserProfile(user.getEmail());
					loggy.debug("User with email: " + user.getEmail() + " deleted");
					res.getWriter().append("Account with email " + user.getEmail() + " deleted. Good riddance");
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	
	

}
