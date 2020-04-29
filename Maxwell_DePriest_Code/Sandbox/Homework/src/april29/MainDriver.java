/*

1. Regex - write validation for SSS or Driver’s License.
2. Print all palindrome between 1000 - 9999;
3. Converting last letter of every word in upper case 
Tip:
   S = “hello hi how are you doing today”
	for loop:
	StringBuilder sb=new StringBuilder();
	String ar[]=s.split(” “;)	sb.append(Character.toUpperCase(ar[i].charAt(0))).apppend(ar[i].substring(1)).append(” “);
4. If the length of the word is odd, convert middle letter of the word as uppercase or print as it is.

*/

package april29;

public class MainDriver {
	
	public static void main(String[] args) {
		RegEx rX = new RegEx();
		System.out.println("1a.");
		rX.ssnValid("34");
		rX.ssnValid("123-45-6789");
		rX.ssnValid("One-Two-Three");
		rX.ssnValid("   56-786-8765");
		System.out.println("1b.");
		rX.dLicenseValid("123456789");
		rX.dLicenseValid("666");
		rX.dLicenseValid("I'm a real driver!");
		System.out.println();
		System.out.println();
		Palindrome p = new Palindrome();
		System.out.println("2. ");
		p.thousandsPalindrome();
		System.out.println();
		TitleCaseAssignment tca = new TitleCaseAssignment();
		System.out.println("3."); 
		System.out.println(tca.lastLetterCapitalize("Well hello there little buddy"));
		System.out.println();
		System.out.println("4. ");
		System.out.println(tca.capitalizeMiddleOdd("Well hello there little buddy"));
		
		
		
		
	}



}
