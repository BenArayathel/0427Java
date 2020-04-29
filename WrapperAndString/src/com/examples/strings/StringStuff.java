package com.examples.strings;

public class StringStuff {
	
	public static void main(String[] args) {
		/*
		 * What is a STring?
		 * 	String is an object that contains an array of characters. It allows you to create
		 * 	and manipulate strings. 
		 * 
		 * Immutable: String can't be changed. 
		 * Final: The class cannot be extended.
		 * 
		 * String objects are stored in the heap but String Literals are stored in the String pool, which 
		 * 	itself is within the Heap memory. 
		 */
		
		String s = "This is a String Literal"; // The string object will first reference the Stirng pool
		String s2 = s;// and point to it if it already exists. same as String s2 = "This is a String Literal";
		
		String s3 = new String("This is a String Literal"); //the new keyword will ALWAYS create a new object in heap memory 
		String s4 = new String("This is a String Literal");
		
		s4.intern(); //Moves the string from the heap to the String pool (but will be GC because there's no reference)
		s4 = s4.intern(); //Moves to the String pool and s4 will point to the String pool
		
//		System.out.println(s == s2);
//		System.out.println(s == s3);
//		System.out.println(s3 == s4);
//		
//		System.out.println(s3.equals(s4));
//		
		//Why immutable? 
		
		String A = "Java";
		String B = A;
		
		A = A + " and more Java"; //A now points to "Java and more Java"
//		A = A.toUpperCase();
//		System.out.println(A);
//		System.out.println(B);
//		
//		System.out.println(A.toUpperCase());
//		System.out.println(A);
		
		//StringBuilder - mutable, more efficient than STringBuffer
		//StringBuufer - mutable, thread safe/synchronized
		
//		String test = "Rick";
		StringBuilder sb = new StringBuilder("Rick");// Converted String to StringBuilder
		StringBuilder sb2 = sb;
		
		sb.append(" and Morty");
		System.out.println("sb :" + sb);
		System.out.println("sb2 :" + sb2);
		String stringy = sb.toString(); //Changes STringBuilder back to String
		
		String moreTest = "Morty";
		StringBuffer sbuf = new StringBuffer(moreTest); //Converts String to StringBuffer
		sbuf.append(" and Summer");
		System.out.println(sbuf);
		String stringy2 = sbuf.toString();
		
		//All 3 classes are final and not related to each other
	}

}
