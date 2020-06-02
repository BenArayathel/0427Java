package com.problems;

public class Tribonacci {
	
	public static int tribonacci(int N) {
		int result = 0;
		
		int a1 = 0;
		int a2 = 0;
		int aN = 1;
		int temp;
		
		if (N < 2) {
			result = 0;
		} else if (N==3) {
			result = 1;
		} else {
			while (N>2) {
				System.out.println(a1+" "+a2+" "+aN);
				System.out.println(N);
				temp = a1 + a2 + aN;
//				System.out.println(temp);
				a1 = a2;
//				System.out.println(a1);
				a2 = aN;
//				System.out.println(a2);
				aN = temp;
//				System.out.println(aN);
				N -= 1;
			}
			result = aN;
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		System.out.println("result = "+tribonacci(8));
		
	}

}
