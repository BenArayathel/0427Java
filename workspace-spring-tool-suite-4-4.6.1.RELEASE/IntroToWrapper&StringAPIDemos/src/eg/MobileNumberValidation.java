package eg;

public class MobileNumberValidation {

	public static void main(String[] args) {
		String s = "+91-1234567890";
		if(s.matches("\\+91-[0-9]{10}")) {//use\\ in front of metacharacters like + for program to works
			System.out.println("Valid number");
		} else {
			System.out.println("Invalid Number");
		}
				
	}

}
 // Validate SSN with dashes, Divers liscense, explore other regular expression symbols assignment