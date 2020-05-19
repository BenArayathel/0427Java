package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServiceTest {

	Service service = new Service();

	@Before // runs before each @Test
	public void before() throws Exception {
		System.out.println("--[ Preparing Test ]--");
	}

	@After // runs after each @Test
	public void after() throws Exception {
		System.out.println("--[ Test Completed ]--\n");
	}

//	@Test
//	public void testDisplayWelcome() {
//		String actual = "Hello!";
//		assertNotEquals("Welcome to Cash R' Us, where your money is ours!", actual);
//	}
//
//	@Test
//	public void testDisplayGoodbye() {
//		String actual = "Goodbye!";
//		assertNotEquals("Thanks for being a loyal Cash R' Us customer!", actual);
//	}
//
//	@Test
//	public void testDisplayLogOut() {
//		String actual = "Signing out!!";
//		assertNotEquals("Logging out, routing back to main portal.", actual);
//	}

	@Test // should validate usernames against RegEx "[a-zA-Z0-9]{3,15}"
	public void isValidUsername() {
		assertTrue(service.isValidUsername("tEsTuSeRnAmE"));
		assertTrue(service.isValidUsername("123456789012345"));
		assertFalse(service.isValidUsername("az")); // violates minimum 3 character rule
		assertFalse(service.isValidUsername("1234567890123456")); // violates maximum 15 characters rule
	}

	@Test // should validate passwords against RegEx "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}"
	public void isValidPassword() {
		assertTrue(service.isValidPassword("#BestBossEver55"));
		assertTrue(service.isValidPassword("*listentoyourMOM33"));
		assertFalse(service.isValidPassword("iamapassword123")); // violates minimum 1 special character rule
		assertFalse(service.isValidPassword("%PleaseLetMeIn")); // violates minimum 1 number rule
	}

	@Test // should validate amounts against RegEx "^\\d{1,5}"
	public void isValidAmount() {
		assertTrue(service.isValidAmount("12345"));
		assertTrue(service.isValidAmount("999"));
		assertFalse(service.isValidAmount("666666")); // violates maximum 5 digits rule
		assertFalse(service.isValidAmount("abcde")); // violates numerics-only rule
	}
}
