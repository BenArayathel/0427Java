package com.bank.model;

public class Employee {

	private String username;
	private String password;
	private String employeeId;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Employee(String username, String password, String employeeId) {
		super();
		this.username = username;
		this.password = password;
		this.employeeId = employeeId;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", employeeId=" + employeeId + "]";
	}
	
}