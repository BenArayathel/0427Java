package scanners_streams;

import java.util.Scanner;

public class Scanners {
	public static void main(String[] args) {

		// Creates a Scanner Object
		Scanner sc = new Scanner(System.in);

		// // BAD PRACTICE; users can enter an int and program will still work
		// System.out.println("Type a String for s: ");
		// String s = sc.nextLine();
		// System.out.println("String: " + s);

		// BEST PRACTICE; ensures appropriate input format
		try {
			System.out.println("Type a String for s: ");
			String s = sc.nextLine(); // Reads String user input (even numbers as String) and assigns to var
			System.out.println("Your String: " + s);
		} catch (Exception e) {
			System.out.println("Invalid String format!");
		}
		// Remember that sc.nextLine() reads EVERYTHING as a String, even numbers.

		try {
			System.out.println("Type a double for d: ");
			String s = sc.nextLine(); // .nextLine() reads String user input
			double d = Double.parseDouble(s); // Uses Wrapper class Double to call parseDouble() and parse the String to a double and assign to var
			System.out.println("Your double: " + d);
		} catch (NumberFormatException e) {
			System.out.println("Invalid double format!");
		}

		// Closes Scanner Object to free resources
		sc.close();
	}
}