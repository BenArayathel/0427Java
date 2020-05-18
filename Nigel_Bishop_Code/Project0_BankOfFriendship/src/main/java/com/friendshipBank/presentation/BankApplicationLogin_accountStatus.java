package com.friendshipBank.presentation;

import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.presentation.Customer.BankAppLoginWelcome_CustomerAccount;
import com.friendshipBank.presentation.Customer.CustomerAccount_Checking;
import com.friendshipBank.presentation.Customer.CustomerAccount_Saving;
import com.friendshipBank.presentation.Employee.EmployeeWelcomePage_Home;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class BankApplicationLogin_accountStatus 
{
	private static String PENDING = "PENDING";
	private static String CUSTOMER = "CUSTOMER";
	private static String EMPLOYEE = "EMPLOYEE";
	private static String REJECTED = "REJECTED";
	
	public static void BankApplicationLogin()
	{
		userAccessService userService = new userAccessServiceImpl();
		userAccess userAccess = new userAccess();
		
		try 
    	{
    		MainDriver.SystemLog.info("SYSTEM:  ENTER USERNAME");
    		String uname = myScanner.UserInput_LongString();
    		MainDriver.SystemLog.info("SYSTEM:  ENTER PASSWORD");
			String upass = myScanner.UserInput_LongString();
			userAccess = userService.loginByUsernameAndPassword(uname, upass);
			
			if(userAccess.getUserName().equals(uname) && userAccess.getUserPassword().equals(upass)) {
				System.out.println();
				if(userAccess.getAccountStatus().equals(PENDING)) 
				{
					MainDriver.SystemLog.info("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
					BankAppLoginWelcome_PendingAccount.loginCustomerID = userAccess.getCustomerID();
					BankAppLoginWelcome_PendingAccount.BankAppLoginWelcomePage_PendingAccount();
				}
				else if(userAccess.getAccountStatus().equals(CUSTOMER)) 
				{
					MainDriver.SystemLog.info("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
					BankAppLoginWelcome_CustomerAccount.loginCustomerID = userAccess.getCustomerID();
					CustomerAccount_Checking.loginCustomerID = userAccess.getCustomerID();
					CustomerAccount_Saving.loginCustomerID = userAccess.getCustomerID();
					BankAppLoginWelcome_CustomerAccount.BankAppLoginWelcomePage_CustomerAccount();
				}
				else if(userAccess.getAccountStatus().equals(EMPLOYEE)) 
				{
					MainDriver.SystemLog.info("SYSTEM:  " + userAccess.getUserName() + " LOGIN SUCCESSFULL");
					EmployeeWelcomePage_Home.EmployeeWelcomePage();
				}
				else if(userAccess.getAccountStatus().equals(REJECTED)) 
				{
					MainDriver.SystemLog.info("SYSTEM:  YOUR ACCOUNT HAS BEEN REJECTED AND IS MARKED FOR DELETION");
				}
			}
    	}
    	catch (BusinessException e)
    	{					

    		MainDriver.SystemLog.info(e.getMessage());
    		MainDriver.SystemLog.error(e.getMessage());
    	}
		
	}
}
