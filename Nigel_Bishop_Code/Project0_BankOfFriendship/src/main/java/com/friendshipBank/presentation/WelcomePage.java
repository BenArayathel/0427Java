package com.friendshipBank.presentation;

import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.presentation.newuser.CreateNewCustomer;
import com.friendshipBank.service.impl.myScanner;

public class WelcomePage 
{
    public static void BankWelcomePage()
    {
    	
    	int userChoice = 0;
    	
    	do {
        	MainDriver.SystemLog.info("\n************************************************");
        	MainDriver.SystemLog.info("*        WELCOME TO BANK OF FRIENDSHIP         *");
        	MainDriver.SystemLog.info("************************************************");
        	MainDriver.SystemLog.info("SELECT: (1) LOGIN PAGE");
        	MainDriver.SystemLog.info("SELECT: (2) NEW USER CREATION PAGE");
        	MainDriver.SystemLog.info("SELECT: (3) EXIT APPLICATION");        	
       	
        	userChoice = myScanner.UserInput_menuChoice();
        	
            switch (userChoice)
            {
                case 1:
                	BankApplicationLogin.LoginToBankApplication();
                    break;
                case 2:
                	CreateNewCustomer.CreateNewBankCustomer();
                    break;
                case 3:
                	MainDriver.SystemLog.info("\nTHANK YOU FOR USING MY BANKING APPLICATION");
    				System.exit(0);
                    break;
                default:
	            	 MainDriver.SystemLog.info("\nSYSTEM:  INVALID OPTION");
                	break;
            }
        }while(userChoice !=3);
    }

}
