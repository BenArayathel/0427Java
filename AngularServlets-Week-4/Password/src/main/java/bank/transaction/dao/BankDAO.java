package bank.transaction.dao;

import java.util.List;

//import not.used.Account;
//import not.used.Customer;
import user.cust.account.models.User;

public interface BankDAO {

	// user register
	// user login: becomes a Customer
	// customer apply: returns an Account ?
	// -----------------------------------------------

	public boolean createUser(User user);
	
	public List<User> getAllUsers();
	
	public List<User> getAllUsers_needingAuth();
	
	// getAllUsers_withAuth()
	public List<User> getAllUsers_withAuth();

	public int login(User user); // used to be void and redirect
	
	//public boolean userRegistrationToBecomeCustomer(User user, String email);
	
	public boolean userRegistrationToBecomeCustomer(User user);// step 1 of 2: apply
	
	//public boolean employeeRejectOrApprove_userRegistrationToBecomeCustomer(User user, String email);	// step 2 of 2: approve check

	public boolean customerApplicationForAccount(User user, String dob, double balance);			// step 1 of 2: apply
	
	public boolean employeeApprove_customerApplicationForAccount(User approvedUser);		// step 2 of 2: approve check
	
	public boolean employeeReject_customerApplicationForAccount(User approvedUser);
	
	public void updateBalance(User user);
	
	public void update_transfer(User user, double funds, User recipient);

}
