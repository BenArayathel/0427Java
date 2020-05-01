package com.example.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainDriver {
	
	public static void main(String[] args) {
		String filename = "./myParrot.txt";
		
		Parrot p1 = new Parrot("Buddy", "Yellow");
		
		writeObject(filename, p1);
		Parrot p2 = readObject(filename);
		p2.fly();
		System.out.println(p2); //We use the transient keyword on the name of the parrot 
								//So the name will not be persisted, so we can't access it. 
	}

	private static Parrot readObject(String filename) {
		Object obj = null;
		Parrot p = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
			
			obj = ois.readObject(); //de-serialization
			p = (Parrot)obj; //Casts an object from type Object to our type Parrot
//			return p;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			System.out.println("I'm in finally!!!");
//			return null;
		}
		return p; //Best practice to have only have return for a block
	}

	private static void writeObject(String filename, Parrot p1) {

		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
			oos.writeObject(p1); //serialization
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
