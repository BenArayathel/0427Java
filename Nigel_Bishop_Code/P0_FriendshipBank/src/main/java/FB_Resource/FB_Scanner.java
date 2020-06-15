package FB_Resource;

import java.util.Scanner;

public class FB_Scanner 
{
	static Scanner input = new Scanner(System.in);
	
	public static int UserInput_Int() {
		int userInput;
		userInput = input.nextInt();
	//	input.close();
		return userInput;
	}
	
	public static String UserInput_LongString() {
		String userInput;
		userInput = input.next();
		userInput += input.nextLine();
		
	//	input.close();
		return userInput;
	}
	
	public static String UserInput_String() {
		String userInput;
		userInput = input.next();
	//	input.close();
		return userInput;
	}
	
}
