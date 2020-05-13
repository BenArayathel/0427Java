package dao;

import java.util.List;

import BankException.bankException;
import bankapp.UserTemplate;

public interface employeeDAO 
{
	public List<UserTemplate> getCustomerbySSN(String ssn) throws bankException;
	public void approveApp(String ssn) throws bankException;
	public void rejectApp(String ssn) throws bankException;
	public List<UserTemplate> getAllAccounts() throws bankException;
	public List<UserTemplate> getAllPendingApps() throws bankException;
	public void createEmployee() throws bankException;
}