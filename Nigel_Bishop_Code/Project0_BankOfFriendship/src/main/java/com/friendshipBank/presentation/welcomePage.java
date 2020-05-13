package com.friendshipBank.presentation;

import com.friendshipBank.Main.mainDriver;
import com.friendshipBank.service.impl.myScanner;

public class welcomePage 
{
    public static void BankWelcomePage()
    {
    	
    	int userChoice = 0;
    	
    	do {
        	System.out.println();
        	mainDriver.SystemLog.info("************************************************");
        	mainDriver.SystemLog.info("*        WELCOME TO BANK OF FRIENDSHIP         *");
        	mainDriver.SystemLog.info("************************************************");
        	System.out.println();
        	mainDriver.SystemLog.info("SELECT: (1) LOGIN PAGE");
        	mainDriver.SystemLog.info("SELECT: (2) NEW USER CREATION PAGE");
        	mainDriver.SystemLog.info("SELECT: (3) EXIT APPLICATION");
        	
        	userChoice = myScanner.UserInput_Int();
        	
            switch (userChoice)
            {
                case 1:
                	BankApplicationLogin.LoginToBankApplication();
                    break;
                case 2:
                	CreateNewCustomer.CreateNewBankCustomer();
                    break;
                case 3:
                	System.out.println();
                	mainDriver.SystemLog.info("THANK YOU FOR USING MY BANKING APPLICATION");
                	System.out.println();
    				System.exit(0);
                    break;
            }
    	

        }while(userChoice !=3);
    }

}
