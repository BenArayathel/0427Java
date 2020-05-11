//package bankapp;
//
//import java.util.Scanner;
//import java.util.Date;
//import java.util.List;
//
//public class NewCustomer 
//{
//
//	public static void newCustomer() 
//	{
//		//apply for a new bank account with a starting balance
//		Scanner scan = new Scanner(System.in);
//		NewCustomerTemplate newuser = new NewCustomerTemplate();
//		System.out.println("Welcome New User!");
//		System.out.println("Please input your information below to be approved for an account!");
//		try 
//		{
//			System.out.println("Please enter your Username");
//			newuser.setUn(scan.nextLine());
////----------------------------------------------------------------------------------
//			System.out.println("Please enter your Password");
//			newuser.setPw(scan.nextLine());
////----------------------------------------------------------------------------------
//			System.out.println("Please enter your First Name");
//			newuser.setFn(scan.nextLine());
////----------------------------------------------------------------------------------
//			System.out.println("Please enter your Last Name");
//			newuser.setLn(scan.nextLine());
////----------------------------------------------------------------------------------
//			System.out.println("Please enter your Date of birth in MM.dd.yyyy format");
////			Date dob = scan.nextLine();
////			newuser.setDob(TraineeServiceImpl.isValidDate(dob));
////----------------------------------------------------------------------------------
//			System.out.println("Please enter your Phone Number in 123-456-7890 format");
//			newuser.setPn(scan.next("[0-9]{3}-[0-9]{3}-[0-9]{4}"));
////----------------------------------------------------------------------------------
//			System.out.println("Please enter your SSN in 123-45-6789 format");
//			newuser.setSsn(scan.next("[0-9]{3}-[0-9]{2}-[0-9]{4}"));
////----------------------------------------------------------------------------------
//			System.out.println("Please enter your Credit Score");
//			newuser.setCs(Integer.parseInt(scan.nextLine()));
////----------------------------------------------------------------------------------			
//			System.out.println("Please enter your Starting Balance");
//			newuser.setSb(Double.parseDouble(scan.nextLine()));
////----------------------------------------------------------------------------------			
//		}catch (NumberFormatException e) 
//		{
//			
//		}
//		System.out.println("Thank you, your information has been added!");
//}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		NewUser k = new NewUser(a,b,c,d,e,f,g,h);
//		
//		System.out.println("Thank you, your information has been added!");
//	}
//	public NewUser(String a, String b, String c, String d, int e, int f, String g, String h)
//	{
//		
//	}
//}
