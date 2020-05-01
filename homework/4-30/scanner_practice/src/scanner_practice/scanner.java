package scanner_practice;

import java.util.Scanner;

public class scanner {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// ALWAYS use nextLine
		// use try/catch for errors
		// convert input to what it should be with parseInt or parseDouble
		// check instructor notes ScannerExample later for .hasNext 
		// and using main method to call it
		try {
			System.out.println("Enter your number: ");
			String myInput = sc.nextLine();
			int i = Integer.parseInt(myInput);
			System.out.println("Your number is " + i);
		} catch (Exception e) {
			System.out.println("Error, try again");
		}
		
		sc.close();
	}

}
