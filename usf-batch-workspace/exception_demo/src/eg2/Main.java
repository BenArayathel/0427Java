package eg2;

import java.io.FileNotFoundException;

import com.exception.BusinessException;
import com.exception.InvalidAgeException;

public class Main {

	public static void main(String[] args) {

		Business b = new Business();
		try {
			b.openFile("test.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}

		try {
			if (b.isValidAge(-20)) {
				System.out.println("Validated");
			}
		} catch (InvalidAgeException e) {
			System.out.println(e.getMessage());
		}
		try {
			if (b.isValidMobileNumber("+91-900123123")) {
				System.out.println("Mobile number validated");
			}
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

	}

}
