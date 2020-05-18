package com.friendshipBank.presentation.Employee;

import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.dao.bankAccountDAO;
import com.friendshipBank.dao.impl.bankAccountDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.presentation.WelcomePage;

public class EmployeeWelcomePage_ViewBankAccounts 
{
	private static String accStatus = "PENDING";
	private static String activeStatus = "ACTIVE";
	private static String rejctedStatus = "REJECTED";
	
	public static void BankAccounts() 
	{
		
		List<bankAccount> bankAccountList = new ArrayList<>();
		bankAccountDAO bankDAO = new bankAccountDAOImpl();
		bankAccountService bankService = new bankAccountServiceImpl();
		
		int userChoice = 0;
    	
    	do 
    	{
        	System.out.println();
        	MainDriver.SystemLog.info("  -  EMPLOYEE: CUSOMTER LOGIN DASHBOARD  -    ");
        	MainDriver.SystemLog.info("******************************************");
        	System.out.println();
        	MainDriver.SystemLog.info("SELECT: (1) VIEW ALL BANK ACCOUNTS");
        	MainDriver.SystemLog.info("SELECT: (2) VIEW ALL BANK ACCOUNTS WITH PENDING STATUS ");
        	MainDriver.SystemLog.info("SELECT: (3) APPROVE PENDING BANK ACCOUNTS ");
        	MainDriver.SystemLog.info("SELECT: (4) REJECT BANK LOGIN ACCOUNTS");
        	MainDriver.SystemLog.info("SELECT: (5) BACK TO EMPLOYEE DASHBOARD");
        	MainDriver.SystemLog.info("SELECT: (6) LOG OUT");
        	MainDriver.SystemLog.info("SELECT: (7) EXIT THE APPLICATION");

			userChoice = myScanner.UserInput_menuChoice();
 
		        switch (userChoice)
		        {
		            case 1:
		            	try 
		            	{
		            		bankAccountList = bankDAO.getAllBankAccounts();
		            		bankAccountList.forEach(user -> MainDriver.SystemLog.info(user));
		            		
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
		            		bankAccountList = bankDAO.getAllBankAccountsStatus(accStatus);
		            		if(bankAccountList.isEmpty()) 
		            		{
		            			MainDriver.SystemLog.info("SYSTEM:  NO PENDING BANK ACCOUNT");
		            		}
		            		else
		            		{
			            		bankAccountList.forEach(user -> MainDriver.SystemLog.info(user));

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
		                   	MainDriver.SystemLog.info("SYSTEM:  APPROVE PENDING BANK ACCOUNTS ");
			            	MainDriver.SystemLog.info("SYSTEM:  ENTER THE CUSTOMER ID NUMBER");
		            		String customerID = myScanner.UserInput_String().toUpperCase();
			            	MainDriver.SystemLog.info("SYSTEM:  ENTER ACCOUNT TYPE (CHECKING OR SAVING)");
		            		String accountType = myScanner.UserInput_String().toUpperCase();
		            		bankService.updateBankAccountStatus(customerID, accountType, activeStatus);
		                   	MainDriver.SystemLog.info("SYSTEM:  " + customerID + " PENDING ACCOUNT HAS BEEN APPROVED.. ");
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
		                   	MainDriver.SystemLog.info("SYSTEM:  REJECT PENDING BANK ACCOUNTS ");
			            	MainDriver.SystemLog.info("SYSTEM:  ENTER THE CUSTOMER ID NUMBER");
		            		String customerID = myScanner.UserInput_String().toUpperCase();
			            	MainDriver.SystemLog.info("SYSTEM:  ENTER ACCOUNT TYPE (CHECKING OR SAVING");
		            		String accountType = myScanner.UserInput_String().toUpperCase();
		            		bankService.updateBankAccountStatus(customerID, accountType,rejctedStatus );
		                   	MainDriver.SystemLog.info("SYSTEM:  " + customerID + " PENDING ACCOUNT HAS BEEN REJECTED .. ");

		            	}
			            catch (BusinessException e)
			            {
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 5:
		            	EmployeeWelcomePage_Home.EmployeeWelcomePage();
		                break;
		            case 6:
		        		WelcomePage.BankWelcomePage();
		         		break;
		            case 7:
		            	MainDriver.SystemLog.info("\nTHANK YOU FOR USING MY BANKING APPLICATION");
						System.exit(0);
		         		break;
		             default:
		            	 MainDriver.SystemLog.info("SYSTEM: INVALID OPTION");
		            	 break;
	    		
		        }
    	}
	    while(userChoice != 7);
	}

}
