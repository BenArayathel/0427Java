package homeworkOne;

public class TitleCase {

	public static void main(String[] args) {
		String demo = "hi my name is imran hello how are you doing today";
		StringBuilder demoSb = new StringBuilder();
		String array[] = demo.split(" ");
		int a = 0;
		for (int i = 0; i < array.length; i++) {
			a = array[i].length();
		    demoSb.append(array[i].substring(0,a-1)).append(Character.toUpperCase(array[i].charAt(a-1))).append(" ");
		}
		System.out.println(demoSb.toString().trim());
		/*
		 * The above will make the string have capital
		 * letters at the end of the word
		 */
		
		
		
		StringBuilder demo2 = new StringBuilder();
		String array2[] = demo.split(" ");
		int b = 0;
		for(int j = 0; j < array2.length; j++) {
			b=array[j].length();
			if(b % 2 == 0) {
				demo2.append(array2[j].substring(0,b-1)).append(Character.toUpperCase(array2[j].charAt(b-1))).append(" ");
			} else {
				demo2.append(array2[j].substring(0,(b/2))).append(Character.toUpperCase(array2[j].charAt(b/2))).append(array2[j].substring((b/2)+1,b)).append(" ");
			}
		}
		System.out.println(demo2.toString().trim());
		}
		/*
		 * The above will make the even numbered words
		 * have capital letters at the end, and the
		 * odd numbered words will have capital letters
		 * in the middle
		 */
}