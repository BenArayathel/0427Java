package Validations;

public class SSN_Validation {

	public static void main(String[] args) {
		
		String ssn = "123-12-1234";
		
		//[0-9]{3}  - Match a digits exactly three times
		// -        - Match literally with "-"
		if(ssn.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) 
		{
			System.out.println("SSN: Valid");
		}
		else 
		{
			System.out.println("SSN: Invalid");
		}

	}

}
