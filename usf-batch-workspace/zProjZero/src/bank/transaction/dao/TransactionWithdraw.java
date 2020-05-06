package bank.transaction.dao;

import user.cust.account.models.Account;
import user.cust.account.models.Transaction;

public class TransactionWithdraw extends Transaction {

	
	Account generic(Transaction t, Account a) {

		// td.addTransactionDeposit((TransactionDeposit) t);
		TransactionWithdraw tw = new TransactionWithdraw();
		tw.addTransactionWithdraw((TransactionWithdraw)t);
		
		// TODO
		// add Transaction to Account
		
		return null;
	}

}
