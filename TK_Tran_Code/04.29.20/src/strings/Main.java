package strings;

public class Main {
    public static void main(String[] args) {

        // Illustrates how String-creation differs in memory.
        String s = "This is a String Literal";  // Creates String in String pool (in heap)
        String s2 = "This is a String Literal";

        String s3 = new String("This is a String Literal"); // new assigns a new memory location (outside String pool, but still in heap).
        String s4 = new String("This is a String Literal"); // Not as efficient for memory-management!

        // == compares MEMORY ADDRESS
        System.out.println(s == s2);    // true
        System.out.println(s == s3);    // false, both in heap but s is in the pool and s3 isn't.
        System.out.println(s3 == s4);   // false, still different memory locations in heap (new always allocates new memory address).

        // .equals() compares CONTENT (also .equalsIgnoreCase())
        System.out.println(s3.equals(s4));  // true

        // .intern() returns String from heap back into String pool (that's also in heap).
        s3 = s3.intern();
        System.out.println(s == s3);	// now true, they're in the same String pool
        


		// StringBuilder
		String test = "Rick";
		StringBuilder sb = new StringBuilder(test);	// Converts String to StringBuilder
		StringBuilder sb2 = sb; 	// Both points to same memory address
		sb.append(" and Morty");   		 // Changing sb will inherently change sb2 (bc sb2 holds sb's value in memory)
		System.out.println("sb: " + sb);   // sb : Rick and Morty
		System.out.println("sb2: " + sb2); // sb : Rick and Morty
        String stringy = sb.toString(); 	// Converts StringBuilder back to String
        System.out.println(stringy.getClass());	// java.lang.String



		// StringBuffer (thread safe/synchronized, but slower than StringBuilder bc it needs to wait for threads)
		String test2 = "Morty";
		StringBuffer sbuff = new StringBuffer(test2);	// Converts String to StringBuffer
		sbuff.append(" and Summer");
		System.out.println("sbuff: " + sbuff);		// Morty and Summer
        String stringy2 = sbuff.toString();			// Converts StringBuffer back to String
        System.out.println(stringy2.getClass());    // java.lang.String



        // Checks memory addresses
        System.out.println(System.identityHashCode(s));     // 1554547125
        System.out.println(System.identityHashCode(s2));    // 1554547125
        System.out.println(System.identityHashCode(s4));    // 617901222
		
		

		// Note: no DIRECT conversion between StringBuilder and StringBuffer; must go through intermediate channels.
        // Note: all 3 classes are final and not related to each other.
        


		// Other String methods
		System.out.println(s2.substring(2, 4));	// Grabs a substring from 2 to 4 (not including)
		System.out.println(s2.length());		// Returns length of String.
		System.out.println(s2.charAt(0));		// Returns character at index.

		// Printing each char on its own line
		for (int i = 0; i < s2.length(); i++) {
			System.out.println(s2.charAt(i));
		}
		System.out.println(s2.trim());	// Removes leading and trailing whitespace.

		// Splitting a String Literal
		String t = "hello how are you doing today?";
		String ar[] = t.split(" ");				// Splits String into String array, space delimited
		for (int i = 0; i < ar.length; i++) {	// Iterates through array and print each word on its own line, capitalizing every word (why not)
			System.out.println(ar[i].toUpperCase()); 
		}
    }
}