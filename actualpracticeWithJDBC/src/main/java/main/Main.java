package main;

import java.util.List;

import dao.StuffDaoImpl;
import model.Stuff;



public class Main {

	public static void main(String[] args) {
		
		//this is my object to run my overrided methods to connect to my sql db
		StuffDaoImpl myImpl = new StuffDaoImpl();
		

		
		//this triggers my Create from CRUD, making a new stuff
//		Stuff thing1 = new Stuff(11, "walls", false, 4);
		//this is the command to insert, if you try again it will throw error for duplicate
//		myImpl.insertStuff(thing1);
		
		Stuff thing1 = new Stuff();
		thing1.setStuff_name("face");
		thing1.setStuff_iscool(false);
		thing1.setStuff_amount(10);
		
		//new command to create new 'stuff'
//		myImpl.insertStuff(thing1);
		
		
		//this deletes an item by it's name
//		myImpl.deleteStuff("bed");
//		myImpl.deleteStuff("paintings");
		
		//updating the amount of a thing that i have
//		myImpl.updateStuffAmount("books", 342);


		//this used my select all, the Read in CRUD
		List<Stuff> stuffList = myImpl.selectAllStuff();
		for (Stuff s: stuffList) {
			System.out.println(s);
		}
	}

}
