package eg;

public class Demo {

	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "hello";
		String s3 = new String("hello");
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
		System.out.println(System.identityHashCode(s3));
		s3 = s3.intern();
		System.out.println(System.identityHashCode(s3));
		
		System.out.println(s2.equalsIgnoreCase("HELLO"));
		
		System.out.println(s2.substring(1));
		System.out.println(s2.substring(2, 4));
		
		System.out.println(s2.length());
		
		//System.out.println(s2.charAt(4));
		//e.g.
		for (int i=0; i<s2.length(); i++) {
			System.out.println(s2.charAt(i));
		}
		
		String spaces = "           a;wioefjq; ekljrqko;werjf         ";
		System.out.println(spaces);
		System.out.println(spaces.trim());
		
		String sentence = "hello hi how are you doing today?";
		System.out.println(sentence);
		String ar[] = sentence.split(" ");
		for (String a : ar) {
			System.out.println(a.toUpperCase());
		}
	}
}
