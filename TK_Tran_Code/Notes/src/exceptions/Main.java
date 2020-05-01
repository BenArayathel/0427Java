package exceptions;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        
        int x = 10;
        int y = 0;                                  // ** Update this value to test exceptions
        int result = 0;

		// Tests DivideByZeroException and NullPointerException
        try {
            result = x / y; // Throws DivideByZeroException
			System.out.println("Result: " + result);	// Only executes if x can be divided by y
			
            // String s = null;
            // System.out.println(s.length());	// Throws NullPointerException
        } catch (ArithmeticException e) {	// Handles DivideByZeroException
            System.out.println("You cannot divide by zero!");
        // } catch (NullPointerException e) {	// Handles NullPointerException
        //     System.out.println("Null pointer access!");
        } finally {							// Executes regardless of whether exceptions are handled
            System.out.println("Finally still executed regardless!");
        }

        // Also works:
        // catch (ArithmeticException | NullPointerException e) {
        //     if (e instanceof ArithmeticException) {
        //         System.out.println("You cannot divide by zero!");
        //     } else {
        //         System.out.println("Null pointer access!");
        //     }
        // }



        BusinessLayer b = new BusinessLayer();

        // Tests FileNotFoundException
        try {
            b.openFile("test.txt");				// Throws FileNotFoundException
        } catch (FileNotFoundException e) { 	// Handles FileNotFoundException
            System.out.println(e); 				// java.io.FileNotFoundException: test.txt (The system cannot find the file specified), prints a generic error output
        }

        // Tests InvalidAgeException (custom-created)
        try {
            if (b.isValidAge(-10)) {                 // ** Update this value to test exceptions
                System.out.println("Validated");
            }
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());		// Similar to just printing e; just another way of printing error output
        }
    }
}