package homeworkOne;

public class Palindrome {

	public static void main(String[] args) {
		for(int i = 1000; i<=9999; i++) {
			Integer a = i;
			String s = a.toString();
			StringBuilder b = new StringBuilder(s);
			b.reverse();
			String x = b.toString();
			if(s.equals(x)) {
				System.out.println(s);
				System.out.println("It's a Palindrome");
			}
		}
	}
}
