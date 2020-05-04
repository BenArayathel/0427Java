package user.cust.account.models;

public class User {

	private String userName;
	private String password;

	private int user_id; // after db generates id
	private String email; // register to be customer

	// CONSTRUCTOR FOR BEFORE DB
	public User(String userName, String password) {
		// super();
		this.userName = userName;
		this.password = password;
	}

	// CONSTRUCTOR FOR AFTER DB
	public User(String userName, String password, int user_id) {
		// super();
		this.userName = userName;
		this.password = password;
		this.user_id = user_id;
	}

//	// CONSTRUCTOR FOR AFTER registration to BE a Customer ?? ?? ?? ??
//	public User(String userName, String password, int user_id) {
//		// super();
//		this.userName = userName;
//		this.password = password;
//		this.user_id = user_id;
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", user_id=" + user_id + ", email=" + email
				+ "]";
	}

}
