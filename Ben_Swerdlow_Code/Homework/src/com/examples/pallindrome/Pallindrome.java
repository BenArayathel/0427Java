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
		for (int i=1000; i<10000; i++) {
			if (Pallindrome.checker(Integer.toString(i))) {
				System.out.println(i);
			}
		}
		
	}

}
/*
 * Homework
 * 		print all pallindromes for integers between 1000 to 9999 - DONE
 */
