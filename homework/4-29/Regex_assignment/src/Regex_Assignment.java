
public class Regex_Assignment {

	public static void main(String[] args) {
		// test if a given social security number is acceptable
		
		String SSN = "891-71-1134";
		
		if (SSN.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
			System.out.println("That works");
		} else {
			System.out.println("That isn't the right format");
		}

	}

}
