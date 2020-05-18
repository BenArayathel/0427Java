package com.friendshipBank.presentation.Employee;

import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.dao.transactionLogDAO;
import com.friendshipBank.dao.impl.transactionLogDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.transaction;
import com.friendshipBank.presentation.WelcomePage;
import com.friendshipBank.service.transactionService;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.transactionServiceImpl;

public class EmployeeWelcomePage_ViewBankTransactions 
{
	public static void bankTransaction() 
	{
		transactionService transService = new transactionServiceImpl();
		transactionLogDAO transDAO = new transactionLogDAOImpl();
		List<transaction> transList = new ArrayList<>();
		
		int userChoice = 0;
    	
    	do 
    	{
        	MainDriver.SystemLog.info("\n    EMPLOYEE: CUSOMTER TRANSACTION DASHBOARD  -    ");
        	MainDriver.SystemLog.info("******************************************");
        	MainDriver.SystemLog.info("SELECT: (1) VIEW ALL BANK TRANSACTIONS");
        	MainDriver.SystemLog.info("SELECT: (2) VIEW TRANSACTIONS BY ACCOUNT NUMBER ");
        	MainDriver.SystemLog.info("SELECT: (3) BACK TO EMPLOYEE DASHBOARD");
          	MainDriver.SystemLog.info("SELECT: (4) LOG OUT");
        	MainDriver.SystemLog.info("SELECT: (5) EXIT THE APPLICATION");

			userChoice = myScanner.UserInput_menuChoice();
 
		        switch (userChoice)
		        {
		            case 1:
		            	try 
		            	{		            		
	            			//GET ALL TRANSACTION
		            		transList = transDAO.getAllBankTransaction();
		            		transList.forEach(user -> MainDriver.SystemLog.info(user));
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
	            			//GET TRANSACTION BY ACCOUNT NUMBER
		                   	MainDriver.SystemLog.info("SYSTEM:  VIEW TRANSACTION BY ACCOUNT NUMBER ");
			            	MainDriver.SystemLog.info("SYSTEM:  ENTER CUSTOMER ACOUNT ID NUMBER");
		            		String accountID = myScanner.UserInput_String().toUpperCase();
		            		transList = transService.getTransactionByAccountID(accountID);
		            		transList.forEach(user -> MainDriver.SystemLog.info(user));
		            	}
			            catch (BusinessException e)
			            {
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 3:
		            	EmployeeWelcomePage_Home.EmployeeWelcomePage();
		                break;
		            case 4:
		        		WelcomePage.BankWelcomePage();
		                break;
		            case 5:
		            	MainDriver.SystemLog.info("\nTHANK YOU FOR USING MY BANKING APPLICATION");
						System.exit(0);
		                break;
		             default:
		            	 MainDriver.SystemLog.info("\nSYSTEM: INVALID OPTION");
		            	 break;
	    		
		        }
    	}
	    while(userChoice != 5);
		
		
		
	}
}
