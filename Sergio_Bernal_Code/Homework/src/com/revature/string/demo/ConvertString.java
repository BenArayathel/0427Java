package com.revature.string.demo;

public class ConvertString {

	public static void main(String[] args) {
		
		String s = "hello hi how are you today?";
		StringBuilder lastL = new StringBuilder();
		StringBuilder MdlL = new StringBuilder();
		String sp[] = s.split(" ");
		
		/*for (int i = 0; i < sp.length; i++) {
			sb.append(sp[i].toUpperCase().charAt(0)).append(sp[i].substring(1)).append(" ");
		}*/
		
		// Last letter to UpperCase
		System.out.println("Last letter to Uppercase");
		
		for (int i = 0; i < sp.length; i++) {
			lastL.append(sp[i].substring(0,sp[i].length() - 1)).append(sp[i].toUpperCase().charAt(sp[i].length() - 1)).append(" ");
		}
		
		System.out.println(lastL.toString().trim());
		System.out.println("--------------");
		
		// Middle letter to UpperCase
		System.out.println("Word ODD middle letter to Uppercase");
		
		for (int i = 0; i < sp.length; i++) {
			if ((sp[i].length() % 2) != 0) {
				int d = sp[i].length() / 2;
				
//				StringBuilder sb = new StringBuilder(sp[i]);
//				String a = sp[i].toUpperCase().substring(d, d+1);
//				MdlL.append(sb.replace(d,d+1,a)).append(" ");
				
				MdlL.append(sp[i].substring(0,d)).append(sp[i].toUpperCase().charAt( d )).append(sp[i].substring(d+1)).append(" ");
			}else {
				MdlL.append(sp[i]).append(" ");
			}
		}
		
		System.out.println(MdlL.toString().trim());
		
	}

}
