package exception.validations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import log.Log;

public class Validation {
	
	
	public boolean isValidEmail(String email) {
		
		// https://howtodoinjava.com/regex/java-regex-validate-email-address/
		//this.email.matches("^(.+)@(.+)$")
		if (email.matches("^(.+)@(.+).com$")) {
			return true;
		} else {
			Log.logger("Invalid Email format");
			return false;
		}
		
	}
	
	public boolean isValidContactPhone(long phoneNum) {
		
		// (contact + "").matches("[0-9]{10}")					// Dr. V's
		// phoneNum.matches("[0-9]{3}\\-[0-9]{3}\\-[0-9]{4}")	// my
		if((phoneNum + "").matches("[0-9]{10}")) {
			//System.out.println("\nValid Soc. Sec. !!!!");
			return true;
		}else {
			//System.out.println("Invalid Info");
			Log.logger("Invalid phone number format");
			return false;
		}
	}
	
	public boolean isValid_ssn(String soc) {
		
		if (soc.matches("[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}")) {
			return true;
		}
		
		return false;
	}
	
	// used to be static
	public Date isValidDate(String dob) throws BusinessException {
		Date d=null;
		// mine:  s.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")
		// dr's: dob.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")
		if(dob.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
											// dd/MM/yyyy  Dr.'s:  dd.MM.yyyy
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(false);
			try {
				d=sdf.parse(dob);
				return d;
			} catch (ParseException e) {
				throw new BusinessException("Entered date "+dob+" is invalid");
				//return null;
			}
		}else {
			throw new BusinessException("Entered date "+dob+" should be in (dd.MM.yyyy) format only");
		}
		
	}

}













