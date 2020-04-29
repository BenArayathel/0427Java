package exceptions;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        int x = 10;
        int y = 0;  // ** Update values to test scenarios
        int res = 0;

        try {
        res = x / y; // Throws first (DivideByZeroException)
        System.out.println("Result: " + res);
        String s = null;
        System.out.println(s.length()); // Throws second (NullPointerException)
        } catch (ArithmeticException e) { // Handles first (DivideByZeroException)
        System.out.println("You cannot divide by zero!");
        } catch (NullPointerException e) { // Handles second (NullPointerException)
        System.out.println("NullPointerException thrown!");
        } finally {
        System.out.println("Finally still executed! Result: " + res);
        }

        // Also works for above:
        // catch (ArithmeticException | NullPointerException e) {
        //     if (e instanceof ArithmeticException) {
        //         System.out.println("You cannot divide by zero!");
        //     } else {
        //         System.out.println("NullPointerException thrown!");
        //     }
        // }

        System.out.println();

        Business b = new Business();

        try {
            b.openFile("test.txt");     // Throws FileNotFoundException
        } catch (FileNotFoundException e) { // Handles FileNotFoundException
            System.out.println(e);
        }

        try {
            if (b.isValidAge(-1)) { // ** Update value to test scenarios
                System.out.println("Validated");
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}