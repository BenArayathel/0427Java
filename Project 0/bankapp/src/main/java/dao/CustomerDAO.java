package dao;

import java.util.List;

import bankapp.NewCustomerTemplate;
import BankException.bankException;

public interface CustomerDAO 
{
	public NewCustomerTemplate createCustomer(NewCustomerTemplate customer)throws bankException;
	public NewCustomerTemplate showCustomerDetails(String ssn)throws bankException;
}
