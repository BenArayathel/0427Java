package com.hackerrank;

import java.util.Scanner;
import java.lang.Math;
import java.lang.StringBuilder;
	
	class javaLoopTwo{
	    public static void main(String []argh){
	        
	    }
	    public static String mathProblem(int a, int b, int n) {
	    	Scanner in = new Scanner(System.in);
	        StringBuilder sb = new StringBuilder();
	        int t=in.nextInt();
	        for(int i=0;i<t;i++){
	            a = in.nextInt();
	            b = in.nextInt();
	            n = in.nextInt();
	            int result = a;
	            
	            // a+(2^j * b) + (2^j * b)...+ (2^n-1 * b)
	            for (int c = 0; c < n; c++) {
	            	result +=  (Math.pow(2, c) * b);
	            	sb.append(result + " ");
	            	System.out.print(result + " ");
	            }
	        
	        in.close();
	        }
	        return sb.toString();
	    }
	}
	        


 // end class


