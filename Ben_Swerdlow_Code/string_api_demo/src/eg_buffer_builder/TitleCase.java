package eg_buffer_builder;

public class TitleCase {

	public static void main(String[] args) {
		String s = "hello hi how are you doing today";
		// save memory by using string builder, since we're going to make changes
		StringBuilder sb = new StringBuilder();
		String ar[] = s.split(" ");
		for (int i=0; i<ar.length; i++) {
			sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)+" ");
		}
		System.out.println(sb.toString().trim());
	}

}
/*
 * 1) try converting last letter of every word to uppercase
 * 2) if the length of each word is odd, then convert the middle letter of the word to uppercase
 *		else, print as it is
*/