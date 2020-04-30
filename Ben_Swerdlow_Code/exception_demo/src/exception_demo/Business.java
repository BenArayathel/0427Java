package exception_demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Business {
	
	public void openFile(String fileName) throws FileNotFoundException, IOException {
		FileInputStream fis=new FileInputStream(fileName);
		fis.close();
	}
	
	public boolean isValidAge(int age) throws InvalidAgeException {
		if(age<=0) {
			throw new InvalidAgeException("Age cannot be negative or zero");
		}
		if(age<20 || age >40) {
			throw new InvalidAgeException("User's age should be between 20 and 40");
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
