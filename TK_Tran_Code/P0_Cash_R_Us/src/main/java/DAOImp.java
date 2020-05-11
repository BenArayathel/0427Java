import dbutil.OracleConnection;
import model.Account;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
	Persistence (Database) Layer Concrete Class
	Implements methods for communication with DB.
 */
public class DAOImp implements DAO {

	final static Logger log = Logger.getLogger(DAOImp.class);

	@Override // Creates a new account in DB
	public void createAccount(Account account) {
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
			log.warn("model.Account " + account.getUsername() + " already exists in the database.");
		}
	}

	@Override // Updates existing account in DB
	public void updateAccount(Account account) {
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
			e.printStackTrace();
		}
	}

	@Override // Returns all accounts
	public List<Account> getAllAccounts() {
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
			e.printStackTrace();
		}
		return accountList; // Spits out the now-populated ArrayList
	}

	@Override // Returns a single account by username
	public Account getAccount(String username) {
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
			e.printStackTrace();
		}
		return account;
	}

//	@Override
//	public void deposit(String account, String amount) throws BusinessException {
//		while (!service.isValidAmount(amount)) {
//			amount = sc.nextLine();
//		}
//		double depositAmount = Double.parseDouble(amount);
//
//		model.Account dummyAccount = dao.getAccount(account);
//		double dummyBalance = dummyAccount.getBalance();
//		dummyBalance += depositAmount;
//		dummyAccount.setBalance(dummyBalance);
//		dao.updateAccount(dummyAccount);
//		log.info("Balance: $" + dummyAccount.getBalance());
//	}

//	@Override
//	public void withdraw(String account, String amount) throws BusinessException {
//		while (!service.isValidAmount(amount)) {
//			amount = sc.nextLine();
//		}
//		double withdrawAmount = Double.parseDouble(amount);
//
//		model.Account dummyAccount = dao.getAccount(account);
//		double dummyBalance = dummyAccount.getBalance();
//		if ((dummyBalance - withdrawAmount) < 0) {
//			log.warn("Cannot withdraw more than $" + dummyBalance + ".");
//		} else {
//			dummyBalance -= withdrawAmount;
//		}
//		dummyAccount.setBalance(dummyBalance);
//		dao.updateAccount(dummyAccount);
//		log.info("Balance: $" + dummyAccount.getBalance());
//	}
}
