package com.april29;

public class Palindrome {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1000; i <= 9999; i++) {
			String s = i + "";
			if (sb.append(s).reverse().toString().equals(s)) {
				System.out.println(s + " is a palindrome");
			}
			sb.replace(0, sb.length(), "");
		}
	}
}
