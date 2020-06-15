package Validations;

public class ConvertLastCharToUpper {

	public static void main(String[] args) 
	{
		
		String s="hello hi how are you doing today";             // Input string
		StringBuilder sb=new StringBuilder();                    // create new string builder
		String ar[]=s.split(" ");                                // split input string at " " while initializing a new string array
		for (int i = 0; i < ar.length; i++)                      // traverse the string array with the max length of array
		{		
			sb.append(ar[i].substring(0,ar[i].length()-1));      // specify the sequence of characters in the string (index 0 to last index (length()-1)
			sb.append(Character.toUpperCase(ar[i].charAt(ar[i].length()-1))).append(" "); // Upper Case the last index and append space (" ") after the last index
		}

		System.out.println(sb.toString().trim());              // convert string builder back to string, if needed trim space before/after string
		                                                       // and finally print the complete string including the last character upper case 

	}

}
