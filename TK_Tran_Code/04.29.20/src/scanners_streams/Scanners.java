package scanners_streams;

import java.util.Scanner;

public class Scanners {
    public static void main(String[] args) {

        // Creates a Scanner Object
        Scanner sc = new Scanner(System.in);

        System.out.println("Type a String for s: ");
        String s = sc.nextLine();
        System.out.println("String: " + s);

        // Best practice to ensure proper input format
        try {
            System.out.println("Type a double for d: ");
            String str = sc.nextLine();             // .nextLine() reads String user input
            double d = Double.parseDouble(str);     // Converts String input into double
            System.out.println("Double: " + d);
        } catch (NumberFormatException e) {
            System.out.println("Invalid format!");
        }

        // Closes Scanner Object
        sc.close();
    }
}

// Incorporate methods to call scanners to assign user input.