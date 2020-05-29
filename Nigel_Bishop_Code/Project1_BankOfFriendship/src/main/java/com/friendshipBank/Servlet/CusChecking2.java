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
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.model.customer;
import com.friendshipBank.model.transaction;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.customerService;
import com.friendshipBank.service.transactionService;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.transactionServiceImpl;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class CusChecking2 extends HttpServlet{
	
	private static String checkAccType = "CHECKING";
	private static String saveAccType = "SAVING";
	private static String accStatus = "PENDING";
	
	private static String transTypeDep = "DEPOSIT";
	private static String transTypeWith = "WITHDRAWL";
	private static String transTypeTrans = "TRANSFER";
	
//	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
//		
//		PrintWriter pw = res.getWriter();
//		userAccessService userService = new userAccessServiceImpl();
//		userAccess userAccess = new userAccess();
//		bankAccount bankAccount1 = new bankAccount();
//		bankAccount bankAccount2 = new bankAccount();
//
//		bankAccountService bankService = new bankAccountServiceImpl();
////		List<userAccess> customerList = new ArrayList<>();
////		userAccessDAO userDAO = new userAccessDAOImpl();
//		customer customer = new customer();
//    	customerService custService = new customerServiceImpl();
//
//		
//		
//			System.out.println("In Customer Dash doGet");
//
//			res.setContentType("application/json");
//						
//			ObjectMapper mapper = new ObjectMapper();
//			
//			HttpSession session = req.getSession(false);
//			if(session==null) 
//			{
//				pw.write("index.html");		
//			}else {
//				userAccess = (userAccess) session.getAttribute("userAccess");
//
//			}
//
//			try 
//        	{
//        		bankAccount1 = bankService.getAccountInfo(userAccess.getCustomerID(), checkAccType);
//
//        		String accStat1 = bankAccount1.getAccountStatus();
//        		
//        		if(accStat1.equals(accStatus)) {
//        			System.out.println("ACCOUNT STATUS:   " + bankAccount1.getAccountStatus());	
//        			System.out.println("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
//        			System.out.println("        PLEASE CONTACT A BANK EMPLOYEE");
//        		}
//        		else 
//        		{
//        			//GET BALANCE
//        			pw.write(mapper.writeValueAsString(bankAccount1.getBalance()));
//        		}
//        	}
//            catch (BusinessException e)
//            {
//            	MainDriver.SystemLog.info(e.getMessage());
//            	MainDriver.SystemLog.error(e.getMessage());
//            }
//
//	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		PrintWriter pw = res.getWriter();
		userAccess userAccess = new userAccess();
		bankAccount bankAccount1 = new bankAccount();
		bankAccount bankAccount2 = new bankAccount();

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
			    bankAccount1 = mapper.readValue(req.getReader(), com.friendshipBank.model.bankAccount.class);

        		bankAccount2 = bankService.getAccountInfo(userAccess.getCustomerID(), checkAccType);
        		String accStat = bankAccount2.getAccountStatus();
        		
        		if(accStat.equals(accStatus)) {
        			System.out.println("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
        			System.out.println("        PLEASE CONTACT A BANK EMPLOYEE");
        		}
        		else {
            		Double currentBalance = bankAccount2.getBalance();
            		Double withdrawlAmount = bankAccount1.getBalance();
            		Double newBalance = currentBalance - withdrawlAmount;
            		
        			//DEPOSIT
            		bankService.updateBankAccountBalance(userAccess.getCustomerID(), checkAccType, newBalance);
            		System.out.println("SYSTEM: $" + withdrawlAmount + " HAS BEEN WITHDRAWL FROM " + userAccess.getCustomerID() + " CHECKING ACCOUNT");
            		
            		//ADDING TO TRANSACTION LOG
            		transaction.setAccountID(bankAccount2.getAccountID());
            		transaction.setCustomerID(userAccess.getCustomerID());
            		transaction.setAccountType(checkAccType);
            		transaction.setBalance(newBalance);
            		transaction.setTransAmount(withdrawlAmount);
            		transaction.setTransType(transTypeWith);
            		transService.createNewBankTransaction(transaction);
	
        		}
			    
        	}
            catch (BusinessException e)
            {
            	MainDriver.SystemLog.info(e.getMessage());
            	MainDriver.SystemLog.error(e.getMessage());
            }

	}
	
	
	
}
