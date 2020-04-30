package eg_buffer_builder;

public class Palindrome {

	public static void main(String[] args) {
		int x=1000;
		//String s=x+"";
//		StringBuilder sb=new StringBuilder(s);
//		sb.reverse();
//		String s1=sb.toString();
//		if(s.equals(s1)) {
//			System.out.println("Its Palindrome");
//		}else {
//			System.out.println("Not a Palindrome");
//		}
		
		
		while (x <= 9999) {
			String s=x+"";
			
			if(new StringBuilder(s).reverse().toString().equals(s)) {
				System.out.println(s + " : Its Palindrome");
				}else {
					//System.out.println(s + " : Not a Palindrome");
				}
			
			 x++;
		}
		
		// print all palindromes beteween 1000 to 9999
//		1001 : Its Palindrome
//		1111 : Its Palindrome
//		1221 : Its Palindrome
//		1331 : Its Palindrome
//		1441 : Its Palindrome
//		1551 : Its Palindrome
		
		// so on and so fourth ...
		
//		8998 : Its Palindrome
//		9009 : Its Palindrome
//		9119 : Its Palindrome
//		9229 : Its Palindrome
//		9339 : Its Palindrome
//		9449 : Its Palindrome
//		9559 : Its Palindrome
//		9669 : Its Palindrome
//		9779 : Its Palindrome
//		9889 : Its Palindrome
//		9999 : Its Palindrome
		
		

	}

}
