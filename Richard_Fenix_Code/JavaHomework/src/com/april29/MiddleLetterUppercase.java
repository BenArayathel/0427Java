package com.april29;

public class MiddleLetterUppercase {
	
	public static void main(String[] args) {
		String str = "hello hi how are you doing today";
		String[] ar = str.split(" ");
		StringBuilder sb = new StringBuilder();
		int wordLength = 0;
		for (int i=0; i<ar.length; i++) {
			wordLength = ar[i].length();
			
			// check and process only if length of word is odd
			if (wordLength > 2 && wordLength % 2 == 1) {
				// copy first half of characters except the middle character (that needs to be capitalized)
				sb.append(ar[i].substring(0, wordLength/2));
			
				// capitalize the last character and store in a variable.
				char midChar = Character.toUpperCase(ar[i].charAt(wordLength/2));

				// append the capitalized middle character to the StringBuilder.
				sb.append(midChar);
				
				// add the second half
				sb.append(ar[i].substring(wordLength/2+1)).append(" ");
			} else {
				sb.append(ar[i]).append(" ");
			}

		}
		
		str = sb.toString().trim();
		System.out.println(str);
	}

	// OUTPUT: heLlo hi hOw aRe yOu doIng toDay

}
