package regex;

public class MobileNumberValidation {
    public static void main(String[] args) {

        // Validates phone number
        String phone = "+91-1234567890";
        if (phone.matches("\\+91-[0-9]{10}")) {     // Must escape +; it has a special RegEx meaning
            System.out.println("Valid phone number: " + phone);
        } else {
            System.out.println("Invalid phone number!");
        }

        // Validates social security number
        String social = "123-45-6789";
        if (social.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
            System.out.println("Valid social security number: " + social);
        } else {
            System.out.println("Invalid social security number!");
        }

        // Validates driver's license
        String license = "T123-456-78-900-0";
        if (license.matches("[A-Z]{1}[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{3}-[0-9]{1}")) {
            System.out.println("Valid driver's license: " + license);
        } else {
            System.out.println("Invalid driver's license!");
        }
    }
}

/*
    Homework:
        Validate SSN [X]
        Validate driver's license [X]
        
    Review other RegEx symbols.
*/