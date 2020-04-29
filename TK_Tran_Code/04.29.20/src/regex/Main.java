package regex;

public class Main {
    public static void main(String[] args) {

        String s ="123abc wasd qwerty !@#$%^&*()";
        System.out.println(s);

        System.out.println(s.replaceAll("[a-zA-Z]", ""));   // Replaces alphanumerics w/ whitespace
        System.out.println(s.replaceAll("[a-zA-Z]", "").length());
        System.out.println(s.replaceAll("[^a-zA-Z]", ""));  // Replace everything (except alphanumerics) w/ whitespace
        System.out.println(s.replaceAll("[^a-zA-Z]", "").length());
        System.out.println(s.replaceAll("[ a-zA-Z0-9]", ""));   // Removes whitespace
        System.out.println(s.replaceAll("[ a-zA-Z0-9]", "").length());

        // First 5 letters should be uppercase followed by 4 digits and followed by 1 uppercase letter
        String p = "ABXYY11210";
        if (p.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}