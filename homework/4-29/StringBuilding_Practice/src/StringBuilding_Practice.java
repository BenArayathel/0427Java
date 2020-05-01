
public class StringBuilding_Practice {

	public static void main(String[] args) {
//	    Homework:
//	        1. Convert last letter of every word to uppercase and print.
//	        2. For each word, if length is odd, convert middle letter of word to uppercase; else, print as is.

		String s = "here is a fresh sentence"; //take some 'input'
//		capitalLast(s);
		capitalMiddle(s);
//		MiddleCaps(s);

		
	}
	
	// capitalize the last letter of every word in the sentence
	public static void capitalLast(String s) {
		StringBuilder sb = new StringBuilder(); // make an empty stringbuilder
		String ar[] = s.split(" "); //make an array of each individual word
		for (int i = 0; i < ar.length; i++) {
	//		all the magic happens here, figure out what to fill our stringbuilder
			sb
				.append(ar[i].substring(0,ar[i].length()-1))
					.append(Character.toUpperCase(ar[i].charAt(ar[i].length() - 1)))
						.append(" ");
		}
		System.out.println(sb);
	}

	
//	capitalize the middle word of every odd numbered word in a sentence
	public static void capitalMiddle(String s) {
		StringBuilder myBuilder = new StringBuilder(); //create an empty stringbuilder
		String arr[] = s.split(" "); // split the string, one word at a time, into an array
		for (int k = 0; k < arr.length; k++) { //iterate through the array of words
			if (arr[k].length() % 2 != 0) {
				int halfway = ((arr[k].length()) / 2);
				arr[k] = arr[k].substring(0, halfway)
							+ arr[k].substring(halfway, halfway + 1).toUpperCase()
									+ arr[k].substring(halfway + 1, arr[k].length());
				myBuilder.append(arr[k]).append(" ");
			} else {
				myBuilder.append(arr[k]).append(" ");
			}

			}
		System.out.println(myBuilder.toString().trim());
		}
	}
	

	

// CORRRECT MIDDLE CODE:

//	public static void MiddleCaps(String s) {
//
////		String s = "hello hi how are you today";
//		String[] ar = s.split(" ");
//		StringBuffer sb=new StringBuffer(); //Can use either builder or buffer, create it as empty first
//		for(int i = 0;i<ar.length;i++) {
//			if(ar[i].length()%2 != 0) { //Check whether the String is odd or even 
////				//reassign 
//				ar[i] = ar[i].substring(0, (ar[i].length())/2)  //First half of the string
//						+ ar[i].substring(( ar[i].length())/2, ((ar[i].length())/2) + 1).toUpperCase() //capitalize the middle character String
//						+ ar[i].substring(((ar[i].length())/2) + 1, ar[i].length()); //add the remaining part of the string
//				//Using API (more elegant)
////				char c[]=ar[i].toCharArray(); //converting String into an array
////				c[c.length/2]=Character.toUpperCase(c[c.length/2]); // Converting middle char of string to upper case
////				sb.append(new String(c)).append(" "); //Converting array back into String and appending it to a buffer 
////			} else {
//				sb.append(ar[i]).append(" ");
////			}
//			System.out.print(ar[i] + " ");
//		}
////		System.out.println(sb.toString().trim());
//		}
//	}
//}


//// Goal: convert every word's first letter to uppercase
//String s = "hello how are you";
//StringBuilder sb = new StringBuilder();
//String ar[] = s.split(" ");
//for (int i = 0; i < ar.length; i++) {
//  sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)).append(" ");
//}
//System.out.println(sb.toString().trim());