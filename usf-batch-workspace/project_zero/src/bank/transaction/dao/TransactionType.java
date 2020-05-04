package bank.transaction.dao;

public abstract class TransactionType {
	
	private int t_id;
	private String date;
	private double amount;
	private int fk_origin_account_id; // identifies account user or transaction
	

}
