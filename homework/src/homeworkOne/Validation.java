package homeworkOne;

public class Validation {

	public static void main(String[] args) {
        String phone = "+91-1234567890";
        if (phone.matches("\\+91-[0-9]{10}")) {
            System.out.println("Valid phone number");
        } else {
            System.out.println("Invalid phone number");
        }
        /*
         * The above will validate whether or not
         * the string is a valid phone number
         */

        String social = "034-67-4356";
        if (social.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
            System.out.println("Valid social security number");
        } else {
            System.out.println("Invalid social security number");
        }
        /*
         * The above will validate whether or not
         * the string is a valid SSN
         */
        
        String license = "A123-1234-1234";
        if (license.matches("[A-Z]{1}[0-9]{3}-[0-9]{4}-[0-9]{4}")) {
            System.out.println("Valid driver's license");
        } else {
            System.out.println("Invalid driver's license");
        }
        /*
         * The above will validate whether or not
         * the string is a valid Illinois Driver's
         * License
         */
    }
}
