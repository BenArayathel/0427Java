package eg2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.exception.BusinessException;
import com.exception.InvalidAgeException;

public class Business {

	
	public void openFile(String fileName) throws FileNotFoundException, ArithmeticException,NullPointerException {
		FileInputStream fis=new FileInputStream(fileName);
	}
	
	public boolean isValidAge(int age) throws InvalidAgeException {
		if(age<=0) {
			throw new InvalidAgeException("Age cannot be -ve or zero");
		}
		if(age<20 || age >40) {
			throw new InvalidAgeException("User's should be age between 20 to 40 only");
		}
		return true;
	}
	
	public boolean isValidMobileNumber(String s)throws BusinessException {
	
		if(!s.matches("\\+91-[0-9]{10}")) {
		throw new BusinessException("Entered mobile number "+s+" is invalid");
		}
		return true;
	}
}
