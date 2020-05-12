package FB_Resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FB_SSN_Validation 
{
	
	private String ssn;
	private String email;
	
	public FB_SSN_Validation() {
		
	}
	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
//	public static String SSN_Validation() 
//	{
//		String ssn = FB_Scanner.UserInput_String();
//
//		String mySsn = null;
//		
//		if(ssn.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) 
//		{
//			mySsn = ssn;
//
//		}
//		else 
//		{
//			System.out.println("Invlaid Social Security Number Format: Please try again");
//			SSN_Validation();
//		}
//		return mySsn;
//	}
	



	public  void SSN_Validation() 
	{	
		String mySsn = FB_Scanner.UserInput_String();
		if(mySsn.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) 
		{
			this.ssn  = mySsn;
		}
		else 
		{
			System.out.println("Invlaid Social Security Number Format: Please try again");
			SSN_Validation();
		}	
	}
	
//	public  void Email_Validation() 
//	{	
//		String myEmail = FB_Scanner.UserInput_String();
//		
//		String email_pattern = "^[a-zA-Z0-9_#$%&’*+/=?^.-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//        Pattern pat = Pattern.compile(email_pattern);
//        Matcher mat = pat.matcher(myEmail);
//		
//		
//		"^[a-zA-Z0-9_#$%&’*+/=?^.-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
//		if(myEmail.matches("[@]{1}"))
//		if(Matcher mat = pat.matcher(myEmail))
//		{
//			this.email  = myEmail;
//		}
//		else 
//		{
//			System.out.println("Invlaid Email Address Format: Please try again");
//			Email_Validation();
//		}	
//	}
	


}
