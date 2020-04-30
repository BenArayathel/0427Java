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
			String lastletter = splitString[i].substring((wordLength-1), (wordLength));
			char lastletter2 = splitString[i].charAt(splitString[i].length()-1);
			System.out.println(lastletter2);
		}
	
		
	
		
	
	}
	
}

// try converting last letter of every word to uppercase
// if the length of the word is odd, convert middle letter of the word to upper case, else print as it is.
