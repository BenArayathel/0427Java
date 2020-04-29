package com.april29;

public class LastLetterUppercase {
	
	public static void main(String[] args) {
		String str = "hello hi how are you doing today";
		String[] ar = str.split(" ");
		StringBuilder sb = new StringBuilder();
		int wordLength = 0;
		for (int i=0; i<ar.length; i++) {
			//StringBuilder sb = new StringBuilder();
			wordLength = ar[i].length();
			
			// copy first few characters except the last character (that needs to be capitalized
			sb.append(ar[i].substring(0, wordLength -1));
			
			// capitalize the last character and store in a variable.
			char lastChar = Character.toUpperCase(ar[i].charAt(wordLength-1));

			// append the last character to the StringBuilder and append the extra space.
			sb.append(lastChar).append(" ");
			//System.out.println(sb.toString());			
		}
		
		str = sb.toString().trim();
		System.out.println(str);
	}
}
