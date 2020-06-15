package com.friendshipBank.presentation.Customer;

import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.presentation.WelcomePage;

public class BankAppLoginWelcome_CustomerAccount 
{
	
	public static String loginCustomerID;

	public static void BankAppLoginWelcomePage_CustomerAccount() 
	{
	
    	int userChoice = 0;
 	
    	do {
    		
        	MainDriver.SystemLog.info("\n  WELCOME TO BANK OF FRIENDSHIP ACCOUNT SUMMARY   ");
        	MainDriver.SystemLog.info("**************************************************");
        	MainDriver.SystemLog.info("SELECT: (1) CHECKING ACCOUNT");
        	MainDriver.SystemLog.info("SELECT: (2) SAVING ACCOUNT");
        	MainDriver.SystemLog.info("SELECT: (3) LOG OUT");
        	MainDriver.SystemLog.info("SELECT: (4) EXIT THE APPLICATION");

			userChoice = myScanner.UserInput_menuChoice();

        	switch(userChoice) {
			case 1:
				CustomerAccount_Checking.CheckingWelcome();
	        	break;
			case 2:
				CustomerAccount_Saving.SavingWelcome();
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
