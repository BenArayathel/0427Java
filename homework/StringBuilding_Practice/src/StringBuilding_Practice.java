
public class StringBuilding_Practice {

	public static void main(String[] args) {
//	    Homework:
//	        1. Convert last letter of every word to uppercase and print.
//	        2. For each word, if length is odd, convert middle letter of word to uppercase; else, print as is.

		String s = "here is a fresh sentence"; //take some 'input'
//		capitalLast(s);
		capitalMiddle(s);

		
	}
	
	
	public static void capitalMiddle(String s) {
		StringBuilder myBuilder = new StringBuilder(); //create an empty stringbuilder
		String arr[] = s.split(" "); // split the string, one word at a time, into an array
		for (int k = 0; k < arr.length; k++) { //iterate through the array of words
			StringBuilder myChar = new StringBuilder();
			char chararr[] = arr[k].toCharArray();
			
//			fill the stringbuilder up with each letter from the array
			myChar
				.append(chararr);
			for (char b : chararr) {
				System.out.print(b);				
			}
		}
	}
	
	/*
	 * 0. break sentence into array of words
	 * 1. iterate through the array to get each word
	 * 2. break each word into arrays of chars
	 * 3. iterate through each array of char, determine if length is even or odd
	 */
	
	
	public static void capitalLast(String s) {
		StringBuilder sb = new StringBuilder(); // make an empty stringbuilder
		String ar[] = s.split(" "); //make an array of each individual word
		for (int i = 0; i < ar.length; i++) {
	//		all the magic happens here, figure out what to fill our stringbuilder
	//		sb.append(Character.toUpperCase(ar[i].charAt(ar[i].length()-1))).append(ar[i]);
			sb
				.append(ar[i].substring(0,ar[i].length()-1))
					.append(Character.toUpperCase(ar[i].charAt(ar[i].length() - 1)))
						.append(" ");
		}
		System.out.println(sb);
	}

}


//        // Goal: convert every word's first letter to uppercase
//        String s = "hello how are you";
//        StringBuilder sb = new StringBuilder();
//        String ar[] = s.split(" ");
//        for (int i = 0; i < ar.length; i++) {
//            sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)).append(" ");
//        }
//        System.out.println(sb.toString().trim());


