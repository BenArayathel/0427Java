package eg_buffer_builder;

public class Cap_if_Odd {

	public static void main(String[] args) {
		
		String s = "hello hi how are you doing today";
		StringBuilder sb = new StringBuilder();
		String ar[] = s.split(" ");
		for (int i = 0; i < ar.length; i++) {
			//sb.append(Character.toUpperCase(ar[i].charAt(0))).append(ar[i].substring(1)).append(" ");
			
			if (ar[i].length() % 2 != 0) {
				
				// first portion of string
				//System.out.println(ar[i].substring(0, ((ar[i].length() -1) / 2)));
				
				sb.append(ar[i].substring(0, ((ar[i].length() -1) / 2)));
				
				// middle char
				// error
				//System.out.println(ar[i].substring( (int) Math.ceil(ar[i].length() / 2) , ( (int) Math.ceil(ar[i].length() / 2) + 1 ) ));
				
				sb.append(ar[i].substring( (int) Math.ceil(ar[i].length() / 2) , ( (int) Math.ceil(ar[i].length() / 2) + 1 ) ).toUpperCase());
				
				// last portion
				//System.out.println(ar[i].substring(( (ar[i].length() / 2) + 1 ), ar[i].length() ));
				
				sb.append(ar[i].substring(( (ar[i].length() / 2) + 1 ), ar[i].length() )).append(" ");
			}
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
