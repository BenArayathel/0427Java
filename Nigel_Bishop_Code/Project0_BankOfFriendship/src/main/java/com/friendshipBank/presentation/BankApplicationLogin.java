package com.friendshipBank.presentation;

import com.friendshipBank.Main.MainDriver;

public class BankApplicationLogin 
{
	
	public static void LoginToBankApplication() 
	{
				
    	int userChoice = 1;
 	
    	do {
        	MainDriver.SystemLog.info("\n  LOGIN PAGE   ");
        	MainDriver.SystemLog.info("**************************************************");

        	switch(userChoice++) {
			case 1:
				BankApplicationLogin_accountStatus.BankApplicationLogin();
	        	break;
			case 2:
				BankApplicationLogin_accountStatus.BankApplicationLogin();
         		break;
			case 3:
				BankApplicationLogin_accountStatus.BankApplicationLogin();
         		break;
			case 4:
            	MainDriver.SystemLog.info("\nSYSTEM:  YOU HAVE EXCEEDED THE NUMBER OF LOGIN ATTEMPTS");
        		WelcomePage.BankWelcomePage();
         		break;
            default:
            	 MainDriver.SystemLog.info("\nSYSTEM:  INVALID OPTION");
            	break;
        	}
    	}
    	while(userChoice != 5);
		
	}
	
	
	

}
