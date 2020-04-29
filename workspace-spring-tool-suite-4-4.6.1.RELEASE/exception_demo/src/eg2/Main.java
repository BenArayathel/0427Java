package eg2;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {

		Business b = new Business();
		try {
			b.openFile("test.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		try {
		if(b.isValidAge(-1)) {
			System.out.println("Validated");
		}
		}catch(ArithmeticException e) {
			System.out.println(e.getMessage());
		}
	}
}
