package april29;

public class RegEx {

	public RegEx() {
	
	}
	
	public String phoneNumValid(String phoneNum) {		
		
		return phoneNum.matches("\\+1-[0-9]{3}-[0-9]{3}-[0-9]{4}") ? ("This is a valid phone number") : ("This is not a valid phone number");
	}
	
	public boolean ssnValid(String ssn) {
		
		if (ssn.matches("[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}")) {
			System.out.println("This is a valid SSN");
			return true;
		}
		else {
			System.out.println("This is not a valid SSN");
			return false;
		}

	}
	
	public boolean dLicenseValid(String dl) {
		
		if (dl.matches("[0-9]{9}")) {
			System.out.println("This is a valid South Carolina Driver's License ID #");
			return true;
		}
		else {
			System.out.println("This is not a valid South Carolina Driver's License ID #");
			return false;
		}
	}

}
