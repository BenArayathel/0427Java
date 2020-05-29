package com.examples.hw430;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HWMain {

	public static void main(String[] args) {
		/*
		 * Flight(int fid, String name, String source, String destination, 
		 * 		  float ratings, double ticketPrice)
		 *
		 * default sorting fid using List, then customize as you want
		 *
		 * also find out about difference between LinkedList and PriorityQueue, 
		 * the two classes implementing the Queue Interface
		 *
		 * Additionally: now use Set Interface instead of List interface
		 *
		 * Additional assignment:
		 * 		make your own object other than flight and serialize it and deserialize it
		 * 		serialize one of the data structure interfaces (Set, List, Queue)
		 * 		to be comfortable with storing multiple objects
		 */
		
		List<Flight> flightList = new ArrayList<>();
		flightList.add(new Flight(123, "Delta 123", "MSP", "IAD", 3.8f, 149.99));
		flightList.add(new Flight(222, "Spirit 222", "LAX", "ORD", 0.8f, 109.99));
		flightList.add(new Flight(70, "American 70", "DCA", "ORD", 2.7f, 149.99));
		flightList.add(new Flight(555, "Delta 555", "MSP", "LAX", 3.5f, 249.99));
		System.out.println("Flight List");
		System.out.println(flightList);
		Collections.sort(flightList);
		System.out.println("Sorted Flight List");
		System.out.println(flightList);
		
		/*
		 * Perhaps the most important distinction between the LinkedList type in Java and the 
		 * PriorityQueue class is the accessibility of its nodes.  In a LinkedList you can access
		 * any node either by its index or by searching for every node equal to a parameter object.
		 * In a PriorityQueue object, only the first object in line (ranked according to priority
		 * per Comparable) can be fetched.
		 */
		
		Set<Flight> flightSet = new HashSet<>();
		flightSet.add(new Flight(123, "Delta 123", "MSP", "IAD", 3.8f, 149.99));
		flightSet.add(new Flight(222, "Spirit 222", "LAX", "ORD", 0.8f, 109.99));
		flightSet.add(new Flight(70, "American 70", "DCA", "ORD", 2.7f, 149.99));
		flightSet.add(new Flight(555, "Delta 555", "MSP", "LAX", 3.5f, 249.99));
		flightSet.add(new Flight(123, "Delta 123", "MSP", "IAD", 3.8f, 149.99));
		System.out.println(flightSet);
		
		String filename = "./myBallplayer.txt";
		
		Ballplayer b1 = new Ballplayer("Joe Mauer", 0.365f, 28);
		
		writeObject(filename, b1);
		Ballplayer b2 = readObject(filename);
		
		System.out.println("Name: " + b2.getName());
		System.out.println("Batting Avg: " + b2.getBa());
		System.out.println("Home Runs: " + b2.getHrs());
		
		ArrayList<Ballplayer> playerList = new ArrayList<>();
		playerList.add(b1);
		playerList.add(new Ballplayer("Miguel Sano", 0.266f, 40));
		playerList.add(new Ballplayer("Eddie Rosario", 0.277f, 29));
		writeList("./myPlayerList.txt", playerList);
		ArrayList<Ballplayer> pl = readList("./myPlayerList.txt");
		
		for(Ballplayer b : pl) {
			System.out.println(b);
		}
	}

	private static Ballplayer readObject(String filename) {
		Object obj = null;
		Ballplayer b = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			obj = ois.readObject();		// deserialization
			b = (Ballplayer)obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return b;	
	}

	private static void writeObject(String filename, Ballplayer b1) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(b1);	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private static ArrayList<Ballplayer> readList(String filename) {
		Object obj = null;
		ArrayList<Ballplayer> playerList = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			obj = ois.readObject();		// deserialization
			playerList = (ArrayList<Ballplayer>)obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return playerList;	
	}

	private static void writeList(String filename, ArrayList<Ballplayer> playerList) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(playerList);	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
