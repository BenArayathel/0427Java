package com.friendshipBank.presentation;

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

//		String Password1;
//		String Password2;
//		private static String accountStatus = "PENDING";
		
    	int userChoice = 0;
 	
    	do {
    		
        	System.out.println();
        	System.out.println("  BANK OF FRIENDSHIP NEW LOGIN PROFILE CREATION   ");
        	System.out.println("**************************************************");
        	System.out.println("SELECT: (1) CREATE NEW LOGIN PROFILE     ");
        	System.out.println("SELECT: (2) EXIT THE APPLICATION");
        	System.out.println("SELECT: (3) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();

        	switch(userChoice) {
			case 1:
	        	try 
	        	{
					System.out.println("PLEASE ENTER VALID INFORMATION FOR NEW LOGIN PROFILE CREATION");
					System.out.println();
					userAccess.setCustomerID(loginCustomerID);
		        	System.out.println("ENTER - UserName: (Must be eight(8) or more Alpha Numeric characters");
		        	userAccess.setUserName(myScanner.UserInput_LongString());
		        	System.out.println("ENTER - Password: (Must be eight(8) or more Alpha Numeric characters" );
		        	System.out.println("        PASWORD must contain at least: One(1) Lowercase letter, One(1) Uppercase letter and One(1) Number ");
		        	Password1 = myScanner.UserInput_LongString();
		        	System.out.println("ENTER - Password: (Confirmation)");
		        	Password2 = myScanner.UserInput_LongString();
		        	if(Password1.equals(Password2)) {
			        	userAccess.setUserPassword(Password2);
		        	}
		        	else {
		        		System.out.println("PASSWORD MISMATCH PLEASE RE-ENTER INFOMRATION");
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
	    			System.out.println(e.getMessage());
	        	}
	        	

				break;
			case 2:
            	System.out.println();
            	System.out.println("THANK YOU FOR USING MY BANKING APPLICATION");
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
