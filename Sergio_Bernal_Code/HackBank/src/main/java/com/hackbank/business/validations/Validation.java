package com.hackbank.business.validations;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hackbank.business.exceptions.BusinessException;

public class Validation {
	
	public Date isValidDate(String date) throws BusinessException{
		
		if(date.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			sdf.setLenient(false);
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				throw new BusinessException("Entered date "+date+" is invalid!");
			}
		}else {
			throw new BusinessException("Date format isn't valid please digit in MM-dd-yyyy");
		}
		
	}
	
	public boolean isValidName(String name){
		return name.matches("(?:[a-zA-Z]+)");
	}
	
	public boolean isValidPassword(String pwd) {
		/** 
		 * Start of group
		 * (?=.*\d)			#   must contains one digit from 0-9
		 * (?=.*[a-z])		#   must contains one lowercase characters
		 * (?=.*[A-Z])		#   must contains one uppercase characters
		 * (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
		 * .				#   match anything with previous condition checking
		 * {6,10}			#   length at least 6 characters and maximum of 10
		*/
		return pwd.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,10})");
	}
	
	public double isValidBalance(double balance) throws BusinessException{
		if (balance >= 0) {
			balance += 0.01d;
			DecimalFormat decim = new DecimalFormat("0.00");
			return Double.parseDouble(decim.format(balance));
		}else {
			throw new BusinessException("We can not accept negative balance!");
		}
	}
	
	public boolean isValidContact(long contact){
		String parseContact = contact+"";
		return parseContact.matches("[0-9]{10}");
	}
	
	public boolean isValidSSN(long ssn){
		String parseContact = ssn+"";
		return parseContact.matches("[0-9]{9}");
	}
	
	public boolean isValidEmail(String email){
		String regexp = "[a-zA-Z]{1}[\\w]+@([\\w]+.)[\\w]+";
		return email.matches(regexp);
	}
	
	public boolean isValidUserId(String id) {
		String regexp = "([A-Z]{2}[0-9]+)([A-Z]{2})([0-9]+)";
		return id.matches(regexp);
	}
	
	public boolean isValidIntId(int id) {
		String parseId = id+"";
		return parseId.matches("([0-9]+)");
	}
	
}
