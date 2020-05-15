package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.application.bank.dao.impl.UserDaoImpl;
import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;

public class UserDaoImplTest {
	
	User user = new User("1", "Bob Smith", "bob@email.com", "7869876543", "bobby", "customer");
	public UserDaoImpl uDI = new UserDaoImpl();
	
	@Test
	public void insertUserTest() {
		try {
			assertEquals("Bob Smith", uDI.insertUser(user).getName());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteUserTest() {
		try {
			assertEquals(0, uDI.deleteUser("bob@email.com"));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void selectAllUsersTest() {
		try {
			assertNotNull(uDI.selectAllUsers());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	

}
