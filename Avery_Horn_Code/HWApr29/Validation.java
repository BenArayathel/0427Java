package stuff;

public class Validation {

		public static void main(String[] args) {
			String s="+91-2345678909";
			if(s.matches("\\+91-[0-9]{10}")) {
				System.out.println("Valid number");
			}else {
				System.out.println("Invalid Number");
			}

		

	/*
	 * Validate SSN
	 */
	String SS = "777-77-7777";
	if (SS.matches("[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}")) {
		System.out.println("Valid SSN");
	}
	else {
		System.out.println("Invalid SSN");
	}
	/*
	 * Validate DL 
	 */
	String dl = "7777b77";
			if(dl.matches("[0-9]{7}"))
			{
				System.out.println("Valid driver's license");
			}
			else
			{
				System.out.println("Invalid driver's license");
			}
		}
}
