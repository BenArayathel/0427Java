package eg_buffer_builder;

/**
 * 
 * PLEASE READ FIRST!!!!!!!
 * 
 * I wanted to take a different and unique approach to this problem 
 * so I used a for loop with an additional iterator int
 * I also used a recursive function instead of a simple for loop to 
 * try it out in a recursive function and would be interested to clock the 
 * runtime on this as a for loop and see which one executes faster!
 */
public class DavesPalindrome {
	private int iterator;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DavesPalindrome p = new DavesPalindrome();
		
		//My own rendition of palindrome 
		p.enterWord("racecar");
		
		//Recursive method, would like to discuss adjusting JVM to handle more than 8999 inputs
		//NOT FULLY IMPLEMENTED
		//p.iteratePalindromes(7999);
		
		
		//Simple for loop
		p.checkPalindromes("9999");
	}
	
	public DavesPalindrome() {
		this.iterator = 0;
		
	}

	/**
	 * Prints out whether word is a palindrome or not
	 * @param inWord the value passed in to assess 
	 */
	public void enterWord(String inWord) {
			int m = 0;
			int j = inWord.length() - 1;
			for (int i = 0; i < inWord.length(); i++) {
				if(inWord.charAt(i) == inWord.charAt(j)) {
					m++;
				}
				j--;
			}
			
			if(m == inWord.length()) {
				System.out.println(inWord + " is a palindrome!");
			}
			else {
				System.out.println(inWord + " is NOT a palindrome!");
			}
	}
	
	/**
	 * Recursively iterates list and checks for palindromes
	 * @param inValue the integer passed in at higest value for assessment
	 */
	public void iteratePalindromes(Integer inValue) {
		if(inValue < 1000) {
			System.out.println("Number of palindromes in list: " + this.iterator);
			return;
		}else {
			int m = 0;
			int j = 3;
			String valueString = String.valueOf(inValue);
			for (int i = 0; i < valueString.length(); i++) {
				if(valueString.charAt(i) == valueString.charAt(j)) {
					m++;
				}
				j--;
			}
			
			if(m == valueString.length()) {
				this.iterator++;
			}
			this.iteratePalindromes(inValue - 1);
			
		}
	}
	
	/**
	 * for loop non-recursive method of checking if each input is a palindrome
	 * @param inWord the value passed in to be assessed
	 */
	public void checkPalindromes(String inWord) {
		int iterator2 = 0;
		
		
		//For loop that iterates through each word
		for(int inNumbers = Integer.parseInt(inWord); inNumbers >= 1000; inNumbers--) {
			int j = inWord.length() - 1;
			int m = 0;
			StringBuilder sb = new StringBuilder();
			sb.append(inNumbers);
			//iterate through each char of current string
			for (int i = 0; i < inWord.length(); i++) {
				if(sb.charAt(i) == sb.charAt(j)) {
					m++;
				}
				j--;
			}
			if(m == inWord.length()) {
				iterator2++;
				
			}
			m = 0;
		}
		
		System.out.println("There are " + iterator2 + " palindromes in this list.");
		
	}
}
