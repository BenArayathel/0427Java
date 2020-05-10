package bank.transaction.dao;

import java.util.List;

import user.cust.account.models.Account;
import user.cust.account.models.Customer;
import user.cust.account.models.User;

public interface BankDAO {

	// user register
	// user login: becomes a Customer
	// customer apply: returns an Account ?
	// -----------------------------------------------

	public boolean createUser(User user);
	
	public List<User> getAllUsers();

	public void login(User user);
	
	//public boolean userRegistrationToBecomeCustomer(User user, String email);
	
	public boolean userRegistrationToBecomeCustomer(User user);// step 1 of 2: apply
	
	//public boolean employeeRejectOrApprove_userRegistrationToBecomeCustomer(User user, String email);	// step 2 of 2: approve check

	public boolean customerApplicationForAccount(User user, String dob, double balance);			// step 1 of 2: apply
	
	public boolean employeeRejectOrApprove_customerApplicationForAccount(User approvedUser);		// step 2 of 2: approve check
	
	public void updateBalance(User user);

}
