package dao;

import java.util.List;

import bankapp.UserTemplate;
import BankException.bankException;

public interface CustomerDAO 
{
	public void createCustomer() throws bankException;
	public List<UserTemplate> showCustomerDetails(String ssn) throws bankException;
	public void withdraw(double withdraw, String ssn) throws bankException;
	public void deposit(double deposit, String ssn) throws bankException;
	public void transfer(double transfer, String ssn, String ssn2) throws bankException;
}
