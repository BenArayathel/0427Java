package eg_buffer_builder;

public class Palindrome {

	public static void main(String[] args) {
		//String s = "madam";
		int x = 1212;
		String s=x+"";// shortcut for changing anything to a string
		// We'll use StringBuilder because we want to change the string in place
		// It's not really necessary for this example, but if we had to do multiple changes to the string
		// (instead of just reversing it), this would save memory
//		StringBuilder sb = new StringBuilder(s);
//		String s1 = sb.reverse().toString();
//		if (s.equals(s1)) {
//			System.out.println(s+" is a pallindrome");
//		} else {
//			System.out.println(s+" is not a pallindrome");
//		}
		// Can simplify to do all at once
		if (new StringBuilder(s).reverse().toString().equals(s)) {
			System.out.println(s+" is a pallindrome");
		} else {
			System.out.println(s+" is not a pallindrome");
		}
	}
}
/*
 * Homework
 * 		print all pallindromes for integers between 1000 to 9999 (easy peasy)
 */
