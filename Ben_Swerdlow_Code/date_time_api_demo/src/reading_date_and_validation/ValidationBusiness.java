package reading_date_and_validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.RuntimeErrorException;

public class ValidationBusiness {
	
	public boolean isValidDate(String s) throws RuntimeException {
		boolean b = false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date d = sdf.parse(s); // parse takes String object and returns date object
			System.out.println("Date object is "+d);
			b = true;
		} catch (ParseException e) {
			// if parser fails
			throw new RuntimeException("Date should be in dd-MM-yyyy format only");
		}
		
		return b;
	}

}
