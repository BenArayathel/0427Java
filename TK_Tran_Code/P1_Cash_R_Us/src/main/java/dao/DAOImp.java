package dao;

import dbutil.OracleConnection;
import exception.BusinessException;
import model.Account;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
	Persistence (Database) Layer Concrete Class
	Implements methods for communication with DB.
 */
public class DAOImp implements DAO {

	static final Logger log = Logger.getLogger(DAOImp.class);

//	Service service = new Service();
//	DAOImp dao = new DAOImp();

	protected String error = "Internal error, please contact SYSADMIN.";

	// Creates a new account in DB
	@Override
	public void createAccount(Account account) throws BusinessException {
		try (Connection conn = OracleConnection.getConn()) {
			String sql = "{CALL CREATEACCOUNT(?, ?, ?, ?, ?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, account.getUsername());
			cs.setString(2, account.getPassword());
			cs.setString(3, account.getName());
			cs.setDouble(4, account.getBalance());
			cs.setString(5, account.getType());
			cs.execute();
		} catch (ClassNotFoundException | SQLException e) {
			log.warn("Account " + account.getUsername() + " already exists!");
			JOptionPane.showMessageDialog(null, "Account already exists!");
		}
	}

	// Updates existing account in DB
	@Override
	public void updateAccount(Account account) throws BusinessException {
		try (Connection conn = OracleConnection.getConn()) {
			String sql = "UPDATE bank SET username=?, password=?, name=?, balance=?, type=? WHERE username='" + account.getUsername() + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());
			ps.setString(3, account.getName());
			ps.setDouble(4, account.getBalance());
			ps.setString(5, account.getType());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.error(error);
		}
	}

	// Returns all accounts
	@Override
	public List<Account> getAllAccounts() throws BusinessException {
		List<Account> accountList = new ArrayList<>(); // Creates an ArrayList to be spit out
		try (Connection conn = OracleConnection.getConn()) {
			String sql = "SELECT * FROM bank";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) { // While ResultSet contains tokens..
				Account account = new Account(); // ..create a single object from those returned values..
				account.setUsername(rs.getString("username")); // ..extract each value from DB and assign to object
				account.setPassword(rs.getString("password"));
				account.setName(rs.getString("name"));
				account.setBalance(rs.getDouble("balance"));
				account.setType(rs.getString("type"));
				accountList.add(account); // ..continually add objects into ArrayList
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(error);
		}
		return accountList; // Spits out the now-populated ArrayList
	}

	// Returns a single account by username
	@Override
	public Account getAccount(String username) throws BusinessException {
		Account account = new Account();
		try (Connection conn = OracleConnection.getConn()) {
			String sql = "SELECT * FROM bank WHERE username = '" + username + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setName(rs.getString("name"));
				account.setBalance(rs.getDouble("balance"));
				account.setType(rs.getString("type"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(error);
		}
		return account;
	}

//	@Override
//	public void deposit(String account, double amount) throws BusinessException {
//		Account dummyAccount = dao.getAccount(account);
//		double dummyBalance = dummyAccount.getBalance();
//		dummyBalance += amount;
//		dummyAccount.setBalance(dummyBalance);
//		dao.updateAccount(dummyAccount);
//		log.info("Balance: $" + dummyAccount.getBalance());
//	}
//
//	@Override
//	public void withdraw(String account, String amount) throws BusinessException {
//		double withdrawAmount = Double.parseDouble(amount);
//
//		Account dummyAccount = dao.getAccount(account);
//		double dummyBalance = dummyAccount.getBalance();
//		if ((dummyBalance - withdrawAmount) < 0) {
//			log.error("Cannot withdraw more than $" + dummyBalance + ".");
//		} else {
//			dummyBalance -= withdrawAmount;
//		}
//		dummyAccount.setBalance(dummyBalance);
//		dao.updateAccount(dummyAccount);
//		log.info("Balance: $" + dummyAccount.getBalance());
//	}
}
