package FB_Customer;

import FB_Resource.FB_SSN_Validation;
import FB_Resource.FB_Scanner;

public class CreateNewCustomer 
{
    public static void CreateNewBankCustomer()
    {
        FB_SSN_Validation newssn = new FB_SSN_Validation();
        CreateNewCustomer customer = new CreateNewCustomer();
        
    	System.out.println();
    	System.out.println("ENTER - Last Name: ");
        String lname = FB_Scanner.UserInput_LongString();
        System.out.println("ENTER - First Name: ");
        String fname = FB_Scanner.UserInput_LongString();
        System.out.println("ENTER - Email Address: ");
        String email = FB_Scanner.UserInput_LongString();
//        newssn.Email_Validation();
//        String email = newssn.getEmail();
        System.out.println("ENTER - Social Security Number:  eg (111-11-1111) ");
//        String ssn = FB_SSN_Validation.SSN_Validation();

        newssn.SSN_Validation();
        String ssn = newssn.getSsn();
        System.out.println("ENTER - Street Address: ");
        String street = FB_Scanner.UserInput_LongString();
        System.out.println("ENTER - City: ");
        String city = FB_Scanner.UserInput_LongString();
        System.out.println("ENTER - State: ");
        String state = FB_Scanner.UserInput_LongString();
        System.out.println("ENTER - ZIP Code: ");
        int zip = FB_Scanner.UserInput_Int();

        
        
//        DatabaseAccess.InsertCustomer(lname, fname, street, city, state, zip);
//        DatabaseAccess db = new DatabaseAccess();
//        List<CUSTOMER> people = db.GetCustomerLastAndFirst(lname, fname);
//
//        foreach (var n in people)
//        {
//            Console.WriteLine("");
//            Console.WriteLine("CUSTOMER ID:   " + n.CustomerID);
//            BankingAssets_AppHome.CustomerID = n.CustomerID;
//            Console.WriteLine("LAST NAME:     " + n.LastName);
//            Console.WriteLine("FIRST NAME:    " + n.FirstName);
//            Console.WriteLine("STREET:        " + n.Street);
//            Console.WriteLine("CITY:          " + n.City);
//            Console.WriteLine("STATE:         " + n.State);
//            Console.WriteLine("ZIP:           " + n.ZIP);
//            Console.WriteLine("CREATION DATE: " + n.DateModified);
//        }
        System.out.println("NEW USER PROFILE SUCCESFULLY CREATED");
        
        System.out.println(fname);   
        System.out.println(lname);
        System.out.println(email);
        System.out.println(ssn);
        System.out.println(street);
        System.out.println(city);
        System.out.println(state);
        System.out.println(zip);
        System.out.println();
        
    }
}
