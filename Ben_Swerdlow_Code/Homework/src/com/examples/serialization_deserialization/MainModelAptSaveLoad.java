package com.examples.serialization_deserialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainModelAptSaveLoad {

	public static void main(String[] args) {
		
		Apartment modelAptPrototype = new Apartment(1400.0, 5, true, 0.0, "0000", "The Best Apartments Ever");
		String filename = "./modelAptPrototype.txt";
		
		try {
			saveApartment(modelAptPrototype, filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: File "+filename+" not found. Please try again.");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: Could not write the Apartment at "+modelAptPrototype.getAddress()+" to file "+filename
					+". Please try again.");
			System.exit(0);
		}
		
		Apartment modelApt01 = loadApartment(filename);
		
		if (modelApt01.equals(null)) {
			System.out.println("ERROR: Could not load "+filename+" as an apartment. Please try a different file.");
		} else {
			System.out.println(modelApt01);	
		}

	}

	private static void saveApartment(Apartment modelAptPrototype, String filename) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
		oos.writeObject(modelAptPrototype);
		oos.close();
	}

	private static Apartment loadApartment(String filename) {
		// Set up the nulls
		Object obj = null;
		Apartment apartment = null;
		// Try to get the object cast
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
			obj = ois.readObject();
			apartment = (Apartment) obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// If it can't cast, will return null
		return apartment;
	}

}
