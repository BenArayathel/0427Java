package bank.transaction.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.utilities.DAOUtilites;
import exception.validations.BusinessException;
import exception.validations.Validation;
import log.Log;
import user.cust.account.controller.CustOptionsDirectory;
import user.cust.account.controller.UserOptionsDirectory;
import user.cust.account.controller.UserWelcome;
import user.cust.account.models.User;

public class BankDaoImpl implements BankDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;


	
	@Override
	public boolean createUser(User user) {

		Validation v = new Validation();
		
		try{

			
			conn = DAOUtilites.getConnection();

			//ps = conn.prepareStatement("INSERT INTO b_user(user_id, username, password, email, contact) values(1, ?, ?, ?, ?)");
			
			// stored procedure: "b_user_pr"
			String sql="{call b_user_pr(?,?,?,?,?)}";
			CallableStatement callableStatement=conn.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.setString(2, user.getUserName());
			callableStatement.setString(3, user.getPassword());
			callableStatement.setString(4, user.getEmail());
			callableStatement.setLong(5, user.getContactPhone());
			
			if (v.isValidEmail(user.getEmail())) {
				
				if (v.isValidContactPhone(user.getContactPhone())) {
					
					if (callableStatement.executeUpdate() != 0 ) {
						System.out.println("User created: log back in...");
						System.exit(0);
						return true;
					} else {
						System.out.println("Sorry something went wrong at the database");
						return false;
					}
					
				}
				

			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		return false;
	}
	
	
	

	@Override
	public List<User> getAllUsers() {

		List<User> userList = new ArrayList<>();
		
		try{

			conn = DAOUtilites.getConnection();
			ps = conn.prepareStatement("SELECT * FROM b_user");
			rs = ps.executeQuery();
			
			/**
			 * User(
			 * String userName, 
			 * long contactPhone, 
			 * String password, 
			 * String user_id, 
			 * String email, 
			 * Date dob, 
			 * String soc,
			 * Double balance,
			 * int a_access)
			 */

			while (rs.next()) {
				//System.out.println(rs.getString("userName"));
				userList.add(
						new User(rs.getString("userName"),
								rs.getLong("contact"),
								rs.getString("password"),
								rs.getString("user_id"),
								rs.getString("email"),
								rs.getDate("dob"),
								rs.getString("soc"),
								rs.getDouble("balance"),
								rs.getInt("a_access")
								
								));
			}
			//rs.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		return userList;
	}
	
	
	@Override
	public List<User> getAllUsers_needingAuth() {

		List<User> userList = new ArrayList<>();
		
		try{

			conn = DAOUtilites.getConnection();
			// old working
			//ps = conn.prepareStatement("select * from b_user where a_access is null");
			//new one below
			ps = conn.prepareStatement("select * from b_user where a_access is null and dob is not null");
			
			rs = ps.executeQuery();
			
			/**
			 * User(
			 * String userName, 
			 * long contactPhone, 
			 * String password, 
			 * String user_id, 
			 * String email, 
			 * Date dob, 
			 * String soc,
			 * Double balance,
			 * int a_access)
			 */
			while (rs.next()) {
				//System.out.println(rs.getString("userName"));
				userList.add(
						new User(rs.getString("userName"),
								rs.getLong("contact"),
								rs.getString("password"),
								rs.getString("user_id"),
								rs.getString("email"),
								rs.getDate("dob"),
								rs.getString("soc"),
								rs.getDouble("balance"),
								rs.getInt("a_access")
								
								));
			}
			//rs.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		return userList;
	}
	
	
	@Override
	public List<User> getAllUsers_withAuth() {

		List<User> userList = new ArrayList<>();
		
		try{

			conn = DAOUtilites.getConnection();
			ps = conn.prepareStatement("select * from b_user where a_access = 1 AND dob is not null");
			rs = ps.executeQuery();
			
			/**
			 * User(
			 * String userName, 
			 * long contactPhone, 
			 * String password, 
			 * String user_id, 
			 * String email, 
			 * Date dob, 
			 * String soc,
			 * Double balance,
			 * int a_access)
			 */
			while (rs.next()) {
				//System.out.println(rs.getString("userName"));
				userList.add(
						new User(rs.getString("userName"),
								rs.getLong("contact"),
								rs.getString("password"),
								rs.getString("user_id"),
								rs.getString("email"),
								rs.getDate("dob"),
								rs.getString("soc"),
								rs.getDouble("balance"),
								rs.getInt("a_access")
								
								));
			}
			//rs.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		return userList;
	}
	
	



	@Override
	public int login(User user) {

		CustOptionsDirectory co = new CustOptionsDirectory();
		
		try{

			conn = DAOUtilites.getConnection();
			ps = conn.prepareStatement("SELECT * FROM b_user WHERE USERNAME=? AND PASSWORD=?");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			
			//Log.logger("Value of execute Query " + ps.executeQuery());
			rs = ps.executeQuery();
			
			//System.out.println("this is rs : " + rs);
			
			while (rs.next()) {			

				/**
				 * User(
				 * 									String userName, 
				 * 									long contactPhone, 
				 * 									String password, 
				 * 									String user_id, 
				 * 									String email, 
				 * Date dob, 
				 * 									String soc,
				 * 									Double balance,
				 * 									int a_access)
				 */
				Log.logger("BankDAOImpl.login verified SOC: " + rs.getString("soc"));
				user.setSoc(rs.getString("soc"));
				user.setContactPhone(rs.getInt("contact"));
				user.setEmail(rs.getString("email"));
				user.setUser_id(rs.getString("user_id"));
				user.setA_access(rs.getInt("a_access"));
				user.setBalance(rs.getDouble("balance"));
				user.setDob(rs.getDate("dob"));

				Log.logger("user_id in Login: " + user.getUser_id());
				
			} 
			if(user.getSoc() != null) {					// soc: your a customer
				//return true;							
				// userName, password, user_id
				Log.logger("Customer privileges:");
				//co.select(user);
				return 1;
			}
			
			else if(user.getUser_id() != null) {		// no soc: not a customer
				//return false;
				Log.logger("User privileges:");
				UserOptionsDirectory uo = new UserOptionsDirectory();		
				//uo.userOptionsDir(user);
				return 2;
			}
			else {
				Log.logger("Invalid");
				Log.logger("Please check credentials..");
				Log.logger("You may need to register if you have not");
				Log.logger("Redirecting back to Login...\n\n");
				UserWelcome uw = new UserWelcome();
				//uw.greetUser();
				return 3;
			}
			
				
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		return 0;
	}
	
	

	@Override
	public boolean userRegistrationToBecomeCustomer(User user) {
		
		Validation v = new Validation();

		
		// new java.sql.Date(trainee.getDob().getTime()
		try{
			String s = user.getSoc();

			// "[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}"
			//s.matches("[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}"
					
			if (v.isValid_ssn(user.getSoc())) {
				
				conn = DAOUtilites.getConnection();
				ps = conn.prepareStatement("UPDATE b_user SET soc=? WHERE user_id=?");
				ps.setString(1, user.getSoc());
				ps.setString(2, user.getUser_id());
				System.out.println("user id in DAO: " + user.getUser_id());
				//ps.setString(2, user.getEmail());
				
				// instead of redirect to get user_id...
				//CustOptionsDirectory c = new CustOptionsDirectory();
				//c.select(user);
				// ... it is easiest to refresh vs query again right here
				
				System.out.println("Successful application submission.");
				System.out.println("\nApproval will take place as soon as possible.");
				//System.out.println("Application For Customer Received @ db");
				System.out.println("Thanks for applying");
				
			}else {
				Log.logger("Sorry that is not a valid format");
				UserOptionsDirectory uo = new UserOptionsDirectory();
				uo.userOptionsDir(user);
			}

			
			if (ps.executeUpdate() != 0) {
				System.exit(0);
				return true;
			} else {
				return false;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		
		
		return false;
	}
	
	


	@Override
	public boolean customerApplicationForAccount(User user, String dob, double balance) {
		

		Validation v = new Validation();
		
		
		try{
			
			if (v.isValidDate(dob) != null) {
				
				//int myInt = 1;
				user.setDob(v.isValidDate(dob));
				conn = DAOUtilites.getConnection();
										// update b_user set a_access=1 where user_id='BUTE123TE5';
				ps = conn.prepareStatement("UPDATE b_user SET balance=?, dob=? WHERE user_id=?");
				

				Log.logger("user_id at backend" + user.getUser_id());
				ps.setDouble(1, balance);
				// dr. V's:  new java.sql.Date(trainee.getDob().getTime())
				ps.setDate(2, new java.sql.Date(user.getDob().getTime()));
				ps.setString(3, user.getUser_id());
				
				Log.logger("Application For Account Received @ db");
				Log.logger("A Bank Employee will make a determination as soon as possible.");
				Log.logger("Thanks for applying");
				
				if(ps.execute()) {
					return true;
				}
			}

			
		}
		catch(SQLException | BusinessException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		return false;
	}
	
	@Override
	public boolean employeeApprove_customerApplicationForAccount(User approvedUser){
		
		// UPDATE THE DATABASE
		
		try{
			int myInt = 1;
			conn = DAOUtilites.getConnection();
									// update b_user set a_access=1 where user_id='BUTE123TE5';
			ps = conn.prepareStatement("UPDATE b_user SET a_access=? WHERE user_id=?");
			// hard-coding 1 as the update
			// which does approve user for account
			Log.logger("user_id at backend" + approvedUser.getUser_id());
			ps.setInt(1, myInt);
			ps.setString(2, approvedUser.getUser_id());
			
			if(ps.execute()) {
				return true;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		return false;
	}
	
	
	// employeeReject_customerApplicationForAccount
	@Override
	public boolean employeeReject_customerApplicationForAccount(User deniedUser){
		
		Log.logger("Yes running empReject... ...");
		
		try{
			int myInt = 1;
			conn = DAOUtilites.getConnection();

			ps = conn.prepareStatement("DELETE from b_user WHERE user_id=?");
			Log.logger("user_id at backend" + deniedUser.getUser_id());
			ps.setString(1, deniedUser.getUser_id());
			
			if(ps.execute()) {
				return true;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		return false;
	}
	
	
	
	@Override
	public void updateBalance(User user) {
		
		try{

			conn = DAOUtilites.getConnection();
									// update b_user set a_access=1 where user_id='BUTE123TE5';
			ps = conn.prepareStatement("UPDATE b_user SET balance=? WHERE user_id=?");
			// hard-coding 1 as the update
			// which does approve user for account
			Log.logger("user_id at backend" + user.getUser_id());
			ps.setDouble(1, user.getBalance());
			ps.setString(2, user.getUser_id());
			
			if(ps.execute()) {
				//return true;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		//return false;
		
	}
	
	
	/**
	 * -- from test to new
	 *	update b_user set balance = (balance - 500) where user_id = 'BUTE123TE5';
	 *
	 *	update b_user set balance = (balance + 500) where user_id = 'BUNE123NE90';
	 */
	@Override
	public void update_transfer(User user, double funds, User recipient) {
		
		try{

			conn = DAOUtilites.getConnection();

			
			// update 1
			ps = conn.prepareStatement("update b_user set balance = (balance - ?) where user_id = ?");
			Log.logger("user_id at backend" + user.getUser_id());
			ps.setDouble(1, funds);
			ps.setString(2, user.getUser_id());
			if(ps.execute()) {
				
				//return true;
			}
			user.setBalance((user.getBalance() - funds)); // correct balance locally

			// update 2			
			ps = conn.prepareStatement("update b_user set balance = (balance + ?) where user_id = ?");
			ps.setDouble(1, funds);
			ps.setString(2, recipient.getUser_id());
			if(ps.execute()) {
				//return true;
			}
	

			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		//return false;
		
	}



	private void closeResources() {
		
		try {
			if (rs != null && !rs.isClosed()) {
				//System.out.println("Closed result set...");
				rs.close();
			}
		} catch (Exception e) {
			//System.out.println("Could not close result set !");
			e.printStackTrace();
		}
		
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			//System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (conn != null) {
				conn.close();
				//System.out.println("Closing down connection...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}






}
























