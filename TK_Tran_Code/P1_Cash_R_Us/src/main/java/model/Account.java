package model;

public class Account {

	private String username;
	private String password;
	private String name;
	private double balance;
	private String type;

	public Account() {
	}

	public Account(String username, String password, String name, double balance, String type) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.balance = balance;
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public String getType() {
		return type;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "{" +
				"Username='" + username + '\'' +
				", Password='" + password + '\'' +
				", Name='" + name + '\'' +
				", Balance=$" + balance +
				", Type='" + type + '\'' +
				'}';
	}
}
