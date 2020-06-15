package com.friendshipBank.presentation.Employee;

import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.presentation.WelcomePage;

public class EmployeeWelcomePage_Home 
{
	
	public static void EmployeeWelcomePage() 
	{
		
    	int userChoice = 0;
     	
    	do {
    		
        	MainDriver.SystemLog.info("\n  WELCOME TO BANK OF FRIENDSHIP EMPLOYEE DASHBOARD   ");
        	MainDriver.SystemLog.info("**************************************************");
        	MainDriver.SystemLog.info("SELECT: (1) VIEW LOGIN ACCOUNTS");
        	MainDriver.SystemLog.info("SELECT: (2) VIEW BANK ACCOUNTS (CHK | SAV)");
        	MainDriver.SystemLog.info("SELECT: (3) VIEW CUSTOMER ACCOUNTS (PROFILE)");
        	MainDriver.SystemLog.info("SELECT: (4) VIEW ALL BANK TRANSACTIONS");
        	MainDriver.SystemLog.info("SELECT: (5) LOG OUT");
        	MainDriver.SystemLog.info("SELECT: (6) EXIT THE APPLICATION");

			userChoice = myScanner.UserInput_menuChoice();

        	switch(userChoice) {
			case 1:
				EmployeeWelcomePage_ViewLoginAccounts.LoginAccounts();
	        	break;
			case 2:
				EmployeeWelcomePage_ViewBankAccounts.BankAccounts();
	        	break;
			case 3:
				EmployeeWelcomePage_ViewCustomerAccounts.EmployeeAccounts();
	        	break;
			case 4:
				EmployeeWelcomePage_ViewBankTransactions.bankTransaction();
	        	break;
			case 5:
        		WelcomePage.BankWelcomePage();
         		break;
			case 6:
            	MainDriver.SystemLog.info("\nTHANK YOU FOR USING MY BANKING APPLICATION");
				System.exit(0);
         		break;
        	
        	}
    	}
    	while(userChoice != 6);
		
		
	}

}
