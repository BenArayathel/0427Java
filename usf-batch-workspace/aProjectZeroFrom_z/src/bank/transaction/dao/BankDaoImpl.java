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
import java.util.List;

import exception.BusinessException;

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

			
			conn = DAOUtilites.getConnection();

			//ps = conn.prepareStatement("INSERT INTO b_user(user_id, username, password, email, contact) values(1, ?, ?, ?, ?)");
			
			// stored procedure: "b_user_pr"
			String sql="{call b_user_pr(?,?,?,?,?)}";
			CallableStatement callableStatement=conn.prepareCall(sql);
			callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			callableStatement.setString(2, user.getUserName());
			callableStatement.setString(3, user.getPassword());
			callableStatement.setString(4, user.getEmail());
			callableStatement.setLong(5, user.getContact());
			
			if (callableStatement.executeUpdate() != 0) {
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
								rs.getString("email"),
								rs.getLong("contact"),
								rs.getString("user_id")
								
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
	public boolean login(User user) {
		User u = null;
		CustOptionsDirectory co = new CustOptionsDirectory();
		Customer c = null;
		
		try{
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM b_user");
//			ResultSet rs = ps.executeQuery();
			conn = DAOUtilites.getConnection();
			ps = conn.prepareStatement("SELECT * FROM b_user WHERE USERNAME=? AND PASSWORD=?");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			
			rs = ps.executeQuery();
			
			System.out.println("this is rs : " + rs);
			
			while (rs.next()) {
				u = new User(
						rs.getString("userName"), 
						rs.getString("password"),
						rs.getString("email"),
						rs.getLong("contact"),
						rs.getString("user_id")
						);
				System.out.println("Does logger have: " + rs.getString("soc"));
				user.setSoc(rs.getString("soc"));
				user.setContact(rs.getInt("contact"));
				user.setEmail(rs.getString("email"));
				user.setUser_id(rs.getString("user_id"));
				//System.out.println("user_id in Login: " + user.getUser_id());
				
			} 
			if(user.getSoc() != null) {
				//return true;							// soc: your a customer
				// userName, password, user_id
				c = new Customer(user.getUserName(), user.getPassword(), user.getUser_id());
				co.select(c);
			}
			
			else {
				//return false;
				UserOptions uo = new UserOptions();		// no soc: not a customer
				uo.seeOptions(user);
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
	
	
//	private String verifyUser_id() {
//		
//		//User u = new User();
//		
//		
//		return null;
//	}
	
	public boolean userRegistrationToBecomeCustomer(User user) {
		
		System.out.println("Successful application submission.");
		System.out.println("\nA Bank Employee will make a dertermination as soon as possible.");
		//System.out.println("Application For Customer Received @ db");
		System.out.println("Thanks for applying");
		
		// new java.sql.Date(trainee.getDob().getTime()
		try{
			String s = user.getSoc();

			if (s.matches("[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}")) {
				
				conn = DAOUtilites.getConnection();
				ps = conn.prepareStatement("UPDATE b_user SET soc=? WHERE user_id=?");
				ps.setString(1, user.getSoc());
				ps.setString(2, user.getUser_id());
				System.out.println("user id in DAO: " + user.getUser_id());
				//ps.setString(2, user.getEmail());
				
			}

			
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
		if(dob.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
											// dd/MM/yyyy  dd.MM.yyyy
			SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
			sdf.setLenient(false);
			try {
				d=sdf.parse(dob);
			} catch (ParseException e) {
				throw new BusinessException("Entered date "+dob+" is invalid");
			}
		}else {
			throw new BusinessException("Entered date "+dob+" should be in (dd.MM.yyyy) format only");
		}
		return d;
	}
	
	
//	@Override
//	public boolean employeeRejectOrApprove_userRegistrationToBecomeCustomer(User user, String email) {
//		
//		System.out.println("\nAutomation Emp @ db: verifying...");
//		System.out.println("Email as: " + email);
//		
//		// TODO ... READ FROM DB
//		// CODE LIKE THIS BELONGS UP FRONT
//		if (email != null && email.length() > 3) { // mimicking emp Auth...
//			// SHOULD I PUT LOGIC IN THE USER.setEMAIL() ??
//			user.setEmail(email);
//			System.out.println("\nApproved as a Customer");
//			//System.out.println("Thanks for applying");
//			System.out.println("Redirect to Customer Options\n");
//			
//			// there technically would be an user_id here if the Database had generated this.
//			Customer customer = new Customer(user.getUserName(), user.getPassword(), user.getUser_id(), user.getEmail());
//			CustOptionsDirectory co = new CustOptionsDirectory();
//			co.select(customer);
//			return true;
//		}
//		else {
//			System.out.println("Something went wrong...");
//			System.out.println("Please try again later...");
//			UserOptions uo = new UserOptions();
//			uo.seeOptions(user);
//		}
//		return false;
//	}

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
























