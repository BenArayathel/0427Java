package com.example.encapsulation;

public class Bank {
	
	public double account; //No longer have direct access to this variable
	
	public Bank() {
		this.account = 0;
	}

	public double getAccount() {//Create a public method, which allows me to return the value
		return account;
	}

	public void setAccount(double account) {
		
		//implement my own logic 
		
//		if(account >10) {
//			System.out.println("Invalid amount");
//		}else {
//			this.account = account; 
//		}
		this.account = account;
		
		
	}

	//Modifying the toString
	@Override
	public String toString() {
		return "Bank [My account has " + account + "]";
	}
	
	

}
