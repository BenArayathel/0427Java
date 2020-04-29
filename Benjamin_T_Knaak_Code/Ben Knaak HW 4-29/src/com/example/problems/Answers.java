package com.example.problems;

public class Answers {
	public static void main(String[] args) {
		// Regex for SSN and Drivers License #
		String fakeSSN = "123-45-6789";
		String badFakeSSN = "12-3456-789";
		String otherBadFakeSSN = "123-4f-6789";
		String[] ssnCollection = {fakeSSN, badFakeSSN, otherBadFakeSSN};
		
		for (String str : ssnCollection) {
			if (str.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
				System.out.println("SSN Valid");
			} else {
				System.out.println("SSN Invalid");
			}
		}
		
		String fakeDL = "Z123456789012";
		String badFakeDL = "A1234";
		String otherBadFakeDL = "a123456789012";
		String[] dlCollection = {fakeDL, badFakeDL, otherBadFakeDL};
		
		for (String str : dlCollection) {
			if (str.matches("[A-Z]{1}[0-9]{12}")) {
				System.out.println("License # Valid");
			} else {
				System.out.println("License # Invalid");
			}
		}
		
		// Print all palindromes between 1000 and 9999
		for (int i = 1000; i < 10000; i++) {
			String s = i + "";
			if(new StringBuilder(s).reverse().toString().equals(s)) {
				System.out.println(s);
			}
		}
		
		// Convert every last letter to upper case
		String s="this is ben's awesome test phrase";
		StringBuilder sb=new StringBuilder();
		String ar[]=s.split(" ");
		for (int i = 0; i < ar.length; i++) {
			sb.append(ar[i].substring(0, ar[i].length() - 1).concat((ar[i].substring(ar[i].length() - 1)).toUpperCase()).concat(" "));
		}
		System.out.println(sb.toString().trim());
		
		// Convert middle letters of words with odd number of letters to upper case
		StringBuilder sb2 = new StringBuilder();
		for (int i = 0; i < ar.length; i++) {
			if (ar[i].length() % 2 == 1) {
				sb2.append(ar[i].substring(0, ar[i].length() / 2).concat("" + Character.toUpperCase(ar[i].charAt(ar[i].length() / 2))).concat(ar[i].substring(ar[i].length() / 2 + 1)).concat(" "));
			} else {
				sb2.append(ar[i]).append((" "));
			}
		}
		System.out.println(sb2.toString().trim());
	}
}
