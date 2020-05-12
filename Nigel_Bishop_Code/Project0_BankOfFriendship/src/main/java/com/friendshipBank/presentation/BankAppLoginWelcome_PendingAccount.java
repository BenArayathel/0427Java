package com.friendshipBank.presentation;


import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.dao.bankAccountDAO;
import com.friendshipBank.dao.customerDAO;
import com.friendshipBank.dao.userAccessDAO;
import com.friendshipBank.dao.impl.bankAccountDAOImpl;
import com.friendshipBank.dao.impl.customerDAOImpl;
import com.friendshipBank.dao.impl.userAccessDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class BankAppLoginWelcome_PendingAccount 
{
	public static String loginCustomerID;
	private static String accType = "CHECKING";
	private static long checkblance = 300;
	private static String accType2 = "SAVING";
	private static long saveblance = 100;
	private static String accStatus = "PENDING";
	
	
	public static void BankAppLoginWelcomePage_PendingAccount() 
	{
		
		userAccessService userService = new userAccessServiceImpl();
		userAccess userAccess = new userAccess();
		bankAccountService bankService = new bankAccountServiceImpl();
		bankAccount bankAccount = new bankAccount();
		bankAccount bankAccount2 = new bankAccount();
		
		List<bankAccount> bankList = new ArrayList<>();
		List<userAccess> uList = new ArrayList<>();
		userAccessDAO  uDao = new userAccessDAOImpl();
		bankAccountDAO bankdao = new bankAccountDAOImpl();
		customerDAO cusDAO = new customerDAOImpl();
//		bankdao.getAllBankAccounts();
		
    	int userChoice = 0;
 	
    	do {
    		
        	System.out.println();
        	System.out.println("  WELCOME TO BANK OF FRIENDSHIP ACCOUNT SUMMARY   ");
        	System.out.println("**************************************************");
        	System.out.println();
        	System.out.println("WARNING: Due to your account being in PENDING STATUS");
        	System.out.println("         functional will be limited");
        	System.out.println();
        	System.out.println("SELECT: (1) REQUEST CHECKING ACCOUNT");
        	System.out.println("SELECT: (2) REQUEST SAVING ACCOUNT");
        	System.out.println("SELECT: (3) EXIT THE APPLICATION");
        	System.out.println("SELECT: (4) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();

        	switch(userChoice) {
			case 1:
	        	try 
	        	{
//	        		uList = uDao.getAllUserLoginAccounts();
//	        		for()
	        		cusDAO.getAllCustomers();
//	        		bankdao.getAllBankAccounts();
//	    			userAccess = userService.loginByUsernameAndPassword(uname, upass);
//	    			if(userAccess.getUserName().equals(uname) && userAccess.getUserPassword().equals(upass))
	        		System.out.println(loginCustomerID);
	        		System.out.println(accType);
//	        		bankAccount2 = bankService.getAccountInfo(loginCustomerID, accType);
////	        		bankAccount2 = bankService.deleteBankAccount(loginCustomerID, accType);
//	        		
//	        		if(bankAccount2.getCustomerID().equals(loginCustomerID) && bankAccount2.getAccountType().equals(accType))
//	        		{
//	        			System.out.println("SYSTEM: CHECKING ACCOUNT HAS ALREADY BEEN REQUESTED.");
//	        			BankAppLoginWelcomePage_PendingAccount();
//	        		}
//	        		else
//	        		{
//		        		bankAccount.setCustomerID(loginCustomerID);
//		        		bankAccount.setAccountType(accType);
//		        		bankAccount.setBalance(checkblance);
//		        		bankAccount.setAccountStatus(accStatus);
//		        		
//		        		bankAccount = bankService.createNewBankAccount(bankAccount);
//
//			       		if(bankAccount.getAccountID() != null) {
//			       			System.out.println();
//			       			System.out.println("SYSTEM: YOUR REQUEST FOR A CHECKING ACCOUNT HAS BEEN COMPLETED");
//			       			System.out.println("        A DEFAULT BALANCE OF $300 HAS BEEN ADDED TO THE ACCOUNT");
//			       			System.out.println("        PER REQUIREMENT TO OPEN A CHECKING ACCOUNT");
//			       			System.out.println();
//			       			System.out.println("SYSTEM: PLEASE MAKE A NOTE OF YOUR ACCOUNT NUMBER: " + bankAccount.getAccountID());
//			       			System.out.println();
//			       			System.out.println(bankAccount);
//			       			BankAppLoginWelcomePage_PendingAccount();
//			       		}
//	        		}
//	        		
//	        		bankService.getAccountInfo(loginCustomerID, accType);



				}
	        	catch (BusinessException e)
	        	{
	    			System.out.println(e.getMessage());
	        	}
	        	break;
			case 2:
	        	try 
	        	{
	        		bankAccount.setCustomerID(loginCustomerID);
	        		bankAccount.setAccountType(accType2);
	        		bankAccount.setBalance(saveblance);
	        		bankAccount.setAccountStatus(accStatus);
	        		
	        		bankAccount = bankService.createNewBankAccount(bankAccount);

		       		if(bankAccount.getAccountID() != null) {
		       			System.out.println();
		       			System.out.println("SYSTEM: YOUR REQUEST FOR A SAVING ACCOUNT HAS BEEN COMPLETED");
		       			System.out.println("        A DEFAULT BALANCE OF $100 HAS BEEN ADDED TO THE ACCOUNT");
		       			System.out.println("        PER REQUIREMENT TO OPEN A SAVING ACCOUNT");
		       			System.out.println();
		       			System.out.println("SYSTEM: PLEASE MAKE A NOTE OF YOUR ACCOUNT NUMBER: " + bankAccount.getAccountID());
		       			System.out.println();
		       			System.out.println(bankAccount);
		       			BankAppLoginWelcomePage_PendingAccount();
		       		}
				}
	        	catch (BusinessException e)
	        	{
	    			System.out.println(e.getMessage());
	        	}
	        	break;
			case 3:
            	System.out.println();
            	System.out.println("THANK YOU FOR USING MY BANKING APPLICATION");
            	System.out.println();
				System.exit(0);
         		break;
        	
			case 4:
            	System.out.println();
        		welcomePage.BankWelcomePage();
         		break;
        	
        	}
    	}
    	while(userChoice != 4);
		
	}
	
	

}
