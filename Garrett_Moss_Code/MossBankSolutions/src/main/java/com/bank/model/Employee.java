package com.bank.model;

public class Employee {

	private String username;
	private String password;
	private String name;
	private String address;
	private String employeeId;
	
	public Employee() {
		
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", name=" + name + ", address=" + address
				+ ", employeeId=" + employeeId + "]";
	}
	
}
