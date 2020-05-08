package bank.transaction.dao;

import user.cust.account.models.Account;
import user.cust.account.models.Transaction;

public abstract class TransactionT_isSuper {
	
	//private static Transaction t = new Transaction();
	
	
	abstract Account generic(Transaction t, Account a);
	
	/**
	 * I was thinking to make this an Abstract Class with 1 Abstract method;
	 * however, I ran into non-castable situations
	 */

	
}
