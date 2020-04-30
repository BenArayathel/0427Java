package regex;

public class SSNandDLvalidation {
	
	//RegEx- Check validation for SSN and DL
	public static void main(String[] args) {
		
		String ssn="123-45-6789";
		String dl = "S123-456-78-123-0";
		
		if(ssn.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
			System.out.println("Valid SSN number");
		}else {
			System.out.println("Invalid SSN number");
		}
		
		if(dl.matches("[A-Z]{1}[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{3}-[0-9]{1}")) {
			System.out.println("Valid driver license number");
		}else {
			System.out.println("Invalid driver license number");
		}
	}
	

}
