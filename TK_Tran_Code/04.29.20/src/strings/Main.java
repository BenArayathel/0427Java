package strings;

public class Main {
    public static void main(String[] args) {

        String s = "This is a String Literal";  // Creates String in String pool (in heap)
        String s2 = "This is a String Literal";

        String s3 = new String("This is a String Literal"); // new assigns a new memory location (outside String pool, but still in heap).
        String s4 = new String("This is a String Literal"); // Not as efficient for memory-management!

        // == checks memory location
        System.out.println(s == s2);    // true
        System.out.println(s == s3);    // false, both in heap but s is in the pool and s3 isn't.
        System.out.println(s3 == s4);   // false, although created same way using new, still different memory locations in heap.

        // .equals() checks content (.equalsIgnoreCase() ignores case)
        System.out.println(s3.equals(s4));  // true

        // Returns String from heap back into String pool (that's still also in heap).
        s3 = s3.intern();
        System.out.println(s == s3);	// now true
        
        String A = "Java";
        String B = A;

        A = A + " and more Java";   // A now points to "Java and more Java";
        System.out.println(A);
        System.out.println(B);

        System.out.println(A.toUpperCase());
        System.out.println(A);



		// StringBuilder
		String test = "Rick";
		StringBuilder sb = new StringBuilder(test);	// Converts String to StringBuilder
		StringBuilder sb2 = sb;

		sb.append(" and Morty");
		System.out.println("sb : " + sb);
		System.out.println("sb2 : " + sb2);
        String stringy = sb.toString();	// Converts StringBuilder back to String
        System.out.println(stringy.getClass());

		// StringBuffer
		String moreTest = "Morty";
		StringBuffer sbuff = new StringBuffer(moreTest);	// Converts String to StringBuffer
		sbuff.append(" and Summer");
		System.out.println(sbuff);
        String stringy2 = sbuff.toString();	// Converts StringBuffer back to String
        System.out.println(stringy2.getClass());



        // Checks memory addresses
        System.out.println(System.identityHashCode(s));     // 1554547125
        System.out.println(System.identityHashCode(s2));    // 1554547125
        System.out.println(System.identityHashCode(s4));    // 617901222
        
		// Note: no DIRECT conversion between StringBuilder and StringBuffer; must go through intermediate.
        // Note: all 3 classes are final and not related to each other.
        


		// Other String methods
		System.out.println(s2.substring(2, 4));	// Grabs a substring.
		System.out.println(s2.length());	// Returns length of String.
		System.out.println(s2.charAt(0));	// Returns character at index.

		// Can iterate with String methods
		for (int i = 0; i < s2.length(); i++) {
			System.out.println(s2.charAt(i));
		}
		System.out.println(s2.trim());	// Removes leading and trailing whitespace.

		// Splitting a String Literal
		String t = "Hello how are you doing today?";
		String ar[] = t.split(" ");
		for (int i = 0; i < ar.length; i++) {
			System.out.println(ar[i].toUpperCase());
		}
    }
}