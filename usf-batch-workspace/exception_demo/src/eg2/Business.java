package eg2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Business {

	
	public void openFile(String fileName) throws FileNotFoundException, ArithmeticException,NullPointerException {
		FileInputStream fis=new FileInputStream(fileName);
	}
	
	public boolean isValidAge(int age) throws ArithmeticException{
		if(age<=0) {
			throw new ArithmeticException("Age cannot be -ve or zero");
		}
		if(age<20 || age >40) {
			throw new ArithmeticException("User's should be age between 20 to 40 only");
		}
		return true;
	}
}
