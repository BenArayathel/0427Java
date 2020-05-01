package com.examples.serialization_deserialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainModelAptSaveLoadList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7540188300706232018L;

	public static void main(String[] args) {
		List<Apartment> apartmentList = makeApartmentList();
		
		String filename = "./aparmentList.txt";
		
		try {
			saveApartmentList(apartmentList, filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: File "+filename+" not found. Please try again.");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: Could not write the Apartment at apartmentList to file "+filename
					+". Please try again.");
			System.exit(0);
		}
		
		List<Apartment> apartmentListLoaded = loadApartmentList(filename);
		
		for (Apartment apt : apartmentListLoaded) {
			System.out.println(apt);
		}

	}

	private static List<Apartment> loadApartmentList(String filename) {
		Object object = null;
		ArrayList<Object> objectList = new ArrayList<>();
//		Apartment apartment = new Apartment();
		ArrayList<Apartment> apartmentList = new ArrayList<>();
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
			object = ois.readObject();
			objectList = (ArrayList<Object>) object;
			for (Object obj : objectList) {
				apartmentList.add((Apartment) obj);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (apartmentList.isEmpty()) {
			return null;
		} else {
			return apartmentList;
		}
	}

	private static void saveApartmentList(List<Apartment> apartmentList, String filename) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
		oos.writeObject(apartmentList);
		oos.close();
	}

	private static List<Apartment> makeApartmentList() {
		ArrayList<Apartment> apartmentList = new ArrayList<>();
		
		apartmentList.add(new Apartment(1400.0, 5, true, 0.0, "1233", "The Best Apartments Ever"));
		apartmentList.add(new Apartment(1800000.0, 2, false, 500, "5675", "The Biggest Apartments Ever"));
		apartmentList.add(new Apartment(1.0, 1, true, 0.0, "1234", "The Smallest Apartments Ever"));
		apartmentList.add(new Apartment(800.0, 1, false, 1200, "3456", "The Best Studios Ever"));
		apartmentList.add(new Apartment(1000.0, 2, false, 800.0, "0987", "Meh Apartments"));
		apartmentList.add(new Apartment(1028.0, 3, true, 700.0, "1294", "A Group of Apartments"));
		apartmentList.add(new Apartment(938.70, 2, true, 750.0, "2344", "You Can Live Here"));
		apartmentList.add(new Apartment(2383.0, 5, true, 100000000, "$$$$", "The Most Expensive Apartments Ever"));
		
		return apartmentList;
	}

}
