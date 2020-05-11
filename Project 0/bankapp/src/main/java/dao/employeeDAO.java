package dao;

import java.util.List;

import BankException.bankException;
import bankapp.NewCustomerTemplate;

public interface employeeDAO 
{
	public NewCustomerTemplate getCustomerbySSN(String ssn) throws bankException;//check
	public void approveApp(String ssn) throws bankException;
	public void rejectApp(String ssn) throws bankException;
	public List<NewCustomerTemplate> getAllCustomers() throws bankException;//check
	public List<NewCustomerTemplate> getAllPendingApps() throws bankException;//check
}