package com.friendshipBank.presentation.Customer;

import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.model.transaction;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.transactionService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.transactionServiceImpl;
import com.friendshipBank.presentation.WelcomePage;

public class CustomerAccount_Saving 
{
	public static String loginCustomerID;
	private static String checkAccType = "CHECKING";
	private static String saveAccType = "SAVING";
	private static String accStatus = "PENDING";
	
	private static String transTypeDep = "DEPOSIT";
	private static String transTypeWith = "WITHDRAWL";
	private static String transTypeTrans = "TRANSFER";
	
    public static void SavingWelcome()
    {	
		bankAccountService bankService = new bankAccountServiceImpl();
		bankAccount bankAccount = new bankAccount();
		bankAccount bankAccount2 = new bankAccount();
    	
		transactionService transService = new transactionServiceImpl();
		transaction transaction = new transaction();
		List<transaction> transList = new ArrayList<>();
		
    	int userChoice = 0;
    	
    	do 
    	{
        	MainDriver.SystemLog.info("\n  SAVING ACCOUNT SUMMARY   ");
        	MainDriver.SystemLog.info("************************************************************");
        	MainDriver.SystemLog.info("SELECT: (1) GET BALANCE");
        	MainDriver.SystemLog.info("SELECT: (2) DEPOSIT ");
        	MainDriver.SystemLog.info("SELECT: (3) WITHDRAWL ");
        	MainDriver.SystemLog.info("SELECT: (4) TRANSFER (TO YOUR CHECKING ACCOUNT)");
        	MainDriver.SystemLog.info("SELECT: (5) TRANSFER (TO A ANOTHER BANK QUALIFIED ACCOUNT)");
        	MainDriver.SystemLog.info("SELECT: (6) VIEW TRANSACTION LOG)");
        	MainDriver.SystemLog.info("SELECT: (7) BACK TO ACCOUNT SUMMARY HOMEPAGE");
        	MainDriver.SystemLog.info("SELECT: (8) LOG OUT");
        	MainDriver.SystemLog.info("SELECT: (9) EXIT THE APPLICATION");
			userChoice = myScanner.UserInput_menuChoice();
 
		        switch (userChoice)
		        {
		            case 1:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			MainDriver.SystemLog.info("");
		            			MainDriver.SystemLog.info("ACCOUNT STATUS:   " + bankAccount.getAccountStatus());	
		            			MainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			MainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
		            			MainDriver.SystemLog.info("ACCOUNT NUMBER: " + bankAccount.getAccountID());
		            			MainDriver.SystemLog.info("ACCOUNT TYPE:   " + bankAccount.getAccountType());
		            			MainDriver.SystemLog.info("BALANCE:        " + bankAccount.getBalance());	
		            		}
		            	}
			            catch (BusinessException e)
			            {
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
			            }

		                break;
		            case 2:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			MainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			MainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
			            		Double currentBalance = bankAccount.getBalance();;
			            		MainDriver.SystemLog.info("\nSYSTEM: ENTER DEPOSIT AMOUNT");		            		
			            		String depositAmountString = myScanner.money();
			            		Double depositAmount = Double.parseDouble(depositAmountString);
			            		Double newBalance = currentBalance + depositAmount;
			            		
			            		if(depositAmount <= 0 || depositAmount == null) {
			            			MainDriver.SystemLog.info("SYSTEM:  TRANSACTION CANCEL");
			            		}
			            		else
			            		{
			            			//DEPOSIT
				            		bankService.updateBankAccountBalance(loginCustomerID, saveAccType, newBalance);
				            		MainDriver.SystemLog.info("SYSTEM: $" + depositAmount + " HAS BEEN DEPOSIT INTO YOUR SAVING ACCOUNT");
				            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
				            		MainDriver.SystemLog.info("\nACCOUNT TYPE: " + bankAccount.getAccountType());
				            		MainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
				            		
				            		//ADDING TO TRANSACTION LOG
				            		transaction.setAccountID(bankAccount.getAccountID());
				            		transaction.setCustomerID(loginCustomerID);
				            		transaction.setAccountType(saveAccType);
				            		transaction.setBalance(newBalance);
				            		transaction.setTransAmount(depositAmount);
				            		transaction.setTransType(transTypeDep);
				            		transService.createNewBankTransaction(transaction);
			            		}
		            		}	
		            	}
			            catch (BusinessException e)
			            {
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 3:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			MainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			MainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
			            		Double currentBalance = bankAccount.getBalance();;
			            		MainDriver.SystemLog.info("\nSYSTEM: ENTER WITHDRAWL AMOUNT");
			            		String withdrawlAmountString = myScanner.money();
			            		Double withdrawlAmount = Double.parseDouble(withdrawlAmountString);
			            		Double newBalance = currentBalance - withdrawlAmount;
			            		
			            		if(withdrawlAmount <= 0 || withdrawlAmount == null) {
			            			MainDriver.SystemLog.info("SYSTEM:  TRANSACTION CANCEL");
			            		}
			            		else
			            		{
				            		if(newBalance <= 0) 
				            		{
				            			MainDriver.SystemLog.info("SYSTEM: UNABLE TO PROCESS THE REQUESTED WITHDRAWL.");
				            			MainDriver.SystemLog.info("        PROCESSING THIS REQUEST WILL RESULT IN A NEGATIVE BALANCE");
				            		}
				            		else 
				            		{
				            			//WITHDRAWL
					            		bankService.updateBankAccountBalance(loginCustomerID, saveAccType, newBalance);
					            		MainDriver.SystemLog.info("SYSTEM: $" + withdrawlAmount + " HAS BEEN WITHDRAWL FROM YOUR CHECKING ACCOUNT");
					            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
					            		MainDriver.SystemLog.info("\nACCOUNT TYPE: " + bankAccount.getAccountType());
					            		MainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
					            		
					            		//ADDING TO TRANSACTION LOG
					            		transaction.setAccountID(bankAccount.getAccountID());
					            		transaction.setCustomerID(loginCustomerID);
					            		transaction.setAccountType(saveAccType);
					            		transaction.setBalance(newBalance);
					            		transaction.setTransAmount(withdrawlAmount);
					            		transaction.setTransType(transTypeWith);
					            		transService.createNewBankTransaction(transaction);
				            		}
			            		}
		            		}
		            	}
			            catch (BusinessException e)
			            {
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 4:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			MainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			MainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
			            		Double currentBalance = bankAccount.getBalance();
			            		MainDriver.SystemLog.info("\nSYSTEM: ENTER TRANSFER AMOUNT");
			            		String transferAmountString = myScanner.money();
			            		Double transferAmount = Double.parseDouble(transferAmountString);
			            		Double newBalance = currentBalance - transferAmount;
			            		
			            		if(transferAmount <= 0 || transferAmount == null) {
			            			MainDriver.SystemLog.info("SYSTEM:  TRANSACTION CANCEL");
			            		}
			            		else
			            		{
			            			//CHECKING ACCOUNT INFO
				            		bankAccount2 = bankService.getAccountInfo(loginCustomerID, checkAccType);
				            		Double transferAccBalance = bankAccount2.getBalance();
				            		transferAccBalance = transferAmount + transferAccBalance;
				            		
				            		if(newBalance <= 0) 
				            		{
				            			MainDriver.SystemLog.info("SYSTEM: UNABLE TO PROCESS THE REQUESTED TRANSFER.");
				            			MainDriver.SystemLog.info("        PROCESSING THIS REQUEST WILL RESULT IN A NEGATIVE BALANCE");
				            		}
				            		else 
				            		{
				            			//TRANSFER AND UPDATE ACCOUNTS
					            		bankService.updateBankAccountBalance(loginCustomerID, saveAccType, newBalance);
					            		bankService.updateBankAccountBalance(loginCustomerID, checkAccType, transferAccBalance);
					            		MainDriver.SystemLog.info("SYSTEM: $" + transferAmount + " HAS BEEN TRANSFERED TO YOUR CHECKING ACCOUNT");
					            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
					            		MainDriver.SystemLog.info("\nACCOUNT TYPE: " + bankAccount.getAccountType());
					            		MainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
					            		bankAccount = bankService.getAccountInfo(loginCustomerID, checkAccType);
					            		MainDriver.SystemLog.info("\nACCOUNT TYPE: " + bankAccount.getAccountType());
					            		MainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
					            		
					            		//ADDING TO TRANSACTION LOG
					            		transaction.setAccountID(bankAccount.getAccountID());
					            		transaction.setCustomerID(loginCustomerID);
					            		transaction.setAccountType(saveAccType);
					            		transaction.setBalance(newBalance);
					            		transaction.setTransAmount(transferAmount);
					            		transaction.setTransType(transTypeTrans);
					            		transService.createNewBankTransaction(transaction);
				            		}
			            		}
		            		}
		            	}
			            catch (BusinessException e)
			            {
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 5:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
		            		String accStat = bankAccount.getAccountStatus();

		            		if(accStat.equals(accStatus)) {
		            			MainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			MainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
			            		Double currentBalance = bankAccount.getBalance();;
			            		MainDriver.SystemLog.info("\nSYSTEM: ENTER DESTINATION ACCOUNT FOR THE TRANSFER");
			            		String transferAccount = myScanner.UserInput_String().toUpperCase();
			            		MainDriver.SystemLog.info("SYSTEM: ENTER TRANSFER AMOUNT");	
			            		String transferAmountString = myScanner.money();
			            		Double transferAmount = Double.parseDouble(transferAmountString);
			            		Double newBalance = currentBalance - transferAmount;
			            		
			            		if(transferAmount <= 0 || transferAmount == null) {
			            			MainDriver.SystemLog.info("SYSTEM:  TRANSACTION CANCEL");
			            		}
			            		else
			            		{
				            		bankAccount2 = bankService.getAccountInfoByAccountID(transferAccount);
				            		Double transferAccBalance = bankAccount2.getBalance();
				            		transferAccBalance = transferAmount + transferAccBalance;
				            		
				            		if(newBalance <= 0) 
				            		{
				            			MainDriver.SystemLog.info("SYSTEM: UNABLE TO PROCESS THE REQUESTED TRANSFER.");
				            			MainDriver.SystemLog.info("        PROCESSING THIS REQUEST WILL RESULT IN A NEGATIVE BALANCE");
				            		}
				            		else 
				            		{
				            			//TRANSFER TO RANDOM ACCOUNT
					            		bankService.updateBankAccountBalance(loginCustomerID, saveAccType, newBalance);
					            		bankService.updateByTransfer(transferAccount, transferAccBalance);
					            		MainDriver.SystemLog.info("SYSTEM: $" + transferAmount + " HAS BEEN TRANSFERED TO ACCOUNT NUMBER " + transferAccount);
					            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
					            		MainDriver.SystemLog.info("\nACCOUNT TYPE: " + bankAccount.getAccountType());
					            		MainDriver.SystemLog.info("BALANCE:      " + bankAccount.getBalance());
					            		
					            		//ADDING TO TRANSACTION LOG
					            		transaction.setAccountID(bankAccount.getAccountID());
					            		transaction.setCustomerID(loginCustomerID);
					            		transaction.setAccountType(saveAccType);
					            		transaction.setBalance(newBalance);
					            		transaction.setTransAmount(transferAmount);
					            		transaction.setTransType(transTypeTrans);
					            		transService.createNewBankTransaction(transaction);
				            		}
			            		}
		            		}
		            	}
			            catch (BusinessException e)
			            {
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 6:
		            	try 
		            	{
		            		bankAccount = bankService.getAccountInfo(loginCustomerID, saveAccType);
		            		String accStat = bankAccount.getAccountStatus();
		            		
		            		if(accStat.equals(accStatus)) {
		            			MainDriver.SystemLog.info("ACCOUNT STATUS:   " + bankAccount.getAccountStatus());	
		            			MainDriver.SystemLog.info("SYSTEM: ACCOUNT IS IN PENDING STATUS, FUNCTIONALITY WILL BE LIMITED");
		            			MainDriver.SystemLog.info("        PLEASE CONTACT A BANK EMPLOYEE");
		            		}
		            		else 
		            		{
		            			//GET TRANSACTION
			            		transList = transService.getTransactionByAccountID(bankAccount.getAccountID());
			            		transList.forEach(user -> MainDriver.SystemLog.info(user));
		            		}
		            	}
		            	catch (BusinessException e)
		            	{
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
		            	}
		                break;
		            case 7:
		            	BankAppLoginWelcome_CustomerAccount.BankAppLoginWelcomePage_CustomerAccount();
		                break;
		            case 8:
		        		WelcomePage.BankWelcomePage();
		                break;
		            case 9:
		            	MainDriver.SystemLog.info("\nTHANK YOU FOR USING MY BANKING APPLICATION");
		            	System.exit(0);
		         		break;
		             default:
		            	 MainDriver.SystemLog.info("\nSYSTEM: INVALID OPTION");
		            	 break;
		        }
    	}
	    while(userChoice != 9);
    }
}
