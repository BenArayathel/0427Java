package com.examples.regex;

public class IdValidator {
	
	public static boolean driversLicense(String license) {
		if (license.matches("<REGEX HERE>")) {
			return true;
		} else {
			return false;	
		}
	}

}
