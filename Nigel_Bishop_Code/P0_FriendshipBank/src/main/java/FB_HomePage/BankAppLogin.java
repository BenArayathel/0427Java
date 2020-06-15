package FB_HomePage;

public class BankAppLogin 
{
	public static void LoginWithUserProfile()
    {
//        var bankingHome = new BankingAssets(new ACCOUNTS_Checking(), new ACCOUNTS_Saving());
//		System.out.println();
//		System.out.println("ENTER - UserName: ");
//        string userName = BankingAssets_AppHome.UserInput_String();
//        System.out.println("ENTER - Password: ");
//        string userPassword = USERACCESS.MaskPassword();
//        System.out.println();
//        string userNameFromDatabase = "";
//        string passwordFromDatabase = "";
//        string passwordHash = USERACCESS.PasswordHash(userPassword);
//
//        DatabaseAccess db = new DatabaseAccess();
//        List<USERACCESS> people = db.GetUserAccessByUNandPass(userName, passwordHash);
//
//        foreach (var n in people)
//        {
//            userNameFromDatabase = n.UserName;
//            passwordFromDatabase = n.UserPassword;
//            CustomerID_USERACCESS = n.CustomerID;
//        }
//        if (numberOfLoginAttempts == 2)
//        {
//            FailLogin();
//        }
//        while (numberOfLoginAttempts != 2)
//        {
//            if (userName == userNameFromDatabase && USERACCESS.PasswordHash(userPassword) == passwordFromDatabase)
//            {
//                bankingHome.Welcome();
//                break;
//            }
//            else
//            {
//                Console.WriteLine("Invalid UserName or Password");
//                Console.WriteLine();
//                numberOfLoginAttempts++;
//                LoginWithUserProfile();
//            }   
//        }
    }
	
    public static void FailLogin()
    {
    	System.out.println();
    	System.out.println("******************");
    	System.out.println("YOU HAVE EXCEEDED THE NUMBER OF LOGIN ATTEMPTS");
    	System.out.println("BANK APPLICATION WILL NOW TERMINATE");
    	System.out.println();
        //BankingAssets_AppHome.BankWelcomePage();
    	System.out.println("THANK YOU FOR USING BANKIGN APPLICATION....");
    	System.out.println();
    }

}
