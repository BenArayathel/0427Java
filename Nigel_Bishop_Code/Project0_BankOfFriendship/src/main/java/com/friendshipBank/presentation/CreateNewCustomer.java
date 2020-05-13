package com.friendshipBank.presentation;

import com.friendshipBank.model.customer;
import com.friendshipBank.service.customerService;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.myScanner;
import com.friendshipBank.Main.mainDriver;
import com.friendshipBank.exception.BusinessException;


public class CreateNewCustomer 
{
    public static void CreateNewBankCustomer()
    {
    	customerService custService = new customerServiceImpl();
    	customer customer = new customer();
    	
    	int userChoice = 0;
    	
    	do {
    		
        	System.out.println();
        	mainDriver.SystemLog.info("****************************************************");
        	mainDriver.SystemLog.info("  WELCOME TO BANK OF FRIENDSHIP NEW USER CREATION   ");
        	mainDriver.SystemLog.info("****************************************************");
        	mainDriver.SystemLog.info("SELECT: (1) CREATE NEW CUSTOMER PROFILE");
        	mainDriver.SystemLog.info("SELECT: (2) EXIT THE APPLICATION");
        	mainDriver.SystemLog.info("SELECT: (3) BACK TO HOMEPAGE");
    		

				userChoice = myScanner.UserInput_Int();

        	switch(userChoice) {
			case 1:
				mainDriver.SystemLog.info("SYSTEM:  PLEASE ENTER VALID CUSTOMER INFORMATION (NEW CUSTOMER PROFILE)");
				System.out.println();
				mainDriver.SystemLog.info("SYSTEM:  ENTER LAST NAME");
	        	customer.setLastName(myScanner.UserInput_LongString());
	        	mainDriver.SystemLog.info("SYSTEM:  ENTER FIRST NAME");
	        	customer.setFirstName(myScanner.UserInput_LongString());
	        	mainDriver.SystemLog.info("SYSTEM:  ENTER EMAIL ADDRESS");
	            customer.setEmailAddress(myScanner.UserInput_LongString());
	        	try 
	        	{
	        		mainDriver.SystemLog.info("SYSTEM:  ENTER CONTACT NUMBER (10 numbers 0-9)");
	    			customer.setContactNumber(Long.parseLong(myScanner.UserInput_LongString()));
	    			mainDriver.SystemLog.info("SYSTEM:  ENTER DATE OF BIRTH(DOB) IN (dd-MM-yyyy format only)");
	    			String dob = myScanner.UserInput_String();
	    			customer.setDob(customerServiceImpl.isValidDate(dob));
	    			mainDriver.SystemLog.info("SYSTEM:   ENTER STREET ADDRESS");
	    	        customer.setStreet(myScanner.UserInput_LongString());
	    	        mainDriver.SystemLog.info("SYSTEM:   ENTER CITY");
	    	        customer.setCity(myScanner.UserInput_LongString());
	    	        mainDriver.SystemLog.info("SYSTEM:   ENTER STATE (Two letter format only. eg: 'NY' (for New York state) ");
	    	        customer.setState(myScanner.UserInput_String().toUpperCase());
	    			
	    			customer = custService.createCustomer(customer);
	    			if(customer.getCustomerID() != null) {
	    				System.out.println();
	    				mainDriver.SystemLog.info("SYSTEM:  CUSTOMER: " + customer.getFirstName() + " " + customer.getLastName() + " HAS REGISTERED WITH BANK OF FRIENDSHIP");
	    				CreateLoginProfile.loginCustomerID = customer.getCustomerID();
	    				
	    				mainDriver.SystemLog.info("Please make a note for your CUSOTMER ID: " + customer.getCustomerID());
	    				System.out.println();
	    				mainDriver.SystemLog.info(customer);
	    			} 
	    			CreateLoginProfile.CreateNewLoginProfile();
	        	}
	        	catch (NumberFormatException e)
	        	{
	        		mainDriver.SystemLog.info("SYSTEM: CONACT NUMBER SHOULD BE NUMBERS ONLY (PLEASE RE-ENTER INFORMATION)");
	        		mainDriver.SystemLog.error("SYSTEM: CONACT NUMBER SHOULD BE NUMBERS ONLY (PLEASE RE-ENTER INFORMATION)");
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
