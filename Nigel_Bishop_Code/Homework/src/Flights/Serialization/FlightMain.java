package Flights.Serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Serialization.Car;

public class FlightMain {

	public static void main(String[] args) {
		
		List<Flight> flightList=new ArrayList<>();
		flightList.add(new Flight(330,"BW1010","JFK","MIA",5.4f,110.49));
		flightList.add(new Flight(110,"JB6565","BWI","MIA",1.5f,280.49));
		flightList.add(new Flight(200,"AA1120","JFK","BWI",2.4f,467.29));
		flightList.add(new Flight(143,"SP1010","OLD","NYA",3.7f,250.52));
		flightList.add(new Flight(857,"SW8710","JFK","MIA",5.6f,228.89));
		flightList.add(new Flight(458,"BW2310","WAS","MIA",5.9f,217.53));
		flightList.add(new Flight(123,"BW8645","JFK","MIA",4.4f,250.52));
		flightList.add(new Flight(565,"JB6431","NYA","BWI",4.2f,467.29));
		flightList.add(new Flight(899,"BW6345","JFK","BWI",5.1f,250.52));
		flightList.add(new Flight(254,"BW1031","JFK","MIA",2.1f,110.49));
		
		System.out.println("Print all Flights in Flight List");   
		printFlights(flightList);				  // UNSORTED Print of all Flights 
		
		String myFile = "./myFlights.txt";

		WriteObjReadObj.writeObject(myFile, flightList);

		List<Flight> flightList2 = WriteObjReadObj.readObject(myFile);
		System.out.println("\nPrint all Flights after reading from 'myFlights.txt'");
		printFlights(flightList2);
	}

	
//	private static List<Flight> readObject(String myFileName) {
//		Object obj = null;
//		List<Flight> c = null;
//		
//		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(myFileName))){
//			
//			obj = ois.readObject(); //de-serialization
//			c = (List<Flight>)obj; //Casts an object from type Object to our type Parrot
////			return p;
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			System.out.println("I'm in finally!!!");
////			return null;
//		}
//		return c; //Best practice to have only have return for a block
//	}
//
//
//	private static void writeObject(String myFileName, List<Flight> flightList) {
//		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(myFileName))){
//			oos.writeObject(flightList); //serialization
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}


	public static void printFlights(List<Flight> flightList) {
		for (int i = 0; i < flightList.size(); i++) {
			System.out.println(flightList.get(i));
		}
	}
	
	
}
