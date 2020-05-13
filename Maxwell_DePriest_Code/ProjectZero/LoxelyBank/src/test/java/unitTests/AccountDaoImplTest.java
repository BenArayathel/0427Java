package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.application.bank.dao.impl.AccountDaoImpl;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.Account;

public class AccountDaoImplTest {
	Account a = new Account("2", "2394859", "2940593", "1000.00", "500.00", "true", "bob@email.com");
	AccountDaoImpl aDI = new AccountDaoImpl();
	
	@Test
	public void selectAccountByEmailTest() {
		try {
			assertNotNull(aDI.selectAccountByEmail("leia@email.com"));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void selectAllAccountsTest() {
		try {
			assertNotNull(aDI.selectAllAccounts());
		} catch (BusinessException e) {
			
			e.printStackTrace();
		}
	}
	@Test
	public void selectAccountByColumnNameTest() {
		try {
			Account aTest = aDI.selectAccountByColumnName("email", "han@email.com");
			assertTrue(aTest instanceof Account);
			assertEquals("han@email.com", aTest.getEmail());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
