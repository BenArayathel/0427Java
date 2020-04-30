package com.example.Strings;

public class TitleCase {
	
	public static void main(String[] args) {
		String s = "hello hi how are you doing today";
		StringBuilder sb = new StringBuilder();
		String ar[] = s.split(" ");
		for (int i = 0; i < ar.length; i++) {
			sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)).append(" ");
		}
		System.out.println(sb.toString().trim());
		
		/*
		 * 1)try converting last letter of every word to uppercase
		 * 2)if length of the word is odd then convert middle letter of the word as uppercase else print as it is
		 * *hint* read the documentation on the StringBuilder Class to view the methods at disposal
		 * create a separate workspace named homework and push the branch to github
		*/
		
		// 1)
		System.out.println("\n1) Capitalizing the last letter of each word...\n");
		StringBuilder sb1 = new StringBuilder();
		for (int i = 0; i < ar.length; i++) {
			int lastChar = ar[i].length() - 1;
			sb1.append(ar[i].substring(0, lastChar)).append(Character.toUpperCase(ar[i].charAt(lastChar))).append(" ");
		}
		System.out.println(sb1.toString().trim());
		
		// 2)
		System.out.println("\n2) Capitalizing the middle letter of word with odd number length...\n");
		StringBuilder sb2 = new StringBuilder();
		for (int i =0; i < ar.length; i++) {
			int wordLength = ar[i].length();
			if (wordLength % 2 == 0) { // if the word is of EVEN length
				sb2.append(ar[i]);
			} else { // if the word is of ODD length
				int middleChar = ar[i].length() / 2;
				sb2.append(ar[i].substring(0, middleChar)).append(Character.toUpperCase(ar[i].charAt(middleChar))).append(ar[i].substring(middleChar + 1, ar[i].length())).append(" ");
			}
		}
		System.out.println(sb2.toString());
	}
}