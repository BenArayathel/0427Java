package HW;

public class RegexValidationHW {
	
	public static void main(String[] args) {
		String s = "612-64-2357";	
		String groupNum = s.substring(4, 6);
		String serialNum = s.substring(7, 11);
		int areaNum = Integer.parseInt(s.substring(0, 3));
		
		if(s.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {	
			if(groupNum.matches("[0]{2}") || serialNum.matches("[0]{4}") || areaNum == 000 || areaNum == 666 || areaNum >= 900) {
				System.out.println("Invalid SSN");
			}else {
				System.out.println("Valid SSN");
			}						
		}else {
			System.out.println("Invalid SSN");
		}	
	}		
}


//RULES FOR SSN
//The first three digits called the area number. The area number cannot be 000, 666, or between 900 and 999.
//Digits four and five are called the group number and range from 01 to 99.
//The last four digits are serial numbers from 0001 to 9999.