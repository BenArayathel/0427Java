package bank.transaction.dao;

import user.cust.account.models.Account;
import user.cust.account.models.Old_Transaction;

public class TransactionWithdraw extends Old_Transaction {

	
	Account generic(Old_Transaction t, Account a) {

		// td.addTransactionDeposit((TransactionDeposit) t);
		TransactionWithdraw tw = new TransactionWithdraw();
		tw.addTransactionWithdraw((TransactionWithdraw)t);
		
		// TODO
		// add Transaction to Account
		
		return null;
	}

}
