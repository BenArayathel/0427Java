package april29;

public class Palidromes {
	// find all palindromes from 1000 to 9999
	public static void main(String[] args) {
		int p = 0;
		for (int i = 1000; i < 10000; i++) {
			StringBuilder s = new StringBuilder(i + "");
			if (s.toString().equals(s.reverse().toString())) {
				System.out.println(s);
				p++;
			}
		}
		System.out.println("Found " + p + " palindromes.");
	}
}
