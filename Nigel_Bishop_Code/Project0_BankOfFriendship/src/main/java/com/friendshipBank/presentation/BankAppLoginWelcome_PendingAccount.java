package com.friendshipBank.presentation;



import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.myScanner;

public class BankAppLoginWelcome_PendingAccount 
{
	public static String loginCustomerID;
	private static String checkAccType = "CHECKING";
	private static Double checkblance = 300.00;
	private static String saveAccType = "SAVING";
	private static Double saveblance = 100.00;
	private static String accStatus = "PENDING";
	
	
	public static void BankAppLoginWelcomePage_PendingAccount() 
	{
		
//		userAccessService userService = new userAccessServiceImpl();
//		userAccess userAccess = new userAccess();
		bankAccountService bankService = new bankAccountServiceImpl();
		bankAccount bankAccount = new bankAccount();
		
		
    	int userChoice = 0;
 	
    	do {
    		
        	MainDriver.SystemLog.info("\n  WELCOME TO BANK OF FRIENDSHIP ACCOUNT SUMMARY   ");
        	MainDriver.SystemLog.info("**************************************************");
        	MainDriver.SystemLog.info("SYSTEM:  ACCOUNT IS IN PENDING STATUS");
        	MainDriver.SystemLog.info("         FUNCTIONALITY WILL BE LIMITED");
        	MainDriver.SystemLog.info("\nSELECT: (1) REQUEST CHECKING ACCOUNT");
        	MainDriver.SystemLog.info("SELECT: (2) REQUEST SAVING ACCOUNT");
        	MainDriver.SystemLog.info("SELECT: (3) LOG OUT");
        	MainDriver.SystemLog.info("SELECT: (4) EXIT THE APPLICATION");

			userChoice = myScanner.UserInput_menuChoice();

        	switch(userChoice) {
			case 1:
	        	try 
	        	{        		
	        		
//	        		bankAccount = bankService.getAccountInfo(loginCustomerID,checkAccType);
//	        		String bAcc = bankAccount.getCustomerID();
//	        		String accType = bankAccount.getAccountType();
//	        		
//	        		if(bAcc.equals(loginCustomerID) && accType.equals(checkAccType) && accType != null)
//	        		{
//	        			System.out.println("SYSTEM: CHECKING ACCOUNT HAS ALREADY BEEN REQUESTED.");
//	        			BankAppLoginWelcomePage_PendingAccount();
//	        		}
//	        		else if(bAcc.equals(null) && accType.equals(null))
//	        		{
		        		bankAccount.setCustomerID(loginCustomerID);
		        		bankAccount.setAccountType(checkAccType);
		        		bankAccount.setBalance(checkblance);
		        		bankAccount.setAccountStatus(accStatus);
		        		
		        		bankAccount = bankService.createNewBankAccount(bankAccount);

			       		if(bankAccount.getAccountID() != null) {
			       			System.out.println();
			       			MainDriver.SystemLog.info("SYSTEM: YOUR REQUEST FOR A CHECKING ACCOUNT HAS BEEN COMPLETED");
			       			MainDriver.SystemLog.info("        A DEFAULT BALANCE OF $300 HAS BEEN ADDED TO THE ACCOUNT");
			       			MainDriver.SystemLog.info("        PER REQUIREMENT TO OPEN A CHECKING ACCOUNT");
			       			System.out.println();
			       			MainDriver.SystemLog.info("SYSTEM: PLEASE MAKE A NOTE OF YOUR ACCOUNT NUMBER: " + bankAccount.getAccountID());
			       			System.out.println();
			       			System.out.println(bankAccount);
			       			BankAppLoginWelcomePage_PendingAccount();
			       		}
//	        		}
				}
	        	catch (BusinessException e)
	        	{
	        		MainDriver.SystemLog.error(e.getMessage());
	        		MainDriver.SystemLog.info(e.getMessage());
	        	}
	        	break;
			case 2:
	        	try 
	        	{
//	        		bankAccount = bankService.getAccountInfo(loginCustomerID,saveAccType);
//	        		String bAcc = bankAccount.getCustomerID();
//	        		String accType = bankAccount.getAccountType();
//	        		
//	        		if(bAcc.equals(loginCustomerID) && accType.equals(saveAccType) && accType != null)
//	        		{
//	        			System.out.println("SYSTEM: SAVING ACCOUNT HAS ALREADY BEEN REQUESTED.");
//	        			BankAppLoginWelcomePage_PendingAccount();
//	        		}
//	        		else
//	        		{      		
		        		bankAccount.setCustomerID(loginCustomerID);
		        		bankAccount.setAccountType(saveAccType);
		        		bankAccount.setBalance(saveblance);
		        		bankAccount.setAccountStatus(accStatus);
		        		
		        		bankAccount = bankService.createNewBankAccount(bankAccount);
	
			       		if(bankAccount.getAccountID() != null) {
			       			System.out.println();
			       			MainDriver.SystemLog.info("SYSTEM: YOUR REQUEST FOR A SAVING ACCOUNT HAS BEEN COMPLETED");
			       			MainDriver.SystemLog.info("        A DEFAULT BALANCE OF $100 HAS BEEN ADDED TO THE ACCOUNT");
			       			MainDriver.SystemLog.info("        PER REQUIREMENT TO OPEN A SAVING ACCOUNT");
			       			System.out.println();
			       			MainDriver.SystemLog.info("SYSTEM: PLEASE MAKE A NOTE OF YOUR ACCOUNT NUMBER: " + bankAccount.getAccountID());
			       			System.out.println();
			       			System.out.println(bankAccount);
			       			BankAppLoginWelcomePage_PendingAccount();
			       		}
//	        		}
				}
	        	catch (BusinessException e)
	        	{
	        		MainDriver.SystemLog.error(e.getMessage());
	        		MainDriver.SystemLog.info(e.getMessage());
	        	}
	        	break;
			case 3:
        		WelcomePage.BankWelcomePage();
         		break;
			case 4:
            	MainDriver.SystemLog.info("\nTHANK YOU FOR USING MY BANKING APPLICATION");
				System.exit(0);
         		break;
            default:
            	 MainDriver.SystemLog.info("\nSYSTEM:  INVALID OPTION");
            	break;
        	}
    	}
    	while(userChoice != 4);
		
	}
	
	

}
