package april29;

public class ReplaceCase {
	/*
	 * Problem Statement
	 * Replace last letter of all words with capital
	 * Replace middle letter of all words of odd length with capital
	 */
	public static void main(String[] args) {
		String s = "Lorem ipsum dolor sit amet, "
				+ "consectetur adipiscing elit, sed do "
				+ "eiusmod tempor incididunt ut labore et "
				+ "dolore magna aliqua. Ut enim ad minim veniam, "
				+ "quis nostrud exercitation ullamco laboris "
				+ "nisi ut aliquip ex ea commodo consequat. "
				+ "Duis aute irure dolor in reprehenderit in "
				+ "voluptate velit esse cillum dolore eu fugiat "
				+ "nulla pariatur. Excepteur sint occaecat cupidatat "
				+ "non proident, sunt in culpa qui officia deserunt "
				+ "mollit anim id est laborum.";
		
		/*
		 * Make an array of all words
		 * If odd, then word length / 2 + 1 must be made capital in array
		 * print first word in array
		 * print space plus subsequent words
		 */
		
		String ar[] = s.split(" ");
		StringBuilder res = new StringBuilder();
		
		for (int i = 0; i < ar.length; i++) {
			StringBuilder currentWord;
			String punctuation = "";
			boolean hasPunctuation = false;
			int len = ar[i].length();
			if (ar[i].substring(len - 1, len).matches("[^A-Za-z]")) { // if word ends in punctuation
				punctuation = ar[i].charAt(len - 1) + "";
				currentWord = new StringBuilder(ar[i].substring(0,len - 2));
				hasPunctuation = true;
				len -= 1;
			} else {
				currentWord = new StringBuilder(ar[i]);
			}
			currentWord.replace(len - 1, len, (ar[i].charAt(len - 1) + "").toUpperCase()); // capitalize end

			if (len % 2 != 0) { // odd length
				currentWord.replace(len / 2, len / 2 + 1, (ar[i].charAt(len/2) + "").toUpperCase()); // cap mid
			}
			if (hasPunctuation)
				currentWord.append(punctuation);
			res.append(currentWord);
			if (i < ar.length - 1) // not at end, add space
				res.append(" ");
		}
		System.out.println(res);
	}
}
