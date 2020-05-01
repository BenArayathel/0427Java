package com.example.serialization;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EagleMain {
	
	public static void main(String[] args) {
		
		String fileName = "./myEagle.txt";
		
		Eagle eagle = new Eagle("wide", "Golden", "Baldy");
		
		writeObject(fileName, eagle);
		Eagle eagle2 = readObject(fileName);
		System.out.println(eagle2);
	}

	private static Eagle readObject(String fileName) {
		
		Object obj = null;
		Eagle lastEagle = null;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			
			obj = ois.readObject(); // de-serialize
			lastEagle = (Eagle)obj; // cast Object to Eagle
			
		}
		catch (EOFException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// typically close resources
		}
		
		return lastEagle;
	}

	private static void writeObject(String fileName, Eagle eagle) {
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
