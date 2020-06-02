package com.pzero.v1.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pzero.v1.business.exceptions.BusinessException;
import com.pzero.v1.business.validations.Validation;

public class ValidationTest {
	
	private static Validation vd;
	
	@BeforeClass
	public static void createValidationObject() {
		vd = new Validation();
	}
	
	@AfterClass
	public static void destroyValidationObject() {
		vd = null;
	}
	
	@Test
	public void testIsValidDate() {
		try {
			assertEquals(new SimpleDateFormat("MM-dd-yyyy").parse("10-10-2020"), vd.isValidDate("10-10-2020"));
//			fail("Expected an BusinessException to be thrown");
		} catch (ParseException | BusinessException e) {
			assertThat(e.getMessage(), is("Index: 0, Size: 0"));
		}
	}

	@Test
	public void testIsValidName() {
		assertEquals(true, vd.isValidName("Revature"));
		assertFalse(vd.isValidName("Revature12"));
	}
	
	@Test
	public void testIsValidCity() {
		assertEquals(true, vd.isValidCity("Ney York"));
		assertFalse(vd.isValidCity("c1t1one"));
	}

	@Test
	public void testIsValidPassword() {
		assertTrue(vd.isValidPassword("Pwd.0123"));
//		assertTrue(vd.isValidPassword("Passw0123"));
		assertFalse(vd.isValidPassword("passWoooo"));
	}

	@Test
	public void testIsValidContact() {
		assertEquals(true, vd.isValidContact("1234567890"));
	}

	@Test
	public void testIsValidSSN() {
		assertTrue(vd.isValidSSN("123456789"));
	}

	@Test
	public void testIsValidEmail() {
		assertEquals(true, vd.isValidEmail("example@example.com"));
	}
	
	@Test
	public void testIsValidBalance() {
		try {
			assertEquals(100.99d, vd.isValidBalance(100.98), 0.00);
		} catch (BusinessException e) {
			assertThat(e.getMessage(), is("Index: 0, Size: 0"));
		}
	}
	
	@Test
	public void testIsValidUserId() {
		assertEquals(true, vd.isValidUserId("AA01LL101020"));
	}
	
	@Test
	public void testIsValidIntId() {
		assertEquals(true, vd.isValidIntId(123456));
	}
	
	@Test
	public void testIsValidAccountNumber() {
		assertEquals(true, vd.isValidAccountNumber("200512636492"));
	}

}
