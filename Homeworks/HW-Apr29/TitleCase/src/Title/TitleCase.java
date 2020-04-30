package Title;

 
public class TitleCase {


 

	public static void main(String[] args) {
		
		//try converting last letter of every word to uppercase
		String s="hello hi how are you doing today";
		StringBuilder sb=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		String ar[]=s.split(" ");
		for (int i = 0; i < ar.length; i++) {
			sb.append(ar[i].substring(0, ar[i].length()-1)).append(Character.toUpperCase(ar[i].charAt(ar[i].length()-1))).append(" ");
		}
		System.out.println(sb.toString());
		
		//if the length of the word is odd then convert middle letter of the word as uppercase else print as it is
		for (int i = 0; i < ar.length; i++) {
			if(ar[i].length() % 2 != 0) {

				sb2.append(ar[i].substring(0, (ar[i].length()/2))).append(Character.toUpperCase(ar[i].charAt((ar[i].length()/2)))).append(ar[i].substring((ar[i].length()/2)+1, (ar[i].length()))).append(" ");
			}else {
				sb2.append(ar[i]).append(" ");
			}
			
		}
		System.out.println(sb2.toString());
	} 


}
