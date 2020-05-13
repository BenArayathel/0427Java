package com.example.interfaces;

import com.example.models.*;

public interface Services {

	public void deleteAccount(String acct, Customer c);
	public void newAccount(Customer c);
	public Customer updateAcc(Customer c, String acc, Double amount);
	public Customer transferBalance(Customer c, String acc1, String acc2, Double amount);
	public void newCustomer();
	public void newEmployee();
	public Customer validLogin(String user, String pass);
	public void employeeValid();
	public Customer transferTo(Customer c, String acc1, String acc2, Double amount);
}
