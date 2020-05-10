package my.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import exception.validations.BusinessException;
import exception.validations.Validation;

//import static org.junit.Assert.*;

//import org.junit.Test;

public class ValidationTest {
	
	Validation v = new Validation();

	@Test
	public void testIsValidEmail() {
		//fail("Not yet implemented");
		System.out.println("ran email test...");
		
		String email = "some@gmail";
		boolean expectedResult = false;
		boolean result = v.isValidEmail(email);
		assertEquals("Missing .com", expectedResult, result);
	}
	
	@Test
	public void test_9digit_IsValidContactPhone() {
		
		System.out.println("ran phone test...");
		//String phone
		long phone = 123123123;
		boolean expectedResult = false;
		boolean result = v.isValidContactPhone(phone);
		assertEquals("only 9 digits", expectedResult, result);
		
	}
	
	@Test
	public void test_11digit_IsValidContactPhone() {
		
		System.out.println("ran phone test...");
		//String phone
		long phone = 32345678901L;
		boolean expectedResult = false;
		boolean result = v.isValidContactPhone(phone);
		assertEquals("1 too many digits", expectedResult, result);
		
	}
	
	@Test
	public void testIsValid_ssn() {
		
		System.out.println("ran ssn test...");
		//String phone
		String ssn = "123.23.9999";
		boolean expectedResult = false;
		boolean result = v.isValid_ssn(ssn);
		assertEquals("format with periods", expectedResult, result);
		
	}
	
	@Test
	public void testIsValidDate() throws BusinessException, ParseException {
		
		System.out.println("ran date test...");
		//String phone
		String date = "23-12-2020";
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		//boolean expectedResult = false;
		Date result = v.isValidDate(date);
		Date expectedResult = sdf.parse(date);
		assertEquals("format with periods", result, expectedResult);
		
	}

}
