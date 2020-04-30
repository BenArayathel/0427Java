package com.example.Strings;

public class BufferBuilder {
	
	public static void main(String[] args) {
		//int x = 1212;
		//String s = x + "";
		String s = "madam"; // palindrome are the same word forward and backwards
		StringBuilder sb = new StringBuilder(s);
		sb.reverse();
		String s1 = sb.toString();
		if (s.equals(s1)) {
			System.out.println("Its Palindrome");
		} else {
			System.out.println("Not a Palindrome");
		}
		
		if (new StringBuilder(s).reverse().toString().equals(s)) {
			System.out.println("Its Palindrome");
		} else {
			System.out.println("Not a Palindrome");
		}
		
		// print all palindromes between 1000 to 9999 (while using the StringBuilder)
		// *hint* use a "for loop"
		
		System.out.println("Printing Palindromes from 1000 to 9999....\n");
		System.out.println("loopIteration : palindrome");
		System.out.println("----------------------------");
		int count = 1;
		int total = 0;
		for (int x = 1000; x < 10000; x++) {
			String s4 = "" + x;
			if (new StringBuilder(s4).reverse().toString().equals(s4)) {
				System.out.println("\t" + count + " : " + s4);
			}
			count++;
		}
	}
}