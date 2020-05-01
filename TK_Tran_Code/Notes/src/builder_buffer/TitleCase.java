package builder_buffer;

public class TitleCase {
    public static void main(String[] args) {

        // Goal: capitalize first letter of every word and print
        String s = "im having soup today";
        String arr[] = s.split(" ");                 // Splits string literal into an array of words by every space
        StringBuilder sb = new StringBuilder();     // Creates StringBuilder to use various methods
        for (int i = 0; i < arr.length; i++) {       // Loops through array
            // 1st .append(): grabs 1st char (.charAt(0)) at current array index (arr[i]), capitalize it and append to sb (it's now at beginning of sb).
            // 2nd .append(): grabs from 2nd char TO THE END OF THE STRING (substring(1)) at current array index (arr[i]) and append it normally to 1st char.
            // 3rd .append(): simply add a space after each word.
            sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
        }

        System.out.println(sb.toString().trim());   // Converts StringBuilder back to String and print, trimming any leading/trailing whitespace.
    }
}

/*
    Homework:
    1. Convert last letter of every word to uppercase and print. [X]
    2. For each word, if length is odd, convert middle letter of word to uppercase; else, print as is.
*/