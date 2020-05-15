package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.application.bank.dao.impl.UserDaoImpl;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;
import com.application.bank.services.impl.UserServiceImpl;

public class UserServiceImplTest {

	User user = new User("1", "Jack Bauer", "jack@email.com", "9087678765", "24", "customer");
	UserServiceImpl uSI = new UserServiceImpl();
	
	@Test
	public void isValidNameTest() {
		assertEquals(true, uSI.isValidName("Clark Kent"));
		assertEquals(false, uSI.isValidName("King William the 3rd"));
		assertEquals(false, uSI.isValidName("Mary-Sue Johnson"));
		
	}
	@Test
	public void isValidPhoneNumber() {
		assertEquals(true, uSI.isValidPhoneNumber("1234568765"));
		assertEquals(false, uSI.isValidPhoneNumber("764-322-3456"));
		assertEquals(false, uSI.isValidPhoneNumber("2345678"));
	}
	@Test
	public void isValidDepositTest() {
		assertEquals(true, uSI.isValidDeposit("100.00"));
		assertEquals(false, uSI.isValidDeposit("Eleven dollars"));
		assertEquals(false, uSI.isValidDeposit("$100.00"));
	}
	@Test
	public void isEmployee() {
		assertEquals(false, uSI.isEmployee(user)); 
	}
	@Test
	public void passwordEncryptionTest() {
		assertEquals("djBjNOGKfIJZR", uSI.passwordEncryption("GalaxyQuest19"));
		assertEquals("hBEKghbJOSRQQ", uSI.passwordEncryption("CloudCity8900"));
	}
	
	

}
