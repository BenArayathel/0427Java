package april29;
import java.lang.StringBuilder;

public class TitleCaseAssignment {

	public TitleCaseAssignment() {
		
	}
	
	public String lastLetterCapitalize(String s) {
		StringBuilder sb = new StringBuilder();
		String ar[] = s.split(" ");
		
		/*
		 * the sb appends the front part of the word, appends the last letter that gets capitalized, and then adds a space
		 */
		for (int i = 0; i < (ar.length); i++) {
			sb.append(ar[i].substring(0, (ar[i].length() - 1) )).append(Character.toUpperCase(ar[i].charAt(ar[i].length()-1))).append(" ");
		}
		return sb.toString().trim();
	
		
	}
	
	public String capitalizeMiddleOdd(String st) {
		StringBuilder sb2 = new StringBuilder();
		String ar2[] = st.split(" ");
		int midIndex;
		
		for (int j = 0; j < ar2.length; j++) {
			if (ar2[j].length() % 2 == 0) {
				sb2.append(ar2[j] + " ");	
			}
			else {
				midIndex = (ar2[j].length() + 1) / 2;
				sb2.append(ar2[j].substring(0, (midIndex -1))).append(Character.toUpperCase(ar2[j].charAt((midIndex - 1)))).append(ar2[j].substring((midIndex))).append(" ");
			}
		}
		
		return sb2.toString().trim();
	}

}
