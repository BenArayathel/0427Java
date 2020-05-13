package com.friendshipBank.presentation.Customer;

import com.friendshipBank.Main.mainDriver;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.presentation.welcomePage;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.myScanner;

public class CustomerAccount_Checking 
{
	
	public static String loginCustomerID;
	private static String checkAccType = "CHECKING";
	private static String saveAccType = "SAVING";
	private static String accStatus = "PENDING";

	
    public static void CheckingWelcome()
    {	
		bankAccountService bankService = new bankAccountServiceImpl();
		bankAccount bankAccount = new bankAccount();
		bankAccount bankAccount2 = new bankAccount();
    	
    	int userChoice = 0;
    	
    	do 
    	{
        	System.out.println();
        	mainDriver.SystemLog.info("  WELCOME TO BANK OF FRIENDSHIP CHECKING ACCOUNT SUMMARY   ");
        	mainDriver.SystemLog.info("************************************************************");
        	System.out.println();
        	mainDriver.SystemLog.info("SELECT: (1) GET BALANCE");
        	mainDriver.SystemLog.info("SELECT: (2) DEPOSIT ");
        	mainDriver.SystemLog.info("SELECT: (3) WITHDRAWL ");
        	mainDriver.SystemLog.info("SELECT: (4) TRANSFER (TO YOUR SAVING ACCOUNT)");
        	mainDriver.SystemLog.info("SELECT: (5) TRANSFER (TO A ANOTHER BANK QUALIFIED ACCOUNT)");
        	mainDriver.SystemLog.info("SELECT: (6) BACK TO ACCOUNT SUMMARY HOMEPAGE");
        	mainDriver.SystemLog.info("SELECT: (7) EXIT THE APPLICATION");
        	mainDriver.SystemLog.info("SELECT: (8) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();
 
		        switch (userChoice)
		        {
		            case 1:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			mainDriver.SystemLog.info("");
		            			mainDriver.SystemLog.info("ACCOUNT STATUS:   " + bankAccount.getAccountStatus());	
		            			mainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			mainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
		            			mainDriver.SystemLog.info("ACCOUNT NUMBER: " + bankAccount.getAccountID());
		            			mainDriver.SystemLog.info("ACCOUNT TYPE:   " + bankAccount.getAccountType());
		            			mainDriver.SystemLog.info("BALANCE:        " + bankAccount.getBalance());
		            		}
		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }

		                break;
		            case 2:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			mainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			mainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
			            		Double currentBalance = bankAccount.getBalance();;
			            		System.out.println();
			            		mainDriver.SystemLog.info("SYSTEM: ENTER DEPOSIT AMOUNT");
			            		String depositAmountString = myScanner.UserInput_String();
			            		Double depositAmount = Double.parseDouble(depositAmountString);
			            		Double newBalance = currentBalance + depositAmount;
			            		
			            		bankService.updateBankAccountBalance(loginCustomerID, checkAccType, newBalance);
			            		mainDriver.SystemLog.info("SYSTEM: $" + depositAmount + " HAS BEEN DEPOSIT INTO YOUR CHECKING ACCOUNT");
			            		System.out.println();
			            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
			            		mainDriver.SystemLog.info("ACCOUNT TYPE: " + bankAccount.getAccountType());
			            		mainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
		            		}
		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 3:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			mainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			mainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
			            		Double currentBalance = bankAccount.getBalance();;
			            		System.out.println();
			            		mainDriver.SystemLog.info("SYSTEM: ENTER WITHDRAWL AMOUNT");
			            		String withdrawlAmountString = myScanner.UserInput_String();
			            		Double withdrawlAmount = Double.parseDouble(withdrawlAmountString);
			            		Double newBalance = currentBalance - withdrawlAmount;
			            		
			            		if(newBalance < 0) 
			            		{
			            			mainDriver.SystemLog.info("SYSTEM: UNABLE TO PROCESS THE REQUESTED WITHDRAWL.");
			            			mainDriver.SystemLog.info("        PROCESSING THIS REQUEST WILL RESULT IN A NEGATIVE BALANCE");
			            		}
			            		else 
			            		{
				            		bankService.updateBankAccountBalance(loginCustomerID, checkAccType, newBalance);
				            		mainDriver.SystemLog.info("SYSTEM: $" + withdrawlAmount + " HAS BEEN WITHDRAWL FROM YOUR CHECKING ACCOUNT");
				            		System.out.println();
				            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
				            		mainDriver.SystemLog.info("ACCOUNT TYPE: " + bankAccount.getAccountType());
				            		mainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
			            		}
		            		}
		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 4:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			mainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			mainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
			            		Double currentBalance = bankAccount.getBalance();
			            		System.out.println();
			            		mainDriver.SystemLog.info("SYSTEM: ENTER TRANSFER AMOUNT");
			            		String transferAmountString = myScanner.UserInput_String();
			            		Double transferAmount = Double.parseDouble(transferAmountString);
			            		Double newBalance = currentBalance - transferAmount;
			            		bankAccount2 = bankService.getAccountInfo(loginCustomerID, saveAccType);
			            		Double transferAccBalance = bankAccount2.getBalance();
			            		transferAccBalance = transferAmount + transferAccBalance;
			            		
			            		if(newBalance < 0) 
			            		{
			            			mainDriver.SystemLog.info("SYSTEM: UNABLE TO PROCESS THE REQUESTED TRANSFER.");
			            			mainDriver.SystemLog.info("        PROCESSING THIS REQUEST WILL RESULT IN A NEGATIVE BALANCE");
			            		}
			            		else 
			            		{			            			
				            		bankService.updateBankAccountBalance(loginCustomerID, checkAccType, newBalance);
				            		bankService.updateBankAccountBalance(loginCustomerID, saveAccType, transferAccBalance);
				            		mainDriver.SystemLog.info("SYSTEM: $" + transferAmount + " HAS BEEN TRANSFERED TO YOUR CHECKING ACCOUNT");
				            		System.out.println();
				            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
				            		mainDriver.SystemLog.info("ACCOUNT TYPE: " + bankAccount.getAccountType());
				            		mainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
				            		System.out.println();
				            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
				            		mainDriver.SystemLog.info("ACCOUNT TYPE: " + bankAccount.getAccountType());
				            		mainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
			            		}
		            		}

		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 5:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			mainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			mainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
			            		Double currentBalance = bankAccount.getBalance();;
			            		System.out.println();
			            		mainDriver.SystemLog.info("SYSTEM: ENTER DESTINATION ACCOUNT FOR THE TRANSFER");
			            		String transferAccount = myScanner.UserInput_String().toUpperCase();
			            		mainDriver.SystemLog.info("SYSTEM: ENTER TRANSFER AMOUNT");
			            		String transferAmountString = myScanner.UserInput_String();
			            		Double transferAmount = Double.parseDouble(transferAmountString);
			            		Double newBalance = currentBalance - transferAmount;
			            		

			            		bankAccount2 = bankService.getAccountInfoByAccountID(transferAccount);
			            		Double transferAccBalance = bankAccount2.getBalance();
			            		transferAccBalance = transferAmount + transferAccBalance;
			            		
			            		if(newBalance < 0) 
			            		{
			            			mainDriver.SystemLog.info("SYSTEM: UNABLE TO PROCESS THE REQUESTED TRANSFER.");
			            			mainDriver.SystemLog.info("        PROCESSING THIS REQUEST WILL RESULT IN A NEGATIVE BALANCE");
			            		}
			            		else 
			            		{
				            		bankService.updateBankAccountBalance(loginCustomerID, checkAccType, newBalance);
				            		bankService.updateByTransfer(transferAccount, transferAccBalance);
				            		mainDriver.SystemLog.info("SYSTEM: $" + transferAmount + " HAS BEEN TRANSFERED TO ACCOUNT NUMBER " + transferAccount);
				            		System.out.println();
				            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
				            		mainDriver.SystemLog.info("ACCOUNT TYPE: " + bankAccount.getAccountType());
				            		mainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
			            		}
		            		}

		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 6:
		            	System.out.println();
		            	BankAppLoginWelcome_CustomerAccount.BankAppLoginWelcomePage_CustomerAccount();
		                break;
		            case 7:
		            	System.out.println();
		            	mainDriver.SystemLog.info("THANK YOU FOR USING MY BANKING APPLICATION");
		            	System.out.println();
						System.exit(0);
		                break;
		            case 8:
		            	System.out.println();
		        		welcomePage.BankWelcomePage();
		         		break;
		             default:
		            	 mainDriver.SystemLog.info("SYSTEM: INVALID OPTION");
		            	 break;
	    		
		        }
    	}
	    while(userChoice != 8);
    	
    }

}
