package com.friendshipBank.presentation;

import com.friendshipBank.Main.mainDriver;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.presentation.Customer.BankAppLoginWelcome_CustomerAccount;
import com.friendshipBank.presentation.Customer.CustomerAccount_Checking;
import com.friendshipBank.presentation.Customer.CustomerAccount_Saving;
import com.friendshipBank.presentation.Employee.BankAppLoginWelcome_EmployeeAccount;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class BankApplicationLogin 
{
	private static String PENDING = "PENDING";
	private static String CUSTOMER = "CUSTOMER";
	private static String EMPLOYEE = "EMPLOYEE";
	private static String REJECTED = "REJECTED";
	
	public static void LoginToBankApplication() 
	{
		
		userAccessService userService = new userAccessServiceImpl();
		userAccess userAccess = new userAccess();
		
    	int userChoice = 0;
 	
    	do {
    		
        	System.out.println();
        	mainDriver.SystemLog.info("  BANK OF FRIENDSHIP LOGIN PAGE   ");
        	mainDriver.SystemLog.info("**************************************************");
        	mainDriver.SystemLog.info("SELECT: (1) LOGIN TO BANK APPLICATION");
        	mainDriver.SystemLog.info("SELECT: (2) EXIT THE APPLICATION");
        	mainDriver.SystemLog.info("SELECT: (3) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();

        	switch(userChoice) {
			case 1:
	        	try 
	        	{
	        		mainDriver.SystemLog.info("SYSTEM:  ENTER USERNAME");
	        		String uname = myScanner.UserInput_LongString();
	        		mainDriver.SystemLog.info("SYSTEM:  ENTER PASSWORD");
					String upass = myScanner.UserInput_LongString();
					
					userAccess = userService.loginByUsernameAndPassword(uname, upass);
					
					if(userAccess.getUserName().equals(uname) && userAccess.getUserPassword().equals(upass)) {
						System.out.println();
						if(userAccess.getAccountStatus().equals(PENDING)) 
						{
							mainDriver.SystemLog.info("SYSTEM:  LOGIN SUCCESSFULL");
							System.out.println();
							BankAppLoginWelcome_PendingAccount.loginCustomerID = userAccess.getCustomerID();
							BankAppLoginWelcome_PendingAccount.BankAppLoginWelcomePage_PendingAccount();
						}
						else if(userAccess.getAccountStatus().equals(CUSTOMER)) 
						{
							mainDriver.SystemLog.info("SYSTEM:  LOGIN SUCCESSFULL");
							BankAppLoginWelcome_CustomerAccount.loginCustomerID = userAccess.getCustomerID();
							CustomerAccount_Checking.loginCustomerID = userAccess.getCustomerID();
							CustomerAccount_Saving.loginCustomerID = userAccess.getCustomerID();
							BankAppLoginWelcome_CustomerAccount.BankAppLoginWelcomePage_CustomerAccount();
						}
						else if(userAccess.getAccountStatus().equals(EMPLOYEE)) 
						{
							mainDriver.SystemLog.info("SYSTEM:  LOGIN SUCCESSFULL");
							BankAppLoginWelcome_EmployeeAccount.EmployeeWelcomePage();
						}
						else if(userAccess.getAccountStatus().equals(REJECTED)) 
						{
							mainDriver.SystemLog.info("SYSTEM:  YOUR ACCOUNT HAS BEEN REJECTED AND MARK FOR DELETION");
						}
					}
	        	}
	        	catch (BusinessException e)
	        	{
	        		mainDriver.SystemLog.info(e.getMessage());
	        		mainDriver.SystemLog.error(e.getMessage());
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
