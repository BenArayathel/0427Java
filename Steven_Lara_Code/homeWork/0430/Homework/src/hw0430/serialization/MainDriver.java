package hw0430.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainDriver {
	
	public static void main(String[] args) {
		
		Basketball b1 = new Basketball();
		Basketball b2 = new Basketball("Wilson", "Black");
		String fileName = "basketball.ser";
		
		writeObject(fileName, b1);
		writeObject(fileName, b2); // overwrites 'b1' that was previously in the file
		Basketball b3 = readObject(fileName);
		System.out.println(b3);
		System.out.println("\nBrand of the basketball is transient therefore value is " + b3.getBrand());
		System.out.println("\nBrand of the basketball stored in 'b2' is " + b2.getBrand());
		
	}
	
	private static void writeObject(String fileName, Basketball b) {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(b);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	private static Basketball readObject(String fileName) {
		Basketball b = null;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
			b = (Basketball) ois.readObject();
			ois.close();
			return b;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
