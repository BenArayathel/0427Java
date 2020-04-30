package com.example.Strings;

public class StringStuff {
	
	public static void main(String[] args) {
		/*
		 * what is a string?
		 * 		String is an object that contains an array of characters. It allows you to create
		 * 		and manipulate strings
		 * 
		 * Immutable: String cannot be changed
		 * Final: the class cannot be extended
		 * 
		 * String objects are stored in the heap but String literals are stored in the String pool, which
		 * 	itself is within the Heap memory
		 */
		
		String s = "This is a String literal"; // The String object will first reference the String pool
		String s2 = "This is a String literal"; // and point to it if it already exists.
		
		String s3 = new String("This is a String literal"); // the new keyword will ALWAYS create a new object in Heap memory
		String s4 = new String("This is a String literal");
		
		s4.intern(); // moves the string from the heap to the String pool (Stack)
		
		System.out.println(s == s2);
		System.out.println(s == s3); // this checks both contents and memory address
		System.out.println(s3 == s4);
		
		System.out.println(s3.equals(s4));
		
		// why immutable?
		
		String A = "Java";
		String B = "Java"; // both variables point to the same string literal
		
		A = A + " and more Java"; // "A" now points to "Java and more Java" so now A is pointing to a whole new String literal
		System.out.println(A);
		System.out.println(B);
		
		System.out.println(A.toUpperCase()); // everytime a string is modified it will create a whole new memory in the String pool
		System.out.println(A);
		
		//StringBuilder - mutable, more efficient than StringBuffer
		//StringBuffer - mutable, thread safe/synchronized
		
		String test = "Rick";
		StringBuilder sb = new StringBuilder(test); // Converted String to StringBuilder
		StringBuilder sb2 = sb;
		
		sb.append(" and Morty");
		System.out.println("Sb: " + sb);
		System.out.println("Sb2: " + sb2);
		String stringy = sb.toString(); // Changes StringBuilder back to String
		
		String moreTest = "Morty";
		StringBuffer sbuf = new StringBuffer(moreTest); // Covert String to StringBuffer
		sbuf.append(" and Summer");
		System.out.println(sbuf);
		String stringy2 = sbuf.toString();
		
	}

}
