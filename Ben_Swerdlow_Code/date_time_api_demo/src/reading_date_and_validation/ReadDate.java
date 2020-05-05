package reading_date_and_validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadDate {

	public static void main(String[] args) {

		String s = "99-99-22";// dd-MM-yyyy
		Boolean b = false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date d = sdf.parse(s); // parse takes String object and returns date object
			System.out.println("Date object is "+d);
			b = true;
		} catch (ParseException e) {
			// if parser fails
			e.printStackTrace();
		}
		
		if (b/*s.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}"*/) {
			System.out.println("Validated date");
		} else {
			System.out.println("Invalid date");
		}
		
		b = false;
		
		// however, parser is lenient. Eg. if month is above 12, will return monthInteger % 12 as the month
		// you need to specify leniency
		
		sdf.setLenient(false);
		try {
			Date d = sdf.parse(s); // parse takes String object and returns date object
			System.out.println("Date object is "+d);
			b = true;
		} catch (ParseException e) {
			// if parser fails
			e.printStackTrace();
		}
		
		if (b/*s.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}"*/) {
			System.out.println("Validated date");
		} else {
			System.out.println("Invalid date");
		}
		
	}

}
