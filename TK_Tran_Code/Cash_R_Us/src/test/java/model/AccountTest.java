package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AccountTest {

	Account account = new Account("username", "password", "name", 10000, "type");

	@Before // runs before each @Test
	public void before() throws Exception {
		System.out.println("--[ Preparing Test ]--");
	}

	@After // runs after each @Test
	public void after() throws Exception {
		System.out.println("--[ Test Completed ]--\n");
	}

	@Test // tests when valid parameters are passed in
	public void AccountAssertEquals() {
		assertEquals("username", account.getUsername());
		assertEquals("password", account.getPassword());
		assertEquals("name", account.getName());
		assertEquals(100, account.getBalance(), 10000);
		assertEquals("type", account.getType());
	}

	@Test // tests when invalid params are passed in
	public void AccountAssertNotEquals() {
		assertNotEquals("fakeusername", account.getUsername());
		assertNotEquals("fakepassword", account.getPassword());
		assertNotEquals("fakename", account.getName());
		assertNotEquals(200, account.getBalance());
		assertNotEquals("hacker", account.getType());
	}
}
