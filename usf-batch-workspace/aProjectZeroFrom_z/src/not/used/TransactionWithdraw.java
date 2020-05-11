package not.used;

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
