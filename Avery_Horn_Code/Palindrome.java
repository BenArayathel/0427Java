package stuff;

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

	

// print all palindromes beteween 1000 to 9999
		for (int i = 1000; i<10000;i++)
		{
			String p=i+"";
			if(new StringBuilder(p).reverse().toString().equals(p))
			{
				System.out.println(p);
			}
		}
	}
}
