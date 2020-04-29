package builder_buffer;

public class TitleCase {
    public static void main(String[] args) {

        // Goal: convert every word's first letter to uppercase
        String s = "hello how are you";
        StringBuilder sb = new StringBuilder();
        String ar[] = s.split(" ");
        for (int i = 0; i < ar.length; i++) {
            sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}

/*
    Homework:
    1. Convert last letter of every word to uppercase and print.
    2. For each word, if length is odd, convert middle letter of word to uppercase; else, print as is.
*/