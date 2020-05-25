package dao;

import dbutil.OracleConnection;
import model.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOImpTest {

	Account account = new Account();

	@Before // runs before each @Test
	public void before() throws Exception {
		System.out.println("--[ Preparing Test ]--");
	}

	@After // runs after each @Test
	public void after() throws Exception {
		System.out.println("--[ Test Completed ]--\n");
	}

	@Test(expected = SQLException.class) // expect an exception to be thrown
	public void createAccount() throws ClassNotFoundException, SQLException {
		Connection conn = OracleConnection.getConn();
		String sql = "{CALL CREATEACCOUNT(?, ?, ?, ?)}"; // should violate insufficient parameters
		CallableStatement cs = conn.prepareCall(sql);
		cs.setString(1, account.getUsername());
		cs.setString(2, account.getPassword());
		cs.setString(3, account.getName());
		cs.setDouble(4, account.getBalance());
		cs.setString(5, account.getType());
		cs.execute();
	}

	@Test(expected = SQLException.class) // expect an exception to be thrown
	public void getAllAccounts() throws ClassNotFoundException, SQLException {
		Connection conn = OracleConnection.getConn();
		String sql = "{CALLL CREATEACCOUNT(?, ?, ?, ?, ?)}"; // should violate PL/SQL syntax
		CallableStatement cs = conn.prepareCall(sql);
		cs.setString(1, account.getUsername());
		cs.setString(2, account.getPassword());
		cs.setString(3, account.getName());
		cs.setDouble(4, account.getBalance());
		cs.setString(5, account.getType());
		cs.execute();
	}

	@Test(expected = SQLException.class) // expect an exception to be thrown
	public void getAccount() throws ClassNotFoundException, SQLException {
		Connection conn = OracleConnection.getConn();
		String sql = "UPDATE bank SET username=?, password=?, name=?, balance=?, type=? WHERE username='" + account.getUsername() + "'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account.getUsername());
		ps.setString(2, account.getPassword());
		ps.setString(3, account.getName());
		ps.setDouble(4, account.getBalance());
		// should violate missing setter
		ps.executeUpdate();
	}

	@Test(expected = SQLException.class) // expect an exception to be thrown
	public void testMissingParam() throws ClassNotFoundException, SQLException {
		Connection conn = OracleConnection.getConn();
		String sql = "UPDATE bank SET username=?, password=?, name=, balance=?, type=? WHERE username='" + account.getUsername() + "'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account.getUsername());
		ps.setString(2, account.getPassword());
		ps.setString(3, account.getName());
		ps.setDouble(4, account.getBalance());
		ps.setString(5, account.getType());
		ps.executeUpdate();
	}

	@Test(expected = SQLException.class) // expect an exception to be thrown
	public void testMissingCallable() throws ClassNotFoundException, SQLException {
		Connection conn = OracleConnection.getConn();
		String sql = "{CALL CREATEACCOUNT(?, ?, ?, ?)}"; // should violates insufficient parameters
		CallableStatement cs = conn.prepareCall(sql);
		cs.setString(1, account.getUsername());
		cs.setString(2, account.getPassword());
		cs.setString(3, account.getName());
		cs.setDouble(4, account.getBalance());
		cs.setString(5, account.getType());
		cs.execute();
	}
}
