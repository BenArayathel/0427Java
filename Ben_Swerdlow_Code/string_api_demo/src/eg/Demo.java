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
	}

}
