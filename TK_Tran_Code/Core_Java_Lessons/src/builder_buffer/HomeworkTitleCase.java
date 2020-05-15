package builder_buffer;

public class HomeworkTitleCase {
    public static void main(String[] args) {
        
        // Homework: convert last letter of every word to uppercase and print.
        String str = "im having soup tonight";
        String arr[] = str.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            // Grabs length of each word in array and assigns to int variable.
            int wordLength = arr[i].length();

            // Grabs substring from beginning (0) to second to last char (wordLength - 1) from each word and appends to sb.
            sb.append(arr[i].substring(0, wordLength - 1));
            
            // Grabs last char from each word, wraps it in Character object in order to capitalize, and assigns back to char variable
            char lastChar = Character.toUpperCase(arr[i].charAt(wordLength - 1));

            // Appends last char to sb along with a space
            sb.append(lastChar);
            sb.append(" ");
        }

        // Converts sb back to String (optional), trims in the process, and prints that String out.
        str = sb.toString().trim();
        System.out.println(str);
    }
}