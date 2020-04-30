package pali;

public class FindPalindrome {
	
	//print all palindromes between 1000-9999
	public static void main(String[] args) {
		int j = 0;
		String p1; 
		for(int i = 1000; i < 10000; i++) {
			p1 = i+"";
			StringBuilder sb = new StringBuilder(p1);
			sb.reverse();
			String p2=sb.toString();
			if(p1.equals(p2)) {
				System.out.println(p1);
				j++;
			}
		}
		System.out.println("Number of palindormes in this range is "+j);
		
	}

}
