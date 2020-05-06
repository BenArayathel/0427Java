package bank.transaction.dao;

import java.util.ArrayList;
import java.util.List;

import user.cust.account.controller.AcctOptionsDirectory;
import user.cust.account.controller.CustOptionsDirectory;
import user.cust.account.controller.UserOptions;
import user.cust.account.models.Account;
import user.cust.account.models.Customer;
import user.cust.account.models.User;

public class BankDaoImpl implements BankDAO {

	List<Employee> employee = new ArrayList<>();
	List<User> user = new ArrayList<>(); // arrayList
	
	@Override
	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("db Creates user.");
		return true;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer login(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean userRegistrationToBecomeCustomer(User user, String email) {
		
		System.out.println("Successful application submission.");
		System.out.println("\nA Bank Employee will make a dertermination as soon as possible.");
		System.out.println("Application For Customer Received @ db");
		System.out.println("Thanks for applying");
		
		// TODO ...PERSIST TO DB
		// FORWARD DATA TO EMPLOYEE FRONT END ?
		// FOR NOW, DO THIS ...
		employeeRejectOrApprove_userRegistrationToBecomeCustomer(user, email);
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







}
