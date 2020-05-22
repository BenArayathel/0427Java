package com.bhank.webapp.model;

public class Transaction {

	private String id;
	private String fromAccountId;
	private String toAccountId;
	private double transferAmount;
	private boolean isDeposit;
	private boolean pending;
	private boolean accepted;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public String getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	public boolean isDeposit() {
		return isDeposit;
	}
	public void setDeposit(boolean isDeposit) {
		this.isDeposit = isDeposit;
	}
	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId
				+ ", transferAmount=" + transferAmount + ", isDeposit=" + isDeposit + ", pending=" + pending
				+ ", accepted=" + accepted + "]";
	}
	
	
}
