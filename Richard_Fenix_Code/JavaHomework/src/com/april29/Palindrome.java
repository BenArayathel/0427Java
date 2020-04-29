package com.april29;

// Print all palindrome between 1000 - 9999;
public class Palindrome {
	public static void main(String[] args) {	
		int count = 0;
		for (int i=1000; i<=9999; i++) {
			String stringNumber = i + "";
			StringBuilder sb = new StringBuilder(stringNumber);
			String reversedString = sb.reverse().toString();
			
			if (stringNumber.equals(reversedString)) {
				System.out.println(i);
				count++;
			}
		}
		System.out.println("Total number of palindrome is " + count);
	
	}
}
