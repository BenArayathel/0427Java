package com.friendshipBank.presentation.Employee;

import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.dao.customerDAO;
import com.friendshipBank.dao.impl.customerDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.customer;
import com.friendshipBank.presentation.WelcomePage;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.myScanner;

public class EmployeeWelcomePage_ViewCustomerAccounts 
{
	
	public static void EmployeeAccounts() 
	{
		List<customer> customerList = new ArrayList<>();
		customerDAO  custDAO = new customerDAOImpl();
		customerServiceImpl custService = new customerServiceImpl();
		
		int userChoice = 0;
    	
    	do 
    	{
        	MainDriver.SystemLog.info("\n  -  EMPLOYEE: CUSOMTER ACCOUNT DASHBOARD  -    ");
        	MainDriver.SystemLog.info("******************************************");
        	MainDriver.SystemLog.info("SELECT: (1) VIEW ALL CUSTOMER");
        	MainDriver.SystemLog.info("SELECT: (2) DELETE A CUSTOMER");
        	MainDriver.SystemLog.info("SELECT: (3) BACK TO EMPLOYEE DASHBOARD");
        	MainDriver.SystemLog.info("SELECT: (4) LOG OUT");
        	MainDriver.SystemLog.info("SELECT: (5) EXIT THE APPLICATION");

			userChoice = myScanner.UserInput_menuChoice();
 
		        switch (userChoice)
		        {
		            case 1:
		            	try 
		            	{
		            		customerList = custDAO.getAllCustomers();
		            		customerList.forEach(user -> MainDriver.SystemLog.info(user));

		            	}
			            catch (BusinessException e)
			            {
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 2:
		            	try 
		            	{
		                   	MainDriver.SystemLog.info("SYSTEM:  DELETE A CUSTOMER ACCOUNT ");
			            	MainDriver.SystemLog.info("SYSTEM:  ENTER THE CUSTOMER ID NUMBER");
		            		String customerID = myScanner.UserInput_String().toUpperCase();
		            		custService.deleteCustomer(customerID);

		                   	MainDriver.SystemLog.info("SYSTEM:  THE REQUESTED CUSTOMER HAS BEEN DELETED");

		            	}
			            catch (BusinessException e)
			            {
			            	MainDriver.SystemLog.info(e.getMessage());
			            	MainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 3:
		            	EmployeeWelcomePage_Home.EmployeeWelcomePage();
		                break;
		            case 4:
		        		WelcomePage.BankWelcomePage();
		         		break;
		            case 5:
		            	MainDriver.SystemLog.info("\nTHANK YOU FOR USING MY BANKING APPLICATION");
						System.exit(0);
		         		break;
		             default:
		            	 MainDriver.SystemLog.info("\nSYSTEM: INVALID OPTION");
		            	 break;
	    		
		        }
    	}
	    while(userChoice != 5);
		
		
		
	}
}
