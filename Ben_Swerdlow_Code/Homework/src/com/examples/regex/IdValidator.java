package com.examples.regex;

public class IdValidator {
	
//	public static boolean driversLicense(String license, String state) {
//		switch (state.toLowerCase()) {
//			case("michigan"):
//				String regex = "[A-Z]{1}";
//				break;
//			case("florida"):
//				String regex = "[A-Z]{1}";
//				break;
//			case("georgia"):
//				String regex = "[A-Z]{1}";
//				break;
//			case("dc"):
//				String regex = "[A-Z]{1}";
//				break;
//			default:
//				return false;
//				// Fix this to error message later
//		}
//		if (license.matches(regex)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	public static boolean SSN(String ssn) {
		if (ssn.replace(" ", "").replace("-", "").matches("[0-9]{9}")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		String[] testSsnList = {"889-27-5849", "123456789", "asdf12354546", "1230189273058912374058917", "1234---    --78453"};
		for (String ssn : testSsnList) {
			System.out.println(IdValidator.SSN(ssn));
		}
	}

}
