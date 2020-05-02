package com.mainportal;

public interface Bank {
	
	public double checkBalance(int c);
	public void transferFunds(int destination, int fromWhere,  double amount);
	public void receiveFunds();  //User object param

}
