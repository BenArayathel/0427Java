package reading_date_and_validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidationBusiness {

	
	public boolean isValidDate(String s) throws Exception {
		boolean b = false;
		if (s.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(false);
			try {
				Date d = sdf.parse(s);// parse-> stakes String obj and returns dateobj
				//System.out.println("Date obj is " + d);
				b = true;
			} catch (ParseException e) {
				throw new Exception("Entered date does'nt exists in Calendar");
			}
			}else {
				throw new Exception("Date should be in dd-MM-yyyy format only");
			}
			return b;
		}

	}

