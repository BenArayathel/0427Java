package bank.transaction.dao;

import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated method stub
		System.out.println("Successful application submission.");
		System.out.println("A Bank Employee will make a dertermination as soon as possible.");
		System.out.println("Application For Customer Received @ db");
		System.out.println("Thanks for applying");
		// FORWARD PROMPT TO EMPLOYEE ?
		employeeRejectOrApprove_userRegistrationToBecomeCustomer(user, email);
		return true;
	}
	
	@Override
	public boolean employeeRejectOrApprove_userRegistrationToBecomeCustomer(User user, String email) {
		// TODO Auto-generated method stub
		System.out.println("Automation Emp @ db: verifying...");
		System.out.println("Email as: " + email);
		
		if (email != null && email.length() > 3) { // mimicking emp Auth...
			// SHOULD I PUT LOGIC IN THE USER.setEMAIL() ??
			user.setEmail(email);
			System.out.println("Approved as a Customer");
			System.out.println("Thanks for applying");
			System.out.println("Redirect to Customer Options");
			Customer customer = new Customer(user.getUserName(), user.getPassword(), user.getEmail());
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
	public boolean customerApplicationForAccount(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("Application For Account Received @ db");
		System.out.println("A Bank Employee will make a dertermination as soon as possible.");
		System.out.println("Thanks for applying");
		// FORWARD PROMPT TO EMPLOYEE ?
		return true;
	}
	
	@Override
	public boolean employeeRejectOrApprove_customerApplicationForAccount(){
		return false;
	}







}
