package com.examples.palindrome;

public class Palindrome {
	
	public static boolean isPalindrome(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.reverse();
		if (s.equals(sb.toString())) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		int count = 0;
		for (int i=1000; i<10000; i++) {
			if (Palindrome.isPalindrome(Integer.toString(i))) {
				System.out.println(i);
				count += 1;
			}
		}
		System.out.println("There are "+count+" pallindrome numbers from [1000, 9999].");
		
	}

}
/*
 * Homework
 * 		print all pallindromes for integers between 1000 to 9999 - DONE
 * 
 * Look at the Array structure within Java
 */
