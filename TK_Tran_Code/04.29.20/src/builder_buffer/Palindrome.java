package builder_buffer;

public class Palindrome {
    public static void main(String[] args) {
        
        // Goal: test whether a String is a palindrome
        String s = "racecar";                         // Original string
        StringBuilder sb = new StringBuilder(s);    // Converts original string to StringBuilder in order to use .reverse()
        sb.reverse();                               // Reverses the StringBuilder
        String s1 = sb.toString();                  // Converts reversed StringBuilder back to String in order compare with original
        if (s.equals(s1)) {                         // Compares original String to reversed String
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
*/