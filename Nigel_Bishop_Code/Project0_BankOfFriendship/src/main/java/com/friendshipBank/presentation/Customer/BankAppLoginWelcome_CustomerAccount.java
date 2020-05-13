package com.friendshipBank.presentation.Customer;

import com.friendshipBank.Main.mainDriver;
import com.friendshipBank.presentation.welcomePage;
import com.friendshipBank.service.impl.myScanner;

public class BankAppLoginWelcome_CustomerAccount 
{
	
	public static String loginCustomerID;

	
	public static void BankAppLoginWelcomePage_CustomerAccount() 
	{
	
    	int userChoice = 0;
 	
    	do {
    		
        	System.out.println();
        	mainDriver.SystemLog.info("  WELCOME TO BANK OF FRIENDSHIP ACCOUNT SUMMARY   ");
        	mainDriver.SystemLog.info("**************************************************");
        	System.out.println();
        	mainDriver.SystemLog.info("SELECT: (1) CHECKING ACCOUNT");
        	mainDriver.SystemLog.info("SELECT: (2) SAVING ACCOUNT");
        	mainDriver.SystemLog.info("SELECT: (3) EXIT THE APPLICATION");
        	mainDriver.SystemLog.info("SELECT: (4) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();

        	switch(userChoice) {
			case 1:
				CustomerAccount_Checking.CheckingWelcome();
	        	break;
			case 2:
				CustomerAccount_Saving.SavingWelcome();
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
