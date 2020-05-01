package com.revature.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// Write and Read only one object
		String filename = "./televisions.txt";
		String listFilename = "./listTelevisions.txt";
		Television tv1 = new Television(101,"4K", "Sony", 5, 21.0);
		Television tv2 = null;
		
		writeFileStream(filename, tv1);
		tv2 = readFileStream(filename);
		System.out.println("My new Television...:)");
		System.out.println(tv2);
		System.out.println("----------------------");
		
		//Write and Read a list of objects
		List<Television> ltv = new ArrayList<>();
		ltv.add(new Television(101,"4K", "Sony", 5, 45.0));
		ltv.add(new Television(105,"4K", "Samsung", 4, 45.0));
		ltv.add(new Television(206,"UHD", "Samsung", 5, 55.0));
		ltv.add(new Television(709,"HD", "LG",3, 50.0));
		ltv.add(new Television(66,"PLASMA", "Sony", 5, 32.0));
		
		writeListStream(listFilename, ltv);
		
		List<Television> ltv2 = null;
		ltv2 = readListStream(listFilename);
		System.out.println("My list of Televisions...:)");
		for (Television tv: ltv2) {
			System.out.println(tv);
		}
	}

	private static Television readFileStream(String filename) {
		
		Television tv = null;
		
		try(ObjectInputStream bi = new ObjectInputStream(new FileInputStream(filename))){
			tv = (Television)bi.readObject();
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("The " + filename + " was not found...try again.");
		} catch (IOException e) {
			System.out.println("This file is not supported.");
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("We're unable to process your request now, try again later");
//			e.printStackTrace();
		}
		return tv;
	}

	private static void writeFileStream(String filename, Object obj) {
		try (ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(filename))){
			bw.writeObject((Television)obj);
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("We're unable to open this "+ filename + " try again later.");
//			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("We're unable to write this file, try again later");
//			e.printStackTrace();
		}
		
	}
	
	// Writing a list of objects
	private static void writeListStream(String filename, List<Television> ltv) {
		try (ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(filename))){
			bw.writeObject(ltv);
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("We're unable to open this "+ filename + " try again later.");
//			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("We're unable to write the list of TVs in a file, try again later");
//			e.printStackTrace();
		}
	}
	
	// reading the list of objects
	private static List<Television> readListStream(String filename) {
		ArrayList<Television> ltv = new ArrayList<>();	
		try (ObjectInputStream bi = new ObjectInputStream(new FileInputStream(filename))){
			List<Object> obj = (ArrayList<Object>) bi.readObject();
			for (Object ob : obj) {
				ltv.add((Television)ob);
			}
		} catch (FileNotFoundException e) {
			System.out.println("The " + filename + " was not found...try again.");
//			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("This file is not supported.");
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("We're unable to open the list from this file, try again later");
//			e.printStackTrace();
		}
		return ltv;
	}

}
