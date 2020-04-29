package eg;

public class RegEx {

	public static void main(String[] args) {
		/*
		 * Reg-Ex stands for Regular Expression
		 * PMA - Pattern Matching Algorithm
		 * [] - expression
		 * {} - length
		 * ^ - not
		 * ? . * () etc. // explore on your own
		 * [a-z]{5} // any lowercase letters of length 5
		 * [0-9]{1,} // at least 1 digit, unlimited max
		 * [a-zA-z0-9]{10} // any alphanumeric word of length 10
		 * [^a-zA-Z0-9] // any non-alphanumeric character (e.g. space, period, comma, etc.)
		 * 
		 * Learn more about RegEx; it's always useful!
		 */
		String s = "123123$asd$asdf dfja;sk asd;lkf AA %%%%%()* ^^^^^^";
		System.out.println(s.replaceAll("[a-zA-z]", ""));
		System.out.println(s.replaceAll("[^a-zA-Z]", ""));
		System.out.println(s.replaceAll("[^a-zA-Z]", "").length());
		System.out.println(s.replaceAll("[^a-zA-Z0-9]", ""));
		System.out.println(s.replaceAll("[^a-zA-Z0-9]", "").length());
		System.out.println(s.replaceAll("[a-zA-Z0-9]", ""));
		System.out.println(s.replaceAll("[a-zA-Z]", "").length());
		
		//first 5 letter should be uppercase, followed by 4 digits and followed by uppercase letter
		String p = "ABXYY1121O";
		if (p.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
			System.out.println("Valid Information");
		} else {
			System.out.println("Invalid Information");
		}
		
		
		
	}

}
