package com.friendshipBank.presentation.newuser;

import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.presentation.WelcomePage;
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
	
    	int userChoice = 1;
 	
    	do {
        	MainDriver.SystemLog.info("\n  NEW LOGIN PROFILE CREATION   ");
        	MainDriver.SystemLog.info("**************************************************");
//        	MainDriver.SystemLog.info("SELECT: (1) CREATE NEW LOGIN PROFILE     ");
//        	MainDriver.SystemLog.info("SELECT: (2) BACK TO HOMEPAGE");
//        	MainDriver.SystemLog.info("SELECT: (3) EXIT THE APPLICATION");
//
//			userChoice = myScanner.UserInput_menuChoice();

        	switch(userChoice) {
			case 1:
	        	try 
	        	{
	        		MainDriver.SystemLog.info("SYSTEM:  PLEASE ENTER VALID INFORMATION FOR NEW LOGIN PROFILE CREATION");
					userAccess.setCustomerID(loginCustomerID);
					MainDriver.SystemLog.info("\nSYSTEM:  UserName: (Must be eight(8) or more Alpha Numeric characters");
					MainDriver.SystemLog.info("SYSTEM:  ENTER USERNAME");
		        	userAccess.setUserName(myScanner.UserInput_LongString());
		        	MainDriver.SystemLog.info("SYSTEM:  Password:  Must be eight(8) or more Alpha Numeric characters" );
		        	MainDriver.SystemLog.info("                    Must contain at least: One(1) Lowercase letter, One(1) Uppercase letter and One(1) Number" );
		        	MainDriver.SystemLog.info("SYSTEM:  ENTER PASSWORD");
		        	Password1 = myScanner.UserInput_LongString();
		        	MainDriver.SystemLog.info("SYSTEM:  RE-ENTER PASSWORD: (Confirmation)");
		        	Password2 = myScanner.UserInput_LongString();
		        	if(Password1.equals(Password2)) {
			        	userAccess.setUserPassword(Password2);
		        	}
		        	else {
		        		MainDriver.SystemLog.info("SYSTEM:  PASSWORD MISMATCH, PLEASE RE-ENTER INFOMRATION");
		        		CreateNewLoginProfile();
		        	}
		        	userAccess.setAccountStatus(accountStatus);
		        	
		       		userAccess = userService.createUserAccess(userAccess);
		       		if(userAccess.getUserID() != null) {
		        		MainDriver.SystemLog.info("\nSYSTEM:  CUSTOMERID: " + loginCustomerID + " LOGIN PROFILE HAS SUCCESSFULLY BEEN CREATED");
	    				MainDriver.SystemLog.info("SYSTEM:  PLEASE MAKE A NOTE OF YOUR LOGIN USERNAME: " + userAccess.getUserName());
	    				MainDriver.SystemLog.info(userAccess);
		       		}
		       		
		       		WelcomePage.BankWelcomePage();
	        	}
	        	catch (BusinessException e)
	        	{
	        		MainDriver.SystemLog.error(e.getMessage());
	        		MainDriver.SystemLog.info(e.getMessage());
	        	}
	        	

				break;
			case 2:
        		WelcomePage.BankWelcomePage();
         		break;
			case 3:
        		MainDriver.SystemLog.info("\nTHANK YOU FOR USING MY BANKING APPLICATION");
				System.exit(0);
         		break;
            default:
            	 MainDriver.SystemLog.info("\nSYSTEM:  INVALID OPTION");
            	break;
        	
        	}
    	}
    	while(userChoice != 3);
		
	}

}
