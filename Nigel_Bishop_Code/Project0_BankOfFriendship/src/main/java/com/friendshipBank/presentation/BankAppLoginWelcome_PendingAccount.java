package com.friendshipBank.presentation;

import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class BankAppLoginWelcome_PendingAccount 
{
	
	public static void BankAppLoginWelcomePage_PendingAccount() 
	{
		
		userAccessService userService = new userAccessServiceImpl();
		userAccess userAccess = new userAccess();
		
    	int userChoice = 0;
 	
    	do {
    		
        	System.out.println();
        	System.out.println("  WELCOME TO BANK OF FRIENDSHIP ACCOUNT SUMMARY   ");
        	System.out.println("**************************************************");
        	System.out.println("SELECT: (1) REQUEST CHECKING ACCOUNT");
        	System.out.println("SELECT: (2) REQUEST SAVING ACCOUNT");
        	System.out.println("SELECT: (3) EXIT THE APPLICATION");
        	System.out.println("SELECT: (4) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();

        	switch(userChoice) {
			case 1:
	        	try 
	        	{
	        		String uname = myScanner.UserInput_LongString();
					String upass = myScanner.UserInput_LongString();				
					userAccess = userService.loginByUsernameAndPassword(uname, upass);
				}
	        	catch (BusinessException e)
	        	{
	    			System.out.println(e.getMessage());
	        	}
	        	break;
			case 2:
	        	try 
	        	{
	        		String uname = myScanner.UserInput_LongString();
					String upass = myScanner.UserInput_LongString();				
					userAccess = userService.loginByUsernameAndPassword(uname, upass);
				}
	        	catch (BusinessException e)
	        	{
	    			System.out.println(e.getMessage());
	        	}
	        	break;
			case 3:
            	System.out.println();
            	System.out.println("THANK YOU FOR USING MY BANKING APPLICATION");
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
