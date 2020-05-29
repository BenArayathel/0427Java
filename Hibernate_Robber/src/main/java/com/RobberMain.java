package com;

public class RobberMain {
	
	public static RobberDao rdao = new RobberDao();

	public static void main(String[] args) {

		// run the function to make some inserts
		// (could be used for default bank users)
		insertIntialValue();
		
		// then print the results
		System.out.println(rdao.selectByName("Billy"));
		System.out.println(rdao.selectByName("Bobby"));
		System.out.println(rdao.selectByName("Byron"));
		
	}
	
	// a function to call the robberdao method to insert
	public static void insertIntialValue() {
		
		rdao.insert(new Robber(0, "Billy", "Blackjack"));
		rdao.insert(new Robber(0, "Bobby", "Blunderbuss"));
		rdao.insert(new Robber(0, "Byron", "Bagpipes"));
	}
		
}

