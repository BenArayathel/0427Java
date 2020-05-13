package com.example.interfaces;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import com.example.models.Customer;
import com.example.models.Employee;

public interface DAO {

	public Customer createUser(Customer c, String name, String accname, Double balance);
	public void updateAcct(Double balance, String acct);
	public void newAcct(Customer c, String name);
	public List<Customer> viewAllAccts();
	public HashMap<String, Double> viewAcct(String name);
	public void deleteAcct(String Acct);
	public Customer validate (String user, String pass);
	public void approveAccount (Customer c, String accName);
	public void denyAccount (String accname);
	public List<HashMap<String, Double>> transactionLog();
	public Employee EmployeeLogin (String email, String pass);
	public Employee createEmployee (Employee e);
	public void Transferrer (String accName, Double amount);
}
