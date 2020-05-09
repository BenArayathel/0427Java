package user.cust.account.models;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private User user;

	private List<Account> account = new ArrayList<>();

//	private String name;
//	private String address;
//	private String city;
//	private String state;
//	private String zip;

	// super(userName, password, user_id);

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

}