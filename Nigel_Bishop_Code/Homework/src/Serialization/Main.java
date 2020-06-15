package Serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		
		String myFile = "./myCars.txt";
		
		Car c1 = new Car("Ford","Focus","Black");
		
		
		writeObject(myFile, c1);
		Car c2 = readObject(myFile);
		c2.drive();
		c2.stop();
		System.out.println(c2); 
								
		

	}


	private static void writeObject(String myFileName, Car c1) 
	{
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(myFileName))){
			oos.writeObject(c1); //serialization
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Car readObject(String myFileName) 
	{
		Object obj = null;
		Car c = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(myFileName))){
			
			obj = ois.readObject(); //de-serialization
			c = (Car)obj; //Casts an object from type Object to our type Car
			
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
		return c; 
	}


}
