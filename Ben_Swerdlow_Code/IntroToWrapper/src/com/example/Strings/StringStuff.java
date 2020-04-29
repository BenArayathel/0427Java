package com.example.Strings;

public class StringStuff {
	
	public static void main(String[] args) {
		/*
		 * String is an object that contains an array of characters. It allows you to create 
		 * and manipulate strings.
		 * 
		 * String is 
		 * Immutable - strings cannot be changed
		 * Final - the class cannot be extended
		 * 
		 * String objects are stored in the heap, but String Literals are stored in the String pool, which
		 * itself is within the Heap memory.
		 */
		
		String s = "This is a String Literal"; // The string object will first reference the String pool
		String s2 = "This is a String Literal"; // and point to it if it already exists
		// s2 doesn't make another string object with the same contents, instead points to the same reference as s
		
		String s3 = new String("This is a String Literal");
		// The new keyword syntax forces Java to create a new string object, not point s3 to the same reference as s and s2
		// This is a rare use
		String s4 = new String("This is a String Literal");
		
		s4.intern(); // Moves the String s4 from the Heap to the String pool
		
		// The == syntax checks if the objects point to the same memory address
		System.out.println(s == s2);//true
		System.out.println(s == s3);//false; the contents are the same, but the memory addresses are different
		System.out.println(s3 == s4);//false; same reasoning as above
		// To compare contents, use String.equals(String s); You will almost always use this syntax
		System.out.println(s.equals(s4));//true; they have the same contents, regardless of memory address
		
		// Why immutable?
		String A = "Java";
		String B = "Java";// String B = A;
		// Since A and B are pointing to the same memory in String Pool, modifying A would modify B (if String wasn't smart)
		
		A += " and more Java";// This syntax (and many others in String) gives the illusion of modification, but it is not
		System.out.println(A);// A now points to the new String literal "Java and more Java", rather than "Java"
		System.out.println(B);// B still points to the original "Java"
		
		System.out.println(A.toUpperCase());// String is smart and created "JAVA AND MORE JAVA" and returned that instead of modifying A
		System.out.println(A);// But A still points to just "Java and more Java"
		
		A = A.toUpperCase(); // This will point A to the "JAVA AND MORE JAVA" String literal
		// If the original contents of A ("Java and more Java") is not referenced anymore, garbage process will remove it
		
		// 1 | String s1 = "hey";
		// 2 | String s2 = "hey";
		// 3 | String s3 = new String("hey");
		// 4 | s1 = "hello";
		// 5 | s2 = "abc";
		// 6 | s3 = s3.intern();
		// 7 | s4 = new String("disappear");
		// 8 | s4.intern();
		//                     HEAP                                                                              STACK
		// =============================================================================================================================
		// |             common String Pool                 | (note: this is the heap, outside ||
		// |------------------------------------------------|  String pool)                    ||
		// | (1)"hey" is created                            | (3)"hey" is created outside      ||
		// | (1)s1, (2)s2 point to "hey"                    |     the String pool              ||
		// | (4)"hello" is created                          | (3)s3 points to "hey"            ||
		// | (4)s1 now points to "hello"                    | (6)"hey is removed and s3        ||
		// | (5)"abc" is created                            |     points to "hey" string       ||
		// | (5)s2 now points to "abc"                      |     already in the pool          ||
		// | (6)s3 now points to "hey"                      | (7)"disappear is created         ||
		// | (8)"disappear" is created in String pool;      |     outside the String pool      ||
		// |     however, nothing points to it, so the      | (7)s4 points to "disappear"      ||
		// |     garbage collector will delete it.          | (8)"disappear" is deleted to     ||
		// | (garbage collector runs in parallel with main) |     make it in the String pool   ||
		
		
		// StringBuilder - mutable, more efficient than StringBuffer, but not thread safe/synchronized
		// StringBuffer - mutable, thread safe/synchronized, but inefficient
		// String is also thread safe/synchronized, but is immutable
		// Both StringBuilder and StringBuffer
		// 		do not use the String pool, they're just in the heap
		// 		are final classes (just like String)
		
		//String test = "Rick";
		StringBuilder sb = new StringBuilder("Rick");// converted String to StringBuilder
		// the above line creates two objects within the line: "Rick" is made in the String pool and sb points to a copy of it outside it
		// after the line, the garbage collector will delete the "Rick" string since it's no longer used/needed
		StringBuilder sb2 = sb;// assigned sb2 to the same string "Rick"
		
		sb.append(" and Morty");// 
		System.out.println("sb : "+sb);
		System.out.println("sb2: "+sb2);
		
		String stringy = sb.toString();// Changes StringBuilder back to String
		
		String moreTest = "Morty";
		StringBuffer sbuf = new StringBuffer(moreTest);// Converts String to StringBufffer
		sbuf.append(" and Summer");
		
		System.out.println(sbuf);
		
		String stringy2 = sbuf.toString();
	}

}
