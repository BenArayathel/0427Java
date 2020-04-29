package exception_demo;

public class Business {
	
	public boolean isalidAge (int age) {
		// TODO Fix this to have appropriate logic
		return true;
	}
	
	public boolean isValidMobileNumber(String s) throws BusinessException {
		if (s.matches("\\+91-[0-9]{10}")) {
			return true;
		} else {
			throw new BusinessException("Entered mobile number "+s+" is invalid.");
		}
	}

}
