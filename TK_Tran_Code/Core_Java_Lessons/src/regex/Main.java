package regex;

public class Main {
    public static void main(String[] args) {

        String s ="123abc wasd qwerty !@#$%^&*()";
        System.out.println(s);

        System.out.println(s.replaceAll("[a-zA-Z]", ""));       // 123   !@#$%^&*(), Replaces all alphanumerics w/ whitespace
        System.out.println(s.replaceAll("[a-zA-Z]", "").length());  // 16
        System.out.println(s.replaceAll("[^a-zA-Z]", ""));      // abcwasdqwerty, Replace everything (except alphanumerics) w/ whitespace
        System.out.println(s.replaceAll("[^a-zA-Z]", "").length()); // 13
        System.out.println(s.replaceAll("[ a-zA-Z0-9]", ""));   // !@#$%^&*(), Replaces all alphanumerics w/ whitespace
        System.out.println(s.replaceAll("[ a-zA-Z0-9]", "").length());  // 10

        // Practice: First 5 letters should be uppercase, followed by 5 digits, followed by 3 uppercase letters
        String p = "ABCDE12345ABC";
        if (p.matches("[A-Z]{5}[0-9]{5}[A-Z]{3}")) {
            System.out.println("Valid format: " + p);
        } else {
            System.out.println("Invalid format!");
        }
    }
}