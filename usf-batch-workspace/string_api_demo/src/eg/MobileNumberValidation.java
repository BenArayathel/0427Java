package eg;

public class MobileNumberValidation {

	public static void main(String[] args) {
		String s="+91-2345678909";
		if(s.matches("\\+91-[0-9]{10}")) {
			System.out.println("Valid number");
		}else {
			System.out.println("Invalid Number");
		}

	}

}
/*
 * Validate SSN
 * Validate DL 
 */
