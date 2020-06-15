package FB_HomePage;

import FB_Customer.CreateLoginProfile;
import FB_Customer.CreateNewCustomer;
import FB_Resource.FB_Scanner;

public class Welcome_Page 
{

	
    public static void BankWelcomePage()
    {
    	System.out.println();
    	System.out.println("************************************************");
    	System.out.println("*    WELCOME TO FRIENDSHIP BANK APPLICATION    *");
    	System.out.println("************************************************");
    	System.out.println();
    	System.out.println("SELECT: (1) SIGN INTO BANKING APPLICATION, (2) NEW ACCOUNTS CREATION");
    	System.out.println("SELECT: (3) EXIT APPLICATION");
        switch (FB_Scanner.UserInput_Int())
        {
            case 1:
            	System.out.println("Logging user in");
    //            USERACCESS.LoginWithUserProfile();
                break;
            case 2:
            	System.out.println("Creating new account");
       //         NewCustomerHome();
            	CreateNewCustomer.CreateNewBankCustomer();
           // 	CreateLoginProfile.CreateUserLoginProfile();
                break;
            default:
            	System.out.println();
            	System.out.println("THANK YOU FOR USING MY BANKING APPLICATION");
            	System.out.println();
                break;
        }
    }
	
	
	
}
