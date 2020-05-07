package bank.transaction.dao;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.utilities.DAOUtilites;
import user.cust.account.controller.AcctOptionsDirectory;
import user.cust.account.controller.CustOptionsDirectory;
import user.cust.account.controller.UserOptions;
import user.cust.account.models.Account;
import user.cust.account.models.Customer;
import user.cust.account.models.User;

public class BankDaoImpl implements BankDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	//private static List<Employee> employee = new ArrayList<>();
	//private static List<User> userList = new ArrayList<>(); // arrayList
	//private static String url = "jdbc:oracle:thin:@database-1.ctmojn75tg7f.us-east-2.rds.amazonaws.com:1521:orcl";
	//private static String username = "mybasic";
	//private static String password = "34uy34uy";
	
	@Override
	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		
		try{

			/**
			 * INSERT INTO b_user(user_id, username, password) values(2, 'test1', 'test1');
			 * 
			 * TODO:
			 * AUTO INCREMENT ON ID'S ------------------------------------------------>
			 */
			conn = DAOUtilites.getConnection();
			//ps = conn.prepareStatement("INSERT INTO b_user(user_id, username, password) values(12, '"+ user.getUserName() +"', '"+ user.getPassword() +"')");
			ps = conn.prepareStatement("INSERT INTO b_user(user_id, username, password) values(4, ?, ?)");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			
			if (ps.executeUpdate() != 0) {
				System.out.println("db Creates user.");
				return true;
			} else {
				System.out.println("Sorry something went wrong");
				return false;
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
		return true;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		System.out.println("getAll ran\n");
		List<User> userList = new ArrayList<>();
		
		// old try with resources
		// Connection conn = DriverManager.getConnection(url, username, password))
		try{
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM b_user");
//			ResultSet rs = ps.executeQuery();
			conn = DAOUtilites.getConnection();
			ps = conn.prepareStatement("SELECT * FROM b_user");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("userName"));
				userList.add(
						new User(rs.getString("userName"), 
								rs.getString("password"),
								rs.getInt("user_id"),
								rs.getString("email")));
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
	public boolean login(User user) {
		
		try{
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM b_user");
//			ResultSet rs = ps.executeQuery();
			conn = DAOUtilites.getConnection();
			ps = conn.prepareStatement("SELECT * FROM b_user WHERE USERNAME=? AND PASSWORD=?");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			
			return rs.next();
				
			
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
	public boolean userRegistrationToBecomeCustomer(User user) {
		
		System.out.println("Successful application submission.");
		System.out.println("\nA Bank Employee will make a dertermination as soon as possible.");
		//System.out.println("Application For Customer Received @ db");
		System.out.println("Thanks for applying");
		
		
		try{

			conn = DAOUtilites.getConnection();
			ps = conn.prepareStatement("UPDATE b_user SET EMAIL=? WHERE USERNAME=?");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUserName());
			
			if (ps.executeUpdate() != 0) {
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
		employeeRejectOrApprove_userRegistrationToBecomeCustomer(user, user.getEmail());
		return true;
	}
	
	@Override
	public boolean employeeRejectOrApprove_userRegistrationToBecomeCustomer(User user, String email) {
		
		System.out.println("\nAutomation Emp @ db: verifying...");
		System.out.println("Email as: " + email);
		
		// TODO ... READ FROM DB
		// CODE LIKE THIS BELONGS UP FRONT
		if (email != null && email.length() > 3) { // mimicking emp Auth...
			// SHOULD I PUT LOGIC IN THE USER.setEMAIL() ??
			user.setEmail(email);
			System.out.println("\nApproved as a Customer");
			//System.out.println("Thanks for applying");
			System.out.println("Redirect to Customer Options\n");
			
			// there technically would be an user_id here if the Database had generated this.
			Customer customer = new Customer(user.getUserName(), user.getPassword(), user.getUser_id(), user.getEmail());
			CustOptionsDirectory co = new CustOptionsDirectory();
			co.select(customer);
			return true;
		}
		else {
			System.out.println("Something went wrong...");
			System.out.println("Please try again later...");
			UserOptions uo = new UserOptions();
			uo.select(user);
		}
		return false;
	}

	@Override
	public boolean customerApplicationForAccount(Customer customer, double balance) {
		
		System.out.println("Application For Account Received @ db");
		System.out.println("A Bank Employee will make a dertermination as soon as possible.");
		System.out.println("Thanks for applying");
		
		// TODO ...  PERSIST TO DB
		// FORWARD TO DATA TO EMPLOYEE FRONT END USER INTERFACE FOR WORKER AUTH
		// FOR NOW, DO THIS ...
		// SENDING CUSTOMER TO NEXT METHOD 
		employeeRejectOrApprove_customerApplicationForAccount(customer, balance);
		return true;
	}
	
	@Override
	public boolean employeeRejectOrApprove_customerApplicationForAccount(Customer customer, double balance){
		
		// TODO ...  READ FROM DB
		// FORWARD TO DATA TO EMPLOYEE FRONT END USER INTERFACE FOR WORKER AUTH
		// or actually listen for call from Employee front-end
		// FOR NOW, DO THIS ...
		System.out.println("\nAutomation Emp @ db: verifying...");
		System.out.println("Verifying Address:" + customer.getAddress().toString());
		System.out.println("Verifying Funds...\n");
		
		// TODO ... READ FROM DB
		// CODE LIKE THIS BELONGS UP FRONT
		if (customer.getAddress() != null && balance >= 0) { // mimicking emp Auth...
			// I will PUT LOGIC -> in the front-end
			
			// String userName, String password, int user_id, String email, int cust_id, String name, String phone, String address,
			//String city, String state, String zip, int acct_id, double balance
			Account a = new Account(customer.getUserName(), customer.getPassword(), customer.getUser_id(), customer.getEmail(),
					customer.getCust_id(), customer.getName(), customer.getPhone(), customer.getAddress(),
					customer.getCity(), customer.getState(), customer.getZip(), balance);
			System.out.println("Approved for Account");
			System.out.println("Thanks for applying");
			System.out.println("\nRedirect to Account Options ...");
			AcctOptionsDirectory ad = new AcctOptionsDirectory();
			ad.select(a);
		}
		else {
			System.out.println("Sorry, something went wrong");
		}
		return false;
	}



	private void closeResources() {
		// TODO Auto-generated method stub
		
		try {
			if (rs != null && !rs.isClosed()) {
				System.out.println("Closed result set...");
				rs.close();
			}
		} catch (Exception e) {
			System.out.println("Could not close result set !");
			e.printStackTrace();
		}
		
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Closing down connection...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



}
























