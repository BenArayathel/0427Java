package stuff;


public class TitleCase {

	public static void main(String[] args) {
		String s="hello hi how are you doing today";
		StringBuilder sb=new StringBuilder();
		String ar[]=s.split(" ");
		for (int i = 0; i < ar.length; i++) {
			sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)).append(" ");
			}
			System.out.println(sb.toString().trim());
			
	/*
	 *1)try converting last letter of every word to uppercase
	 */
			StringBuilder sb2 = new StringBuilder();
			for (int i = 0; i < ar.length; i++) {
				sb2.append(ar[i].substring(0, (ar[i].length()-1))+(ar[i].substring(ar[i].length()-1).toUpperCase())+(" ")); 
			}
			System.out.println(sb2.toString().trim());
	 /*
	 *2)if the length of the word is odd then convert middle letter of the word as uppercase 
	 *else print as it is
	 * 
	 */
			StringBuilder sb3 = new StringBuilder();
			for (int i = 0; i < ar.length; i++) {
				if ((ar[i].length())%2 != 0)
				{
					sb3.append(ar[i].substring(0, ((ar[i].length())/2))).append(Character.toUpperCase(ar[i].charAt((ar[i].length())/2))).append(ar[i].substring(((ar[i].length())/2)+1)).append(" ");
				}
				else
				{
					sb3.append(ar[i]).append(" ");
				}
			}
			System.out.println(sb3.toString().trim());
	}	
}
