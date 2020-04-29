package builder_buffer;

public class Palindrome {
    public static void main(String[] args) {
        
        String s = "madam";

        // Testing if the String is a Palindrome
        StringBuilder sb = new StringBuilder(s);    // Converts String to Builder/Buffer to use methods.
        sb.reverse();                               // NOW we can use methods such as .reverse().
        String s1 = sb.toString();                  // Converts StringBuilder back to String.
        if (s.equals(s1)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }

        // Shorthand way!
        if (new StringBuilder(s).reverse().toString().equals(s)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
    }
}

/*
    Homework:
    Print all palindromes between 1000 to 9999.
        Tip: Surround condition with a for loop
*/