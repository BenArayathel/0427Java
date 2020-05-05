package date_time.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/* Formatting Keys
    dd - date = dd-05 / d-5, 10
    MM - month = MM-04 / M-4 MMM-Jan MMMM - January
    yy - year = yy-20 / yyyy-2020
    hh - 12 hr format
    HH - 24 hr format
    mm - minutes
    ss - seconds
    a - AM/PM
    z/Z - z is timezone, Z is GMT
*/

public class Formatting {
    public static void main(String[] args) {

        // Instantiating a Date object.
        Date d = new Date();
        System.out.println("Default format: " + d); // Defaults to current system time.

        // Customizing our own date-time format.
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yy hh:mm:ss HH:mm:ss z Z a");
        System.out.println("Custom format: " + sdf.format(d)); // format() takes Date obj and formats, returning as String.

    }
}