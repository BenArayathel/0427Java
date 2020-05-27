package e1;

public class Ex1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String value = "goldenvalueswith()[][],,,=";
		
//		String a = "{\"key\":\"" + value +"\",";
//		
//		String b = a.substring(0, a.length() - 1);
//		
//		String c = b.concat("}");
		
		String a = "{\"key\":\"" + value +"\",";
		
		a = a.replaceAll("[^a-zA-Z]", "");

		
		a = a.substring(0, a.length() - 1);
		
		a = a.concat("}");
		
		System.out.println(a);
//		System.out.println("this is b: " + b);
//		System.out.println("this is c: " + c);

	}

}
