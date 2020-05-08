package bank.transaction.dao;

import user.cust.account.models.Account;
import user.cust.account.models.Transaction;

public class TransactionDeposit extends Transaction {

	Account generic(Transaction t, Account a) {
		
		TransactionDeposit td = new TransactionDeposit();
		td.addTransactionDeposit((TransactionDeposit) t);
		
		// TODO
		// add Transaction to Account
		return a;
	}
	
	

}
