package Passwords;



public class firstToUppercase {

	public static void main(String[] args) {
		
//		String s = "hello hi how are you today";
//		StringBuilder sb=new StringBuilder();
//		String ar[]=s.split(" ");
//		for (int i=0; i < ar.length; i++) {
//			sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)).append(" ");
//		}
//		System.out.println(sb);

		String k = "hello how are you";
		StringBuilder greeting = new StringBuilder();
		String splitString[] = k.split(" ");
		for (int i=0; i<splitString.length; i++) {
			int wordLength = splitString[i].length();
			char lastletter2 = splitString[i].charAt(splitString[i].length()-1);
			String lastletter3 = splitString[i].substring(0,(wordLength)-1);
			greeting.append(lastletter3).append(Character.toUpperCase(lastletter2)).append(" ");
			
			
		}
	System.out.println(greeting);
		
	
		
	
	}
	
}

// try converting last letter of every word to uppercase
// if the length of the word is odd, convert middle letter of the word to upper case, else print as it is.
