package com.friendshipBank.presentation;

import com.friendshipBank.model.customer;
import com.friendshipBank.service.customerService;
import com.friendshipBank.service.impl.customerServiceImpl;
import com.friendshipBank.service.impl.myScanner;
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
        	System.out.println("****************************************************");
        	System.out.println("  WELCOME TO BANK OF FRIENDSHIP NEW USER CREATION   ");
        	System.out.println("****************************************************");
//        	System.out.println();
        	System.out.println("SELECT: (1) CREATE NEW CUSTOMER PROFILE");
//        	System.out.println("SELECT: (2) CREATE NEW LOGIN PROFILE     (REQUIRE CUSTOMER ID)");
//        	System.out.println("SELECT: (3) REQUEST NEW CHECKING ACCOUNT (REQUIRE CUSTOMER ID)");
//        	System.out.println("SELECT: (4) REQUEST NEW SAVING ACCOUNT   (REQUIRE CUSTOMER ID)");
        	System.out.println("SELECT: (2) EXIT THE APPLICATION");
        	System.out.println("SELECT: (3) BACK TO HOMEPAGE");
    		

				userChoice = myScanner.UserInput_Int();

        	switch(userChoice) {
			case 1:
				System.out.println("PLEASE ENTER VALID CUSTOMER INFORMATION (NEW CUSTOMER PROFILE)");
				System.out.println();
	        	System.out.println("ENTER - Last Name: ");
	        	customer.setLastName(myScanner.UserInput_LongString());
	        	System.out.println("ENTER - First Name: ");
	        	customer.setFirstName(myScanner.UserInput_LongString());
	            System.out.println("ENTER - Email Address: ");
	            customer.setEmailAddress(myScanner.UserInput_LongString());
	        	try 
	        	{
	    			System.out.println("ENTER - Contact Number: (10 numbers 0-9)");
	    			customer.setContactNumber(Long.parseLong(myScanner.UserInput_LongString()));
	    			System.out.println("ENTER - Date of Birth(DOB) in dd-MM-yyyy format only");
	    			String dob = myScanner.UserInput_String();
	    			customer.setDob(customerServiceImpl.isValidDate(dob));
	    	        System.out.println("ENTER - Street Address: ");
	    	        customer.setStreet(myScanner.UserInput_LongString());
	    	        System.out.println("ENTER - City: ");
	    	        customer.setCity(myScanner.UserInput_LongString());
	    	        System.out.println("ENTER - State as two letter format. Example: NY (for New York state) ");
	    	        customer.setState(myScanner.UserInput_String().toUpperCase());
	    			
	    			customer = custService.createCustomer(customer);
	    			if(customer.getCustomerID() != null) {
	    				System.out.println();
	    				System.out.println("CUSTOMER: " + customer.getFirstName() + " " + customer.getLastName() + " has been registered with BANK OF FRIENDSHIP");
	    				CreateLoginProfile.loginCustomerID = customer.getCustomerID();
	    				
	    				System.out.println("Please make a note for your CUSOTMER ID: " + customer.getCustomerID());
	    				System.out.println();
	    				System.out.println(customer);
	    			} 
	    			CreateLoginProfile.CreateNewLoginProfile();
	        	}
	        	catch (NumberFormatException e)
	        	{
	    			System.out.println("Contact Number should numbers only kindly re-enter");
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
