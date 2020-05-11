package bank.transaction.dao;

import java.util.List;

import not.used.Account;
import not.used.Customer;
import user.cust.account.models.User;

public interface TransactionDAO {
	
	
	public boolean createTransaction(User user, String trans);
	
	public boolean viewAllTransactions();
	
	public boolean viewCustTransactions(User user);
	
	// the stuff below I thought would exist here and maybe it should
	// ----------------------------------------------

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
//	public boolean TransferTo(Customer customer, TransactionT_isSuper transfer);

	// customer transfer receive: Customer, Transfer
	// this might not be needed

	// customer view balance: Account
//	public double viewBalance(Account account);

	// customer withdraw from Account
//	public boolean withdraw(TransactionDaoImpl transaction);

	// customer deposit
//	public boolean deposit(TransactionDaoImpl transaction);

	// employee view all-customer's-transactions
//	public List<TransactionDaoImpl> viewAllCustTrans(Employee employee, Account account);

	// employee view all transactions: *!
//	public List<TransactionDaoImpl> viewBankWideTrans(Employee employee);

	// employee view customer bank accounts: *!*
//	public List<Account> viewCustAccounts(Employee employee, Customer customer);

}
