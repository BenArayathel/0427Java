package Flights.Serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import Serialization.Car;

public class WriteObjReadObj 
{
	static void writeObject(String myFileName, List<Flight> flightList) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(myFileName))){
			oos.writeObject(flightList); //serialization
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static List<Flight> readObject(String myFileName) {
		Object obj = null;
		List<Flight> c = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(myFileName))){
			
			obj = ois.readObject(); //de-serialization
			c = (List<Flight>)obj; //Casts an object from type Object to our type List<Flight>

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
//			finally {
//			System.out.println("I'm in finally!!!");
//		}
		return c; //Best practice to have only have return for a block
	}

}
