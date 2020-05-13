package com.friendshipBank.presentation.Employee;

import com.friendshipBank.Main.mainDriver;
import com.friendshipBank.presentation.welcomePage;
import com.friendshipBank.service.impl.myScanner;

public class BankAppLoginWelcome_EmployeeAccount 
{
	
	public static void EmployeeWelcomePage() 
	{
		
    	int userChoice = 0;
     	
    	do {
    		
        	System.out.println();
        	mainDriver.SystemLog.info("  WELCOME TO BANK OF FRIENDSHIP EMPLOYEE DASHBOARD   ");
        	mainDriver.SystemLog.info("**************************************************");
        	System.out.println();
        	mainDriver.SystemLog.info("SELECT: (1) VIEW LOGIN ACCOUNTS");
        	mainDriver.SystemLog.info("SELECT: (2) VIEW BANK ACCOUNTS (CHK - SAV)");
        	mainDriver.SystemLog.info("SELECT: (3) EXIT THE APPLICATION");
        	mainDriver.SystemLog.info("SELECT: (4) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();

        	switch(userChoice) {
			case 1:
				EmployeeWelcomePage_ViewLoginAccounts.LoginAccounts();
	        	break;
			case 2:
				EmployeeWelcomePage_ViewBankAccounts.BankAccounts();
	        	break;
			case 3:
            	System.out.println();
            	mainDriver.SystemLog.info("THANK YOU FOR USING MY BANKING APPLICATION");
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
