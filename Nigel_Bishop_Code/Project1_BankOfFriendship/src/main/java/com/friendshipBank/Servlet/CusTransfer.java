package com.friendshipBank.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.Transfer;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.model.transaction;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.transactionService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.transactionServiceImpl;

public class CusTransfer extends HttpServlet{
	
	
	private static String checkAccType = "CHECKING";
	private static String saveAccType = "SAVING";
	private static String accStatus = "PENDING";
	
	private static String transTypeDep = "DEPOSIT";
	private static String transTypeWith = "WITHDRAWL";
	private static String transTypeTrans = "TRANSFER";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		PrintWriter pw = res.getWriter();
		userAccess userAccess = new userAccess();
		bankAccount bankAccount1 = new bankAccount();
		bankAccount bankAccount2 = new bankAccount();
		Transfer tranfer = new Transfer();

		bankAccountService bankService = new bankAccountServiceImpl();
    	transaction transaction = new transaction();
		transactionService transService = new transactionServiceImpl();

			System.out.println("In Customer Dash doPost");

			res.setContentType("application/json");
						
			ObjectMapper mapper = new ObjectMapper();
			
			HttpSession session = req.getSession(false);
			if(session==null) 
			{
				pw.write("index.html");		
			}else {
				userAccess = (userAccess) session.getAttribute("userAccess");

			}

			try 
        	{
				tranfer = mapper.readValue(req.getReader(), com.friendshipBank.model.Transfer.class);
				
				if(tranfer.getSource().equals("CHECKING")) 
				{
					
					
        			// SAVING ACCOUNT INFO
					bankAccount1 = bankService.getAccountInfo(userAccess.getCustomerID(), checkAccType);
            		bankAccount2 = bankService.getAccountInfo(userAccess.getCustomerID(), saveAccType);
            		Double DestinationAccBalance = bankAccount2.getBalance();
            		DestinationAccBalance = tranfer.getTransferAmount() + DestinationAccBalance;
            		Double SourceAccBalance = bankAccount1.getBalance();
            		SourceAccBalance = SourceAccBalance - tranfer.getTransferAmount();
            		
            		if(SourceAccBalance <= 0) 
            		{
            			MainDriver.SystemLog.info("SYSTEM: UNABLE TO PROCESS THE REQUESTED TRANSFER.");
            			MainDriver.SystemLog.info("        PROCESSING THIS REQUEST WILL RESULT IN A NEGATIVE BALANCE");
            		}
            		else 
            		{	
            			//TRANSFER AND UPDATE ACCOUNTS
	            		bankService.updateBankAccountBalance(userAccess.getCustomerID(), checkAccType, SourceAccBalance);
	            		bankService.updateBankAccountBalance(userAccess.getCustomerID(), saveAccType, DestinationAccBalance);
//	            		MainDriver.SystemLog.info("SYSTEM: $" + transferAmount + " HAS BEEN TRANSFERED TO YOUR CHECKING ACCOUNT");
//	            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
//	            		MainDriver.SystemLog.info("\nACCOUNT TYPE: " + bankAccount.getAccountType());
//	            		MainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
//	            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
//	            		MainDriver.SystemLog.info("\nACCOUNT TYPE: " + bankAccount.getAccountType());
//	            		MainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
	            		
	            		//ADDING TO TRANSACTION LOG
	            		transaction.setAccountID(bankAccount1.getAccountID());
	            		transaction.setCustomerID(userAccess.getCustomerID());
	            		transaction.setAccountType(checkAccType);
	            		transaction.setBalance(SourceAccBalance);
	            		transaction.setTransAmount(tranfer.getTransferAmount());
	            		transaction.setTransType(transTypeTrans);
	            		transService.createNewBankTransaction(transaction);
            		}
				}
				else if(tranfer.getSource().equals("SAVING")) 
				{
//					saveAccType checkAccType
        			// SAVING ACCOUNT INFO
					bankAccount1 = bankService.getAccountInfo(userAccess.getCustomerID(), saveAccType);
            		bankAccount2 = bankService.getAccountInfo(userAccess.getCustomerID(), checkAccType);
            		Double DestinationAccBalance = bankAccount2.getBalance();
            		DestinationAccBalance = tranfer.getTransferAmount() + DestinationAccBalance;
            		Double SourceAccBalance = bankAccount1.getBalance();
            		SourceAccBalance = SourceAccBalance - tranfer.getTransferAmount();
            		
            		if(SourceAccBalance <= 0) 
            		{
            			MainDriver.SystemLog.info("SYSTEM: UNABLE TO PROCESS THE REQUESTED TRANSFER.");
            			MainDriver.SystemLog.info("        PROCESSING THIS REQUEST WILL RESULT IN A NEGATIVE BALANCE");
            		}
            		else 
            		{	
            			//TRANSFER AND UPDATE ACCOUNTS
	            		bankService.updateBankAccountBalance(userAccess.getCustomerID(), saveAccType, SourceAccBalance);
	            		bankService.updateBankAccountBalance(userAccess.getCustomerID(), checkAccType, DestinationAccBalance);
//	            		MainDriver.SystemLog.info("SYSTEM: $" + transferAmount + " HAS BEEN TRANSFERED TO YOUR CHECKING ACCOUNT");
//	            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
//	            		MainDriver.SystemLog.info("\nACCOUNT TYPE: " + bankAccount.getAccountType());
//	            		MainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
//	            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
//	            		MainDriver.SystemLog.info("\nACCOUNT TYPE: " + bankAccount.getAccountType());
//	            		MainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
	            		
	            		//ADDING TO TRANSACTION LOG
	            		transaction.setAccountID(bankAccount1.getAccountID());
	            		transaction.setCustomerID(userAccess.getCustomerID());
	            		transaction.setAccountType(saveAccType);
	            		transaction.setBalance(SourceAccBalance);
	            		transaction.setTransAmount(tranfer.getTransferAmount());
	            		transaction.setTransType(transTypeTrans);
	            		transService.createNewBankTransaction(transaction);
            		}
				}
			    
        	}
            catch (BusinessException e)
            {
            	MainDriver.SystemLog.info(e.getMessage());
            	MainDriver.SystemLog.error(e.getMessage());
            }

	}
	

}
