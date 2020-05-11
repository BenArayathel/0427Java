package not.used;

import java.util.ArrayList;
import java.util.List;

public class Old_Transaction {

	private int t_id;
	private String date;
	private double transactionValue; // $
	private int fk_origin_account_id; // foreign key user_id
//	private String transactionType;
	private List<TransactionDeposit> transactionDeposits = new ArrayList<>();
	private List<TransactionWithdraw> transactionWithdraws = new ArrayList<>();
	private List<TransferSent> transferSent = new ArrayList<>();
	private List<TransferReceived> transferReceived = new ArrayList<>();

	public Old_Transaction() {
		super();
	}

	public Old_Transaction(int t_id, String date, double transactionValue, int fk_origin_account_id) {
		// super();
		this.t_id = t_id;
		this.date = date;
		this.transactionValue = transactionValue;
		this.fk_origin_account_id = fk_origin_account_id;
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTransactionValue() {
		return transactionValue;
	}

	public void setTransactionValue(double transactionValue) {
		this.transactionValue = transactionValue;
	}

	public int getFk_origin_account_id() {
		return fk_origin_account_id;
	}

	public void setFk_origin_account_id(int fk_origin_account_id) {
		this.fk_origin_account_id = fk_origin_account_id;
	}

	// ------------------------------------------------------DEPOSITS

	public List<TransactionDeposit> getAllTransactionDeposits() {
		return transactionDeposits;
	}

	public void addTransactionDeposit(TransactionDeposit transactionDeposit) {
		this.transactionDeposits.add(transactionDeposit);
	}

	public void deleteTransactionDeposit(TransactionDeposit transactionDeposit) {
		this.transactionDeposits.remove(transactionDeposit);
	}
	// ------------------------------------------------------DEPOSITS
	// ------------------------------------------------------WITHDRAWS

	public List<TransactionWithdraw> getAllTransactionWithdraws() {
		return this.transactionWithdraws;
	}

	public void addTransactionWithdraw(TransactionWithdraw transactionWithdraw) {
		this.transactionWithdraws.add(transactionWithdraw);
	}

	public void deleteTransactionWithdraw(TransactionWithdraw transactionWithdraw) {
		this.transactionWithdraws.remove(transactionWithdraw);
	}

	// ------------------------------------------------------WITHDRAWS
	// ------------------------------------------------------TRANSFER-SENT
	public List<TransferSent> getAllTransferSent() {
		return transferSent;
	}

	public void addTransferSent(TransferSent transferSent) {
		this.transferSent.add(transferSent);
	}

	public void deleteTransferSent(TransferSent transferSent) {
		this.transferSent.remove(transferSent);
	}

	// ------------------------------------------------------TRANSFER-SENT
	// ------------------------------------------------------TRANSFER-RECEIVED
	public List<TransferReceived> getAllTransferReceived() {
		return transferReceived;
	}

	public void addTransferReceived(TransferReceived transferReceived) {
		this.transferReceived.add(transferReceived);
	}

	public void deleteTransferReceived(TransferReceived transferReceived) {
		this.transferReceived.remove(transferReceived);
	}
	// ------------------------------------------------------TRANSFER-RECEIVED

	@Override
	public String toString() {
		return "TransactionT_isSuper [t_id=" + t_id + ", date=" + date + ", transactionValue=" + transactionValue
				+ ", fk_origin_account_id=" + fk_origin_account_id + "]";
	}

}
