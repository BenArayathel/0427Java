package com.friendshipBank.presentation.Employee;

import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.Main.mainDriver;
import com.friendshipBank.dao.bankAccountDAO;
import com.friendshipBank.dao.impl.bankAccountDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.bankAccount;
import com.friendshipBank.presentation.welcomePage;
import com.friendshipBank.service.bankAccountService;
import com.friendshipBank.service.impl.bankAccountServiceImpl;
import com.friendshipBank.service.impl.myScanner;

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
        	mainDriver.SystemLog.info("  -  EMPLOYEE CUSOMTER LOGIN DASHBOARD  -    ");
        	mainDriver.SystemLog.info("******************************************");
        	System.out.println();
        	mainDriver.SystemLog.info("SELECT: (1) VIEW ALL BANK ACCOUNTS");
        	mainDriver.SystemLog.info("SELECT: (2) VIEW ALL BANK ACCOUNTS WITH PENDING STATUS ");
        	mainDriver.SystemLog.info("SELECT: (3) APPROVE PENDING BANK ACCOUNTS ");
        	mainDriver.SystemLog.info("SELECT: (4) REJECT BANK LOGIN ACCOUNTS");
        	mainDriver.SystemLog.info("SELECT: (5) EXIT THE APPLICATION");
        	mainDriver.SystemLog.info("SELECT: (6) BACK TO EMPLOYEE DASHBOARD");
        	mainDriver.SystemLog.info("SELECT: (7) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();
 
		        switch (userChoice)
		        {
		            case 1:
		            	try 
		            	{
		            		bankAccountList = bankDAO.getAllBankAccounts();
		            		bankAccountList.forEach(user -> mainDriver.SystemLog.info(user));
		            		
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
		            		bankAccountList = bankDAO.getAllBankAccountsStatus(accStatus);
		            		bankAccountList.forEach(user -> mainDriver.SystemLog.info(user));
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
		                   	mainDriver.SystemLog.info("SYSTEM:  APPROVE PENDING BANK ACCOUNTS ");
			            	mainDriver.SystemLog.info("SYSTEM:  ENTER THE CUSTOMER ID NUMBER");
		            		String customerID = myScanner.UserInput_String().toUpperCase();
			            	mainDriver.SystemLog.info("SYSTEM:  ENTER ACCOUNT TYPE (CHECKING OR SAVING)");
		            		String accountType = myScanner.UserInput_String().toUpperCase();
		            		bankService.updateBankAccountStatus(customerID, accountType, activeStatus);
		                   	mainDriver.SystemLog.info("SYSTEM:  " + customerID + " PENDING ACCOUNT HAS BEEN APPROVED.. ");
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
		                   	mainDriver.SystemLog.info("SYSTEM:  REJECT PENDING BANK ACCOUNTS ");
			            	mainDriver.SystemLog.info("SYSTEM:  ENTER THE CUSTOMER ID NUMBER");
		            		String customerID = myScanner.UserInput_String().toUpperCase();
			            	mainDriver.SystemLog.info("SYSTEM:  ENTER ACCOUNT TYPE (CHECKING OR SAVING");
		            		String accountType = myScanner.UserInput_String().toUpperCase();
		            		bankService.updateBankAccountStatus(customerID, accountType,rejctedStatus );
		                   	mainDriver.SystemLog.info("SYSTEM:  " + customerID + " PENDING ACCOUNT HAS BEEN REJECTED .. ");

		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 5:
		            	System.out.println();
		            	mainDriver.SystemLog.info("THANK YOU FOR USING MY BANKING APPLICATION");
		            	System.out.println();
						System.exit(0);
		                break;
		            case 6:
		            	System.out.println();
		            	BankAppLoginWelcome_EmployeeAccount.EmployeeWelcomePage();
		         		break;
		            case 7:
		            	System.out.println();
		        		welcomePage.BankWelcomePage();
		         		break;
		             default:
		            	 mainDriver.SystemLog.info("SYSTEM: INVALID OPTION");
		            	 break;
	    		
		        }
    	}
	    while(userChoice != 7);
	}

}
