package bank.transaction.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import exception.BusinessException;
import log.Log;
import connection.utilities.DAOUtilites;
import user.cust.account.controller.CustOptionsDirectory;
import user.cust.account.controller.UserOptionsDirectory;
import user.cust.account.models.Account;
import user.cust.account.models.Customer;
import user.cust.account.models.User;

public class BankDaoImpl implements BankDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;


	
	@Override
	public boolean createUser(User user) {

		
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
			
			if (isValidEmail(user.getEmail())) {
				
				if (isValidContactPhone(user.getContactPhone())) {
					
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
			

			
			//  insert User
			//rs.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		return false;
	}
	
	
	public boolean isValidEmail(String email) {
		
		// https://howtodoinjava.com/regex/java-regex-validate-email-address/
		//this.email.matches("^(.+)@(.+)$")
		if (email.matches("^(.+)@(.+)$")) {
			return true;
		} else {
			Log.logger("Invalid Email format");
			return false;
		}
		
	}
	
	public boolean isValidContactPhone(long phoneNum) {
		
		// (contact + "").matches("[0-9]{10}")					// Dr. V's
		// phoneNum.matches("[0-9]{3}\\-[0-9]{3}\\-[0-9]{4}")	// my
		if((phoneNum + "").matches("[0-9]{10}")) {
			//System.out.println("\nValid Soc. Sec. !!!!");
			return true;
		}else {
			//System.out.println("Invalid Info");
			Log.logger("Invalid phone number format");
			return false;
		}
	}
	
	

	@Override
	public List<User> getAllUsers() {

		//System.out.println("getAll ran\n");
		List<User> userList = new ArrayList<>();
		
		// old try with resources
		// Connection conn = DriverManager.getConnection(url, username, password))
		try{
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM b_user");
//			ResultSet rs = ps.executeQuery();
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
	public void login(User user) {

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
				co.select(user);
				//return true;
			}
			
			else if(user.getUser_id() != null) {		// no soc: not a customer
				//return false;
				Log.logger("User privileges:");
				UserOptionsDirectory uo = new UserOptionsDirectory();		
				uo.userOptionsDir(user);
				//return true;
			}
			else {
				Log.logger("Invalid");
				Log.logger("Please check credentials..");
				Log.logger("You may need to register if you have not");
				//return false;
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
	
	

	
	public boolean userRegistrationToBecomeCustomer(User user) {
		
		System.out.println("Successful application submission.");
		System.out.println("\nApproval will take place as soon as possible.");
		//System.out.println("Application For Customer Received @ db");
		System.out.println("Thanks for applying");
		
		// new java.sql.Date(trainee.getDob().getTime()
		try{
			String s = user.getSoc();

			// "[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}"
			if (s.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")) {
				
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
		
		
		// FORWARD DATA TO EMPLOYEE FRONT END ?
		// FOR NOW, DO THIS ...
		//employeeRejectOrApprove_userRegistrationToBecomeCustomer(user, user.getEmail());
		return false;
	}
	
	
	
	
	@Override
	public boolean userRegistrationToBecomeCustomer(User user, String dob) {
		
		System.out.println("Successful application submission.");
		System.out.println("\nA Bank Employee will make a dertermination as soon as possible.");
		//System.out.println("Application For Customer Received @ db");
		System.out.println("Thanks for applying");
		
		// new java.sql.Date(trainee.getDob().getTime()
		try{

			if (isValidDate(dob) != null) {
				
				user.setDob(isValidDate(dob));
				
				//java.util.Date utilStartDate = user.getDob();
				//java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
				conn = DAOUtilites.getConnection();
				ps = conn.prepareStatement("UPDATE b_user SET dob=? WHERE EMAIL=?");
				ps.setDate(1, new java.sql.Date(user.getDob().getTime()));
				//ps.setString(2, user.getUserName());
				ps.setString(2, user.getEmail());
				
			}

			
			if (ps.executeUpdate() != 0) {
				return true;
			} else {
				return false;
			}
			
		}
		catch(SQLException | BusinessException e) {
			e.printStackTrace();
		}
		finally {
			closeResources();
			
		}
		
		
		// FORWARD DATA TO EMPLOYEE FRONT END ?
		// FOR NOW, DO THIS ...
		//employeeRejectOrApprove_userRegistrationToBecomeCustomer(user, user.getEmail());
		return false;
	}
	
	
	public static Date isValidDate(String dob) throws BusinessException {
		Date d=null;
		// mine:  s.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}")
		// dr's: dob.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")
		if(dob.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
											// dd/MM/yyyy  Dr.'s:  dd.MM.yyyy
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			sdf.setLenient(false);
			try {
				d=sdf.parse(dob);
				return d;
			} catch (ParseException e) {
				throw new BusinessException("Entered date "+dob+" is invalid");
			}
		}else {
			throw new BusinessException("Entered date "+dob+" should be in (dd.MM.yyyy) format only");
		}
		//return d;
	}
	


	@Override
	public boolean customerApplicationForAccount(User user, String dob, double balance) {
		
		System.out.println("Application For Account Received @ db");
		System.out.println("A Bank Employee will make a determination as soon as possible.");
		System.out.println("Thanks for applying");
		
		
		
		try{
			if (isValidDate(dob) != null) {
				
				//int myInt = 1;
				user.setDob(isValidDate(dob));
				conn = DAOUtilites.getConnection();
										// update b_user set a_access=1 where user_id='BUTE123TE5';
				ps = conn.prepareStatement("UPDATE b_user SET balance=?, dob=? WHERE user_id=?");
				

				Log.logger("user_id at backend" + user.getUser_id());
				ps.setDouble(1, balance);
				// dr. V's:  new java.sql.Date(trainee.getDob().getTime())
				ps.setDate(2, new java.sql.Date(user.getDob().getTime()));
				ps.setString(3, user.getUser_id());
				
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
	public boolean employeeRejectOrApprove_customerApplicationForAccount(User approvedUser){
		
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























