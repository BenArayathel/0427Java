package com.friendshipBank.presentation.Employee;

import java.util.ArrayList;
import java.util.List;

import com.friendshipBank.Main.mainDriver;
import com.friendshipBank.dao.customerDAO;
import com.friendshipBank.dao.userAccessDAO;
import com.friendshipBank.dao.impl.customerDAOImpl;
import com.friendshipBank.dao.impl.userAccessDAOImpl;
import com.friendshipBank.exception.BusinessException;
import com.friendshipBank.model.customer;
import com.friendshipBank.model.userAccess;
import com.friendshipBank.presentation.welcomePage;
import com.friendshipBank.service.userAccessService;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.service.impl.userAccessServiceImpl;

public class EmployeeWelcomePage_ViewLoginAccounts 
{
	private static String accStatus = "PENDING";
	private static String customeraccStatus = "CUSTOMER";
	private static String rejctedStatus = "REJECTED";
	
	public static void LoginAccounts() 
	{
		userAccessDAO userDAO = new userAccessDAOImpl();
		userAccessService userService = new userAccessServiceImpl();
		List<userAccess> userAccessList = new ArrayList<>();
		List<customer> customerList = new ArrayList<>();
		customerDAO  custDAO = new customerDAOImpl();
		customerServiceImpl custService = new customerServiceImpl();
		
		int userChoice = 0;
    	
    	do 
    	{
        	System.out.println();
        	mainDriver.SystemLog.info("  -  EMPLOYEE CUSOMTER LOGIN DASHBOARD  -    ");
        	mainDriver.SystemLog.info("******************************************");
        	System.out.println();
        	mainDriver.SystemLog.info("SELECT: (1) VIEW ALL LOGIN ACCOUNTS");
        	mainDriver.SystemLog.info("SELECT: (2) VIEW ALL LOGIN ACCOUNTS WITH PENDING STATUS ");
        	mainDriver.SystemLog.info("SELECT: (3) APPROVE PENDING LOGIN ACCOUNTS ");
        	mainDriver.SystemLog.info("SELECT: (4) REJECT PENDING LOGIN ACCOUNTS");
        	mainDriver.SystemLog.info("SELECT: (5) VIEW ALL CUSTOMER");
        	mainDriver.SystemLog.info("SELECT: (6) DELETE A CUSTOMER");
        	mainDriver.SystemLog.info("SELECT: (7) EXIT THE APPLICATION");
        	mainDriver.SystemLog.info("SELECT: (8) BACK TO EMPLOYEE DASHBOARD");
        	mainDriver.SystemLog.info("SELECT: (9) BACK TO HOMEPAGE");

			userChoice = myScanner.UserInput_Int();
 
		        switch (userChoice)
		        {
		            case 1:
		            	try 
		            	{
		            		userAccessList = userDAO.getAllUserLoginAccounts();
		            		userAccessList.forEach(user -> mainDriver.SystemLog.info(user));
		            		
		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }

		                break;
		            case 2:
		            	try 
		            	{
		            		userAccessList = userDAO.getAllUserLoginAccountsStatus(accStatus);
		            		userAccessList.forEach(user -> mainDriver.SystemLog.info(user));
		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 3:
		            	try 
		            	{
		                   	mainDriver.SystemLog.info("SYSTEM:  APPROVE PENDING LOGIN ACCOUNTS ");
			            	mainDriver.SystemLog.info("SYSTEM:  ENTER THE CUSTOMER ID NUMBER");
		            		String customerID = myScanner.UserInput_String().toUpperCase();
		            		userService.updateUserAccessStatus(customerID, customeraccStatus);
		                   	mainDriver.SystemLog.info("SYSTEM:  " + customerID + " PENDING ACCOUNT HAS BEEN APPROVED.. ");
		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 4:
		            	try 
		            	{
		                   	mainDriver.SystemLog.info("SYSTEM:  REJECT PENDING LOGIN ACCOUNTS ");
			            	mainDriver.SystemLog.info("SYSTEM:  ENTER THE CUSTOMER ID NUMBER");
		            		String customerID = myScanner.UserInput_String().toUpperCase();
		            		userService.updateUserAccessStatus(customerID, rejctedStatus);
		                   	mainDriver.SystemLog.info("SYSTEM:  " + customerID + " PENDING ACCOUNT HAS BEEN REJECTED FOR LOGIN STATUS.. ");

		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 5:
		            	try 
		            	{
		            		customerList = custDAO.getAllCustomers();
		            		customerList.forEach(user -> mainDriver.SystemLog.info(user));

		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 6:
		            	try 
		            	{
		                   	mainDriver.SystemLog.info("SYSTEM:  DELETE A CUSTOMER ACCOUNT ");
			            	mainDriver.SystemLog.info("SYSTEM:  ENTER THE CUSTOMER ID NUMBER");
		            		String customerID = myScanner.UserInput_String().toUpperCase();
		            		custService.deleteCustomer(customerID);

		                   	mainDriver.SystemLog.info("SYSTEM:  THE REQUESTED CUSTOMER HAS BEEN DELETED");

		            	}
			            catch (BusinessException e)
			            {
			            	mainDriver.SystemLog.info(e.getMessage());
			            	mainDriver.SystemLog.error(e.getMessage());
			            }
		                break;
		            case 7:
		            	System.out.println();
		            	mainDriver.SystemLog.info("THANK YOU FOR USING MY BANKING APPLICATION");
		            	System.out.println();
						System.exit(0);
		                break;
		            case 8:
		            	System.out.println();
		            	BankAppLoginWelcome_EmployeeAccount.EmployeeWelcomePage();
		         		break;
		            case 9:
		            	System.out.println();
		        		welcomePage.BankWelcomePage();
		         		break;
		             default:
		            	 mainDriver.SystemLog.info("SYSTEM: INVALID OPTION");
		            	 break;
	    		
		        }
    	}
	    while(userChoice != 9);
		
		
		
	}

}
