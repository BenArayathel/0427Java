package eg;

public class MobileNumberValidation {
	
	public static void main(String[] args) {
		String s = "+91-1234567890";
		if (s.matches("\\+91-[0-9]{10}")) {// remember + and other meta characters must be escaped (escape sequence: \\)
			System.out.println("Valid Number");
		} else {
			System.out.println("Invalid Number");
		}
	}

}
/*
 * Homework
 * 		Validate SSN
 * 		Validate DL
 * (See Homework regex.IdValidator class)
*/
