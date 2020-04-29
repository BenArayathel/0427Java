package com.examples.regex;

public class IdValidator {
	
	public static boolean isDriversLicense(String license, String state) throws IllegalArgumentException {
		String regex = "";
		// source: https://ntsi.com/drivers-license-format/
		switch (state.toLowerCase()) {
			case("michigan"):
				regex = "[A-Z]{1}([0-9]{10}$|[0-9]{12}$)";
				break;
			case("florida"):
				regex = "^[A-Z]{1}[0-9]{12}$";
				break;
			case("georgia"):
				regex = "^[0-9]{7,9}$";
				break;
			default:
				throw new IllegalArgumentException("The state "+state+" has yet to be added to the system or does not exist.");
		}
		if (license.matches(regex)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isSSN(String ssn) {
		if (ssn.replace(" ", "").replace("-", "").matches("[0-9]{9}")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		String[] testSsnList = {"889-27-5849", "123456789", "asdf12354546", "1230189273058912374058917", "1234---    --78453"};
		for (String ssn : testSsnList) {
			if (IdValidator.isSSN(ssn)) {
				System.out.println(ssn+" is a valid SSN.");
			} else {
				System.out.println(ssn+" is not a valid SSN");
			}
		}
		
		String[][] testDriversLicenseList = {
				{"B1234567890", "Michigan"},
				{"Z123456789012", "Michigan"},
				{"ZZZSDFQWE18928341", "Michigan"},
				{"F678901234567", "Florida"},
				{"FloridaDriversLicense123", "Florida"},
				{"1234567", "Georgia"},
				{"12345678", "Georgia"},
				{"123456789", "Georgia"},
				{"1234567890", "Georgia"},
				{"1234567", "Delaware"},
				{"invalid", "invalid"}
			};
		
		for (int i=0; i<testDriversLicenseList.length; i++) {
			try {
				if (IdValidator.isDriversLicense(testDriversLicenseList[i][0], testDriversLicenseList[i][1])) {
					System.out.println(testDriversLicenseList[i][0]
							+" is a valid driver's license in the state of "
							+testDriversLicenseList[i][1]);
				} else {
					System.out.println(testDriversLicenseList[i][0]
							+" is an invalid driver's license in the state of "
							+testDriversLicenseList[i][1]);
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e);
			}
			
		}
	}

}
