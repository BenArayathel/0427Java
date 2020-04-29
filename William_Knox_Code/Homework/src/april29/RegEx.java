package april29;

public class RegEx {
	// regex for SSN, email, driver's license
	public static void main(String[] args) {
		// generate inputs
		String ssn1 = "123456789";
		String ssn2 = "234as6789";
		int ssn3 = 567891234;
		int ssn4 = 12345;
		String ssn5 = "345-67-8912";
		String email1 = "bob@gmail.com";
		String email2 = "bob@revature.com";
		String email3 = "pancakes";	
		String email4 = "bobby@asdf.com";
		String driver1 = "WDL123F12E5D";
		String driver2 = "CDL154DS65SDAFG654AS";
		String driver3 = "ADD56FS546S6";
		String driver4 = "ADL9sdf0a9fs";
		String driver5 = driver4.toUpperCase();
		
		// create arrays of my inputs
		String[] ssnArray = {ssn1, ssn2, ssn5};
		int[] ssnArray2 = {ssn3, ssn4};
		String[] emailArray = {email1, email2, email3, email4};
		String[] driverArray = {driver1, driver2, driver3, driver4, driver5};
		
		// create regex strings
		String ssnRegEx = "[0-9]{9}";
		String emailRegEx = ".*@.*\\.(com|org|edu|mil)"; // there's more domain types but this is just an example
		String revatureEmailRegEx = ".*(@revature\\.com)";
		String driverRegEx = "[ACDFGHIKLMNOPRSTUVW]DL[A-Z0-9]{9}";
		
		// example of how to fix input errors, i.e. remove dashes before checking validation
		for (int i = 0; i < ssnArray.length; i++) {
			if (ssnArray[i].contains("-")) {
				StringBuilder s = new StringBuilder();
				for (int j = 0; j < ssnArray[i].length(); j++) {
					if (ssnArray[i].charAt(j) != '-') {
						s.append(ssnArray[i].charAt(j));
					}
				}
				ssnArray[i] = s.toString();
			}
		}
		
		// test
		// social security number
		validator(ssnRegEx, ssnArray, "SSN");  // yes no yes
		validator(ssnRegEx, ssnArray2, "SSN"); // yes no
		
		// email address
		validator(emailRegEx, emailArray, "email address"); // yes yes no yes
		validator(revatureEmailRegEx, emailArray, "Revature email address"); // no yes no no
		
		// drivers license number
		validator(driverRegEx, driverArray, "driver's license number"); // yes no no no yes
		
		// all tests check out :)
	}
	
	public static void validator(String regex, String[] query, String type) {
		for (int i = 0; i < query.length; i++) {
			if (query[i].matches(regex))
				System.out.println(query[i] + " is a valid " + type + ".");
			else
				System.out.println(query[i] + " is an INVALID " + type + ".");
		}
	}
	
	public static void validator(String regex, int[] query, String type) {
		for (int i = 0; i < query.length; i++) {
			if ((query[i] + "").matches(regex))
				System.out.println(query[i] + " is a valid " + type + ".");
			else
				System.out.println(query[i] + " is an INVALID " + type + ".");
		}
	}
}
