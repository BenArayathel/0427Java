package lists_and_serialization;

import java.util.List;

import lists_and_serialization.Soda;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class MainDriver {

	public static void main(String[] args) {
		String sodaFile = "./sodaFile.txt";
		
		List<Soda> sodaList = new ArrayList<>();
		sodaList.add(new Soda("pepsi", false, 450));
		sodaList.add(new Soda("coke", true, 460));
		sodaList.add(new Soda("cherry pepsi", false, 450));
		sodaList.add(new Soda("vanilla coke", false, 440));
		
		writeStuff(sodaFile, sodaList);
				
		Object myResult = readStuff(sodaFile);
		System.out.println(myResult.toString());

	
	}
	
	public static void writeStuff(String sodaFile, Object sodaList) {
		try (ObjectOutputStream myStream = new ObjectOutputStream(new FileOutputStream(sodaFile))) {
			myStream.writeObject(sodaList);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Object readStuff(String sodaFile) {
		Object readObject = null;
		Object s = null;
		
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(sodaFile))) {
			readObject = inputStream.readObject();
			s = (Object)readObject;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("All done printing the sodas");
//			System.out.println(s);
		}
	
		return s;
	}

}

/*
 * 1. create a class
 * 2. make a list or set of objects from it
 * 3. serialize them, write to a file
 */
