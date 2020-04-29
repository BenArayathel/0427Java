package regex;

public class MobileNumberValidation {
    public static void main(String[] args) {

        // Validates phone number
        String phone = "+91-1234567890";
        if (phone.matches("\\+91-[0-9]{10}")) {                 // Must escape the + first
            System.out.println("Valid phone number");
        } else {
            System.out.println("Invalid phone number");
        }

        // Validates social security number
        String social = "123-45-6789";
        if (social.matches("")) {
            System.out.println("Valid social security number");
        } else {
            System.out.println("Invalid social security number");
        }

        // Validates driver's license
        String license = "";
        if (license.matches("")) {
            System.out.println("Valid driver's license");
        } else {
            System.out.println("Invalid driver's license");
        }
    }
}

/*
    Homework:
        Validate SSN
        Validate driving license
    Review other RegEx symbols.
*/