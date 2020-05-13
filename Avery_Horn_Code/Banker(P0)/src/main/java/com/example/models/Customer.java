package com.example.models;

import java.util.HashMap;
import java.util.Objects;

import com.example.operations.ServicesImpl;
//model class
public class Customer {

	private String userid;
	private String name;
	private String password;
	private HashMap<String, Double> accounts = new HashMap<>();
	
	public Customer()
	{
		
	}
	
	
	public Customer(String userid, String name, String password, String accName, Double balance) {
		super();
		this.userid = userid;
		this.name = name;
		this.password = password;
		this.accounts.put(accName, balance);
	}



	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String username) {
		this.name = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void newAccount(String accName, Double balance) {
		this.accounts.put(accName,balance);
	}
	public Double getBalance (String accName)
	{
		return this.accounts.get(accName);	
	}
	public void setBalance(String accName, Double newBalance)
	{
		this.accounts.replace(accName, newBalance);
	}
	public void deletion(String acct)
	{
		this.accounts.remove(acct);
	}
	public HashMap<String, Double> getAccounts()
	{
		return this.accounts;
	}


	@Override
	public String toString() {
		return "Customer [userid=" + userid + ", name=" + name + ", password=" + password + ", accounts=" + accounts
				+ "]";
	}
}
