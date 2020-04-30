package eg_buffer_builder;

import java.security.acl.LastOwnerException;

public class DavesTitleCase {

	public static void main(String[] args) {
		String s="hello hi how are you doing today";
		StringBuilder sb=new StringBuilder();
		String ar[]=s.split(" ");
		for (int i = 0; i < ar.length; i++) {
			sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)).append(" ");
		}
		System.out.println(sb.toString().trim());
		 
		System.out.println(new DavesTitleCase().lastLetterToUpper("this is my new string"));
		
		System.out.println(new DavesTitleCase().camelCase("seven"));

	}
	
	public DavesTitleCase() {
		
	}
	
	public String lastLetterToUpper(String inString) {
		StringBuilder builder = new StringBuilder();//Builds a new String Builder
		String[] words = inString.split(" ");	//Splits the input string into individual words delimited by " "
		
		
		for (int i = 0; i < words.length; i++) {
			//System.out.println("Something: " + words[i].toUpperCase().charAt(words[i].length() - 1));
			builder.append(words[i].substring(0, words[i].length() - 1)).append(words[i].toUpperCase().charAt(words[i].length() - 1)).append(" ");
		}
		
		return builder.toString();
	}
	
	
	
	
	public String camelCase(String inString) {
		String s = inString;
		
		if(inString.length() % 2 == 0) {
			//Do Nothing just return value
		}else { //Camel Case the String
			Character upperChar = inString.toUpperCase().charAt(inString.length() / 2);
			s = s.replace(inString.charAt(inString.length() / 2), upperChar);
		}
		
		return s;
	}

}
/*
 *1)try converting last letter of every word to uppercase
 *2)if the length of the word is odd then convert middle letter of the word as uppercase 
 *else print as it is
 * 
 */
