package Validations;

public class Palindromes {

	public static void main(String[] args) {
		
		// Print all Palindromes between 1000-9999

		
		int totalPalindromes = 0;                  // initialize total Palindrome to 0
		for (int i = 1000; i <= 9999; i++) 
		{
			
			String s = i+"";                       // Convert the index integer to string
			StringBuilder sb=new StringBuilder(s); //Create string builder with newly converted string
			sb.reverse();                          // reverse the string builder
			String s1 = sb+"";                     // Convert string builder back to string for comparison
			
			if(s.equals(s1))                       // compare to verify they are Palindrome
			{            
				System.out.println(i);             // print Palindrome to console		
				totalPalindromes++;                // Increment total Palindrome
			}
		}
		
		System.out.println("Total number of Palindromes: " + totalPalindromes);
	}

}
