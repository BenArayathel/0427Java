package com.example.encapsulation;

public class MainDriver {
	
//	public void addToAccount(Bank a, double money) {
//		//external logic 
//		a.account = money;
//	}

	public static void main(String[] args) {
		/*
		 * Practice of restricting access to resources within our class.
		 * 
		 * We achieve this through access modifiers within Java:
		 * 		public 
		 * 		private 
		 * 		default (implicit)
		 * 		protected
		 * 
		 * We set variables to be private and create public getters and setters for the variable. 
		 */
		
		Bank b = new Bank();
		System.out.println(b.getAccount());
		
		
		
		b.setAccount(1000);
		b.setAccount(5);
		System.out.println(b.getAccount());
		
		System.out.println(b.toString());
		System.out.println(b);

	}

}

