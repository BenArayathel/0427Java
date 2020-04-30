package builder_buffer;

public class HomeworkPalindrome {
    public static void main(String[] args) {

        // Homework: Print all palindromes between 1000 and 9999.
        for (int i = 1000; i <= 9999; i++) {          // Starts off with an int holding 1000
            String x = i + "";                        // Converts int to Integer to String (shorthand)
            StringBuilder sb = new StringBuilder(x);  // Converts String to StringBuilder to use .reverse()
            sb.reverse();                             // Reverses the number
            String s1 = sb.toString();                // Converts StringBuilder back to String
            if (x.equals(s1)) {                       // Compares the two Strings; if equal print it
                System.out.println(i);
            }
        }
    }
}