package not.used;

public class TransactionDeposit extends Old_Transaction {

	Account generic(Old_Transaction t, Account a) {
		
		TransactionDeposit td = new TransactionDeposit();
		td.addTransactionDeposit((TransactionDeposit) t);
		
		// TODO
		// add Transaction to Account
		return a;
	}
	
	

}
