package hw;

public class TitleCaseHW {
	public static void main(String[] args) {
		String s = "hello hi how are you doing today";
		StringBuilder sb = new StringBuilder();
		String ar[] = s.split(" ");
		for (int i = 0; i < ar.length; i++) {
			sb.append(ar[i].substring(0, (ar[i].length()-1))).append(Character.toUpperCase(ar[i].charAt(ar[i].length()-1))).append(" ");
		}
		System.out.println(sb.toString().trim());
	}
}

/*
 * 1. try converting last letter of every word to uppercase 
 */