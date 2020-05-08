package com.company.model;

public class TransactionType {
	
	private String TransactionType;
	private String Description;
	public String getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Override
	public String toString() {
		return "TransactionType [TransactionType=" + TransactionType + ", Description=" + Description + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionType other = (TransactionType) obj;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (TransactionType == null) {
			if (other.TransactionType != null)
				return false;
		} else if (!TransactionType.equals(other.TransactionType))
			return false;
		return true;
	}
	
	

}
