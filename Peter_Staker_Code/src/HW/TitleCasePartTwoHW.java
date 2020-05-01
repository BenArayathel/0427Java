package HW;

public class TitleCasePartTwoHW {
	public static void main(String[] args) {
		String s = "hello hi how are you doing today";
		StringBuilder sb = new StringBuilder();
		String ar[] = s.split(" ");
		for (int i = 0; i < ar.length; i++) {
			if(ar[i].length() % 2 != 0) {
				sb.append(ar[i].substring(0, (ar[i].length()/2))).append(Character.toUpperCase(ar[i].charAt(ar[i].length()/2))).append(ar[i].substring((ar[i].length()/2)+1)).append(" ");
			}else {
				sb.append(ar[i]).append(" ");
			}
		}
		System.out.println(sb.toString().trim());
	}
}

// 2. if the length of the word is odd then convert middle letter of the word as uppercase,
//  else print as it is