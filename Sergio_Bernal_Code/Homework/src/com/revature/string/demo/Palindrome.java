package com.revature.string.demo;

public class Palindrome {

	public static void main(String[] args) {
		
		//String p = "madam";
		//StringBuilder sb = new StringBuilder(p);
		//sb.reverse();
		
		for (int i = 1000; i <= 9999; i++) {
			String p = i+"";
			if (new StringBuilder(p).reverse().toString().equals(p)) {
				System.out.println(p);
			}
		}
		
	}

}
