package com.problems;

public class StringDifference {
	
	public static String stringDifference(int N, String S, String P) {
		String result = null;
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			char s = S.charAt(i);
			char p = P.charAt(i);
			int sn = Integer.parseInt(Character.toString(s));
			int pn = Integer.parseInt(Character.toString(p));
			int r;
			if (sn > pn) {
				r = sn - pn; 
			} else if (pn > sn) {
				r = pn - sn;
			} else {
				r = 0;
			}
			sb.append(Integer.toString(r));
		}
		result = sb.toString();
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(stringDifference(5, "49682", "23498"));
	}

}
