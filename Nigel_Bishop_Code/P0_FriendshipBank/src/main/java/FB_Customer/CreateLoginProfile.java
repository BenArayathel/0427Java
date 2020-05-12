package FB_Customer;

import FB_Resource.FB_Scanner;

public class CreateLoginProfile 
{
	
    // CREATE LOGIN PROFILE FOR BANKING APPLICATION LOGIN
    public static void CreateUserLoginProfile()
    {

    	System.out.println();
    	System.out.println();
    	System.out.println("CREATING NEW LOGIN PROFILE ACCOUNT");
    	System.out.println();
    	System.out.print("ENTER - UserName: ");
        String userName = FB_Scanner.UserInput_String();
        System.out.print("ENTER - Password: ");
        String password1 = FB_Scanner.UserInput_String();
        System.out.print("ENTER - Password: ");
        String password2 = FB_Scanner.UserInput_String();
        System.out.println();


        if (password1.equals(password2))	
        {
        	System.out.println("*********    PASSWORD MATCH    ************");
        }
        else
        {
        	System.out.println("*********    PASSWORD MISMATCH    ************");
        	System.out.println("***    PLEASE RE-ENTER INFORMATION     ***");
        	System.out.println();
        	CreateUserLoginProfile();
        }

        System.out.println("LOGIN PROFILE SUCCESSFULLY CREATED");
        System.out.println();
    }
	
	

}
