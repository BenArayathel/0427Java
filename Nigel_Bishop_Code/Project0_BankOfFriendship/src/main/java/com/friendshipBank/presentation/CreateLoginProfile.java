package com.friendshipBank.presentation;

import com.friendshipBank.Main.mainDriver;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class CreateLoginProfile 
{
	public static String loginCustomerID;
	private static String Password1;
	private static String Password2;
	private static String accountStatus = "PENDING";
	
	public static void CreateNewLoginProfile() 
	{
		userAccessService userService = new userAccessServiceImpl();
		userAccess userAccess = new userAccess();
	
    	int userChoice = 0;
 	
    	do {
    		
        	System.out.println();
        	mainDriver.SystemLog.info("  BANK OF FRIENDSHIP NEW LOGIN PROFILE CREATION   ");
        	mainDriver.SystemLog.info("**************************************************");
        	mainDriver.SystemLog.info("SELECT: (1) CREATE NEW LOGIN PROFILE     ");
        	mainDriver.SystemLog.info("SELECT: (2) EXIT THE APPLICATION");
        	mainDriver.SystemLog.info("SELECT: (3) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();

        	switch(userChoice) {
			case 1:
	        	try 
	        	{
	        		mainDriver.SystemLog.info("SYSTEM:  PLEASE ENTER VALID INFORMATION FOR NEW LOGIN PROFILE CREATION");
					System.out.println();
					userAccess.setCustomerID(loginCustomerID);
					mainDriver.SystemLog.info("SYSTEM:  UserName: (Must be eight(8) or more Alpha Numeric characters");
					mainDriver.SystemLog.info("SYSTEM:  ENTER USERNAME");
		        	userAccess.setUserName(myScanner.UserInput_LongString());
		        	mainDriver.SystemLog.info("SYSTEM:  Password:  Must be eight(8) or more Alpha Numeric characters" );
		        	mainDriver.SystemLog.info("                    Must contain at least: One(1) Lowercase letter, One(1) Uppercase letter and One(1) Number" );
		        	mainDriver.SystemLog.info("SYSTEM:  ENTER PASSWORD");
		        	Password1 = myScanner.UserInput_LongString();
		        	mainDriver.SystemLog.info("SYSTEM:  RE-ENTER PASSWORD: (Confirmation)");
		        	Password2 = myScanner.UserInput_LongString();
		        	if(Password1.equals(Password2)) {
			        	userAccess.setUserPassword(Password2);
		        	}
		        	else {
		        		mainDriver.SystemLog.info("SYSTEM:  PASSWORD MISMATCH, PLEASE RE-ENTER INFOMRATION");
		        		CreateNewLoginProfile();
		        	}
		        	userAccess.setAccountStatus(accountStatus);
		        	
		       		userAccess = userService.createUserAccess(userAccess);
		       		if(userAccess.getUserID() != null) {
		       			System.out.println(userAccess);
		       		}
		       		
		       		welcomePage.BankWelcomePage();
	        	}
	        	catch (BusinessException e)
	        	{
	        		mainDriver.SystemLog.error(e.getMessage());
	        		mainDriver.SystemLog.info(e.getMessage());
	        	}
	        	

				break;
			case 2:
            	System.out.println();
            	mainDriver.SystemLog.info("THANK YOU FOR USING MY BANKING APPLICATION");
            	System.out.println();
				System.exit(0);
         		break;
        	
			case 3:
            	System.out.println();
        		welcomePage.BankWelcomePage();
         		break;
        	
        	}
    	}
    	while(userChoice != 3);
		
	}

}
