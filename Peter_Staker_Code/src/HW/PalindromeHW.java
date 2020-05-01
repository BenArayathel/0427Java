package HW;

public class PalindromeHW {

	public static void main(String[] args) {
		
		for (int i = 1000; i <= 9999; i++) {
			
			String s = i + "";
			
			if(new StringBuilder(s).reverse().toString().equals(s)) {
				System.out.println(i);
			}
		}
	}
}
	
//print all palindromes between 1000 to 9999