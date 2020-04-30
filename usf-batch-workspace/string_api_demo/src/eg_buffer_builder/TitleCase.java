package eg_buffer_builder;

public class TitleCase {

	public static void main(String[] args) {
		
		String s = "hello hi how are you doing today";
		StringBuilder sb = new StringBuilder();
		String ar[] = s.split(" ");
		for (int i = 0; i < ar.length; i++) {
			sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)).append(" ");
		}
		System.out.println(sb.toString().trim());

	}

}
/*
 * 
 * 2)if the length of
 * the word is odd then convert middle letter of the word as uppercase else
 * print as it is
 * 
 */
