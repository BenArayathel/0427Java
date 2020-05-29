package com;

public class RobberMain {
	
	public static RobberDao rdao = new RobberDao();

	public static void main(String[] args) {

		// run the function to make some inserts
		// (could be used for default bank users)
		insertIntialValue();
		
		// print them all out...
		System.out.println(rdao.selectAll());
		
		// change one
		updateARobber("Billy", "Baseball Bat");
		
		// ...print them all out again...
		System.out.println(rdao.selectAll());
		
		// delete one
		Robber bobby = rdao.selectByName("Bobby");
		rdao.delete(bobby);
		
		// ...print them all out again...
		System.out.println(rdao.selectAll());
		
	}
	
	// a function to call the robberdao method to insert
	public static void insertIntialValue() {
		
		rdao.insert(new Robber(0, "Billy", "Blackjack"));
		rdao.insert(new Robber(0, "Bobby", "Blunderbuss"));
		rdao.insert(new Robber(0, "Byron", "Bagpipes"));
	}
	
	// my addition, update a robber's weapon, by name
	public static void updateARobber(String name, String newWeapon) {
		Robber billy = rdao.selectByName(name);
		billy.setWeapon(newWeapon);
		
		rdao.update(billy);
	}
		
}

