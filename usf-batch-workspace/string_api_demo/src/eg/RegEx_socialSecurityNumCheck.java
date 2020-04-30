package eg;

public class RegEx_socialSecurityNumCheck {

	public static void main(String[] args) {
		/*
		 * Reg-Ex - Regular Expression
		 * PMA - Pattern Matching Algorithm
		 * [] - expression
		 * {} - length
		 * ^ - not
		 * ? . * () +
		 * [a-z]{5} - any lowercase letter of length 5
		 * [0-9]{1,} - at least 1 digit and max can be any
		 * [a-zA-Z0-9]{10} - and alphanumeric word of length 10
		 * [^a-zA-Z0-9] - apart from alphanumeric
		 */
		
		
		String s="123-12-1234";
		
		
		if(s.matches("[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}")) {
			System.out.println("\nValid Soc. Sec. !!!!");
		}else {
			System.out.println("Invalid Info");
		}
	}

}
