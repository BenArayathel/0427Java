package com.examples.string_manipulation;

public class Capitalization {

	public static void main(String[] args) {
		String s = "this is my test sentence.";
		String res1 = Capitalization.lastLetterCapital(s);
		System.out.println(res1);
		
		String t = "this has a lot of odd letter words";
		String res2 = Capitalization.middleCharacterCapital(t);
		System.out.println(res2);
		
		System.out.println(Capitalization.middleCharacterCapital(res1));
	}
	
	public static String lastLetterCapital (String s) {
		StringBuilder sb = new StringBuilder();
		String[] sArr = s.split(" ");
		for (int i=0; i<sArr.length; i++) {
			sb.append(sArr[i].substring(0,sArr[i].length()-1)).append(Character.toUpperCase(sArr[i].charAt(sArr[i].length()-1)));
			sb.append(" ");
		}
		String result = sb.toString().trim();
		return result;
	}
	
	public static String middleCharacterCapital (String s) {
		StringBuilder sb = new StringBuilder();
		String [] sArr = s.split(" ");
		for (int i=0; i<sArr.length; i++) {
			if (sArr[i].length()%2==1) {
				sb.append(sArr[i].substring(0,sArr[i].length()/2));
				sb.append(Character.toUpperCase(sArr[i].charAt(sArr[i].length()/2)));
				sb.append(sArr[i].substring(sArr[i].length()/2+1, sArr[i].length()));
				sb.append(" ");
			} else {
				sb.append(sArr[i]);
				sb.append(" ");
			}
		}
		String result = sb.toString().trim();
		return result;
	}

}
/*
 * 1) try converting last letter of every word to uppercase - DONE
 * 2) if the length of each word is odd, then convert the middle letter of the word to uppercase
 *		else, print as it is - DONE
*/
