package com.friendshipBank.presentation;

import com.friendshipBank.service.impl.myScanner;

public class welcomePage 
{
    public static void BankWelcomePage()
    {
    	
    	int userChoice = 0;
    	
    	do {
        	System.out.println();
        	System.out.println("************************************************");
        	System.out.println("*        WELCOME TO BANK OF FRIENDSHIP         *");
        	System.out.println("************************************************");
        	System.out.println();
        	System.out.println("SELECT: (1) LOGIN TO BANKING APPLICATION");
        	System.out.println("SELECT: (2) NEW USER");
        	System.out.println("SELECT: (3) EXIT APPLICATION");
        	
        	userChoice = myScanner.UserInput_Int();
        	
            switch (userChoice)
            {
                case 1:
//                	System.out.println("Logging user in");
                	BankApplicationLogin.LoginToBankApplication();
          //  		fBank_log.info("THIS IS AN INFO UPDATE!!!!");
        //            USERACCESS.LoginWithUserProfile();
                    break;
                case 2:

           //         NewCustomerHome();
                	CreateNewCustomer.CreateNewBankCustomer();
               // 	CreateLoginProfile.CreateUserLoginProfile();
                    break;
                case 3:
                	System.out.println();
                	System.out.println("THANK YOU FOR USING MY BANKING APPLICATION");
                	System.out.println();
    				System.exit(0);
                    break;
            }
    	

        }while(userChoice !=3);
    }

}
