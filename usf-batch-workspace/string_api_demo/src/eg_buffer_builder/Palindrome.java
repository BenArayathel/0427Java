package eg_buffer_builder;

public class Palindrome {

	public static void main(String[] args) {
		int x=1212;
		String s=x+"";
//		StringBuilder sb=new StringBuilder(s);
//		sb.reverse();
//		String s1=sb.toString();
//		if(s.equals(s1)) {
//			System.out.println("Its Palindrome");
//		}else {
//			System.out.println("Not a Palindrome");
//		}
		
		if(new StringBuilder(s).reverse().toString().equals(s)) {
			System.out.println("Its Palindrome");
			}else {
				System.out.println("Not a Palindrome");
			}

	}

}
// print all palindromes beteween 1000 to 9999