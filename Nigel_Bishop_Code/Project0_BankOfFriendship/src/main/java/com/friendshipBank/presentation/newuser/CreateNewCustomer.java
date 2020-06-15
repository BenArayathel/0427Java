package com.friendshipBank.presentation.newuser;

import com.friendshipBank.model.customer;
import com.friendshipBank.presentation.WelcomePage;
import com.friendshipBank.service.customerService;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.Main.MainDriver;
import com.friendshipBank.exception.BusinessException;


public class CreateNewCustomer 
{
    public static void CreateNewBankCustomer()
    {
    	customerService custService = new customerServiceImpl();
    	customer customer = new customer();
    	
    	int userChoice = 0;
    	
    	do {
        	MainDriver.SystemLog.info("\n  NEW USER CREATION   ");
        	MainDriver.SystemLog.info("****************************************************");
        	MainDriver.SystemLog.info("SELECT: (1) CREATE NEW CUSTOMER PROFILE");
        	MainDriver.SystemLog.info("SELECT: (2) BACK TO HOMEPAGE");
        	MainDriver.SystemLog.info("SELECT: (3) EXIT THE APPLICATION");
    		
        	userChoice = myScanner.UserInput_menuChoice();

        	switch(userChoice) {
			case 1:
				MainDriver.SystemLog.info("SYSTEM:  PLEASE ENTER VALID CUSTOMER INFORMATION (NEW CUSTOMER PROFILE)");
				MainDriver.SystemLog.info("\nSYSTEM:  ENTER LAST NAME");
	        	customer.setLastName(myScanner.UserInput_LongString());
	        	MainDriver.SystemLog.info("SYSTEM:  ENTER FIRST NAME");
	        	customer.setFirstName(myScanner.UserInput_LongString());
	        	MainDriver.SystemLog.info("SYSTEM:  ENTER EMAIL ADDRESS");
	            customer.setEmailAddress(myScanner.UserInput_LongString());
	        	try 
	        	{
	        		MainDriver.SystemLog.info("SYSTEM:  ENTER CONTACT NUMBER (10 numbers 0-9)");
	    			customer.setContactNumber(Long.parseLong(myScanner.UserInput_LongString()));
	    			MainDriver.SystemLog.info("SYSTEM:  ENTER DATE OF BIRTH(DOB) IN (dd-MM-yyyy format only)");
	    			String dob = myScanner.UserInput_String();
	    			customer.setDob(customerServiceImpl.isValidDate(dob));
	    			MainDriver.SystemLog.info("SYSTEM:   ENTER STREET ADDRESS");
	    	        customer.setStreet(myScanner.UserInput_LongString());
	    	        MainDriver.SystemLog.info("SYSTEM:   ENTER CITY");
	    	        customer.setCity(myScanner.UserInput_LongString());
	    	        MainDriver.SystemLog.info("SYSTEM:   ENTER STATE (Two letter format only. eg: 'NY' (for New York state) ");
	    	        customer.setState(myScanner.UserInput_String().toUpperCase());
	    			
	    			customer = custService.createCustomer(customer);
	    			if(customer.getCustomerID() != null) {
	    				MainDriver.SystemLog.info("\nSYSTEM:  CUSTOMER: " + customer.getFirstName() + " " + customer.getLastName() + " HAS REGISTERED WITH BANK OF FRIENDSHIP");
	    				CreateLoginProfile.loginCustomerID = customer.getCustomerID();
	    				
	    				MainDriver.SystemLog.info("\nSYSTEM:  PLEASE MAKE A NOTE OF YOUR CUSOTMER ID: " + customer.getCustomerID());
	    				System.out.println();
	    				MainDriver.SystemLog.info(customer);
	    			} 
	    			CreateLoginProfile.CreateNewLoginProfile();
	        	}
	        	catch (NumberFormatException e)
	        	{
	        		MainDriver.SystemLog.info("SYSTEM: CONACT NUMBER SHOULD BE NUMBERS ONLY (PLEASE RE-ENTER INFORMATION)");
	        		MainDriver.SystemLog.error("SYSTEM: CONACT NUMBER SHOULD BE NUMBERS ONLY (PLEASE RE-ENTER INFORMATION)");
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
