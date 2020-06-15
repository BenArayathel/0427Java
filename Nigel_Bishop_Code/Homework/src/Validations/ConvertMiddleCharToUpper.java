package Validations;

public class ConvertMiddleCharToUpper {

	public static void main(String[] args) 
	{
		
		String s="hello hi how are you doing today";             // Input string
		StringBuilder sb=new StringBuilder();                    // create new string builder
		String ar[]=s.split(" ");                                // split input string at " " while initializing a new string array
		for (int i = 0; i < ar.length; i++)                      // traverse the string array with the max length of array
		{		
			if(ar[i].length() % 2 == 0)                         // find length of even string in the string array
			{
				sb.append(ar[i]).append(" ");                   // continue the sequence by appending a space (" ") at the end
			}
			else                                                // Odd strings in the string array
			{
				int mIndex = (ar[i].length() /2);				//index of the middle character eg. "today" length=5/2 ans=2, index 2 = "d"
				sb.append(ar[i].substring(0,mIndex));           // specify the sequence of characters in the string (index 0 to middle index (length()/2)
				char c = Character.toUpperCase(ar[i].charAt(mIndex)); // convert the middle index character to upper case
				sb.append(c);                                         // continue the sequence by appending the newly converted middle character
				sb.append(ar[i].substring(mIndex/2+2)).append(" ");   // continue the sequence by appending the remaining character starting after the middle index character
                                                                      // also appending a space (" ") at the end
			}
		}
		System.out.println(sb.toString().trim());              // convert string builder back to string, if needed trim space before/after string
        													   // and finally print the complete string including the middle character upper case 
	}                                                          // while ignoring the even string length
}
