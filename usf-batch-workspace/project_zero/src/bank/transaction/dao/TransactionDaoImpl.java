package bank.transaction.dao;

import java.util.List;

import user.cust.account.models.Account;
import user.cust.account.models.Customer;

public class TransactionDaoImpl implements TransactionDAO {

	// withdraw

	// deposit

	// receive transfer

	// send transfer

	@Override
	public boolean TransferTo(Customer customer, TransactionT_isSuper transfer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double viewBalance(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean withdraw(TransactionDaoImpl transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deposit(TransactionDaoImpl transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TransactionDaoImpl> viewAllCustTrans(Employee employee, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionDaoImpl> viewBankWideTrans(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> viewCustAccounts(Employee employee, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
