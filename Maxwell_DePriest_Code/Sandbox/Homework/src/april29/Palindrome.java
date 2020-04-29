
package april29;

import java.lang.StringBuilder;

public class Palindrome {
	
	private String firstNum;
	private StringBuilder secondNum;
	
	public Palindrome() {
		
	}
	
	public void thousandsPalindrome() {
		for (int i = 1000; i < 9999; i++) {
			firstNum = i+"";
			StringBuilder thirdNum = new StringBuilder(firstNum);
			secondNum = thirdNum.reverse();
			
			if (firstNum.equals(secondNum.toString())) {
				System.out.println(firstNum);
			}
		}
	}

}
