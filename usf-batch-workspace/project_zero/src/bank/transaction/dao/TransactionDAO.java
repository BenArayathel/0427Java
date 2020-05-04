package bank.transaction.dao;

import java.util.List;

import user.cust.account.models.Account;
import user.cust.account.models.Customer;

public interface TransactionDAO {

	// customer transfer to

	// customer transfer receive

	// customer view balance: Account

	// customer withdraw from Account

	// customer deposit

	// employee view all-customer's-transactions

	// employee view all transactions

	// employee view customer bank accounts
	// --------------------------------------------------------------

	// customer transfer to: Customer, Transfer
	public boolean TransferTo(Customer customer, TransactionType transfer);

	// customer transfer receive: Customer, Transfer
	// this might not be needed

	// customer view balance: Account
	public double viewBalance(Account account);

	// customer withdraw from Account
	public boolean withdraw(Transaction transaction);

	// customer deposit
	public boolean deposit(Transaction transaction);

	// employee view all-customer's-transactions
	public List<Transaction> viewAllCustTrans(Employee employee, Account account);

	// employee view all transactions: *!
	public List<Transaction> viewBankWideTrans(Employee employee);

	// employee view customer bank accounts: *!*
	public List<Account> viewCustAccounts(Employee employee, Customer customer);

}
