package date_time.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reading_Validation {
    public static void main(String[] args) {

        String s = "21-11-2000";

        // Class used to format (date > text) and parse (text > dates) dates.
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Sets the default format we want.

        // sdf.setLenient(false); // Sets leniency for user input format.

        boolean b = false;
        // Validation
        if (s.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) { // Restricts format using RegEx
            try {
                // Parsing
                Date d = sdf.parse(s); // parse() takes String obj and returns Date obj.
                System.out.println("Parsed from String to Date object: " + d);
                b = true; // b set to true here once we know everything is running fine.
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (b) { // If passes RegEx validation, then print corresponding message.
            System.out.println("Date validated.");
        } else {
            System.out.println("Invalid date.");
        }
        
    }
}