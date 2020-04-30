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
		
		Parrot p1 = new Parrot("Buddy", "yellow");
		
		writeObject(filename, p1);
		
		Parrot p2 = readObject(filename);
		
		System.out.println(p2);
	}

	private static Parrot readObject(String filename) {
		Object obj = null;
		// set Parrot p to null so it will return null if it cannot cast obj to Parrot
		Parrot p = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			obj = ois.readObject();// de-serialization
			p = (Parrot) obj;// the above line must read an Object, but we want a Parrot object, so we downcast it
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// finally will still run even if try contained a return statement!
			System.out.println("I'm in finally!!!!");
		}
		// If it doesn't work, returns null
		return p;
	}

	private static void writeObject(String filename, Parrot p1) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
			oos.writeObject(p1); // serialization; ta-da!
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
