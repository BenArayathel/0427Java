package Validations;

public class Driver_License_Validation {

	public static void main(String[] args) {
		
		String dl = "A-123-123-123-123";
		
		//([A-Z]|[a-z]){1} - Matches either a single Uppercase letter or Lowercase letter
		//[0-9]{3}  - Match a digits exactly three times
		// -        - Match literally with "-"
		
		if(dl.matches("([A-Z]|[a-z]){1}-[0-9]{3}-[0-9]{3}-[0-9]{3}-[0-9]{3}")) 
		{
			System.out.println("Your Maryland DL: VALID");
		}
		else 
		{
			System.out.println("Your Maryland DL: INVALID");
		}

	}

}
