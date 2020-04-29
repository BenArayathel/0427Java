package com.examples.pallindrome;

public class Pallindrome {
	
	public static boolean checker(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.reverse();
		String revS = sb.toString();
		if (s.equals(revS)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		int count = 0;
		for (int i=1000; i<10000; i++) {
			if (Pallindrome.checker(Integer.toString(i))) {
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
