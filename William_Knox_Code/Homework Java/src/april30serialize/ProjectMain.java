package april30serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class ProjectMain {
	public static void main(String[] args) {
		
		/*
		 * Serialize your object, then de-serialize your object
		 * Serialize and de-serialize a...
		 * 		Set of your objects
		 * 		List of your objects
		 * 		Queue of your objects
		 * 
		 * Transient property: Magazine.darts
		 */

		Blaster s1 = new Stryfe(new Magazine(10, 2));
		Blaster s2 = new Stryfe(new Magazine(20, 4));
		Blaster s3 = new Stryfe(new Magazine(30, 6));
		Blaster d1 = new DoubleBreach(new Magazine(3,3));
		Blaster d2 = new DoubleBreach(new Magazine(6,6));
		Blaster d3 = new DoubleBreach(new Magazine(9,9));
		
		List<Blaster> bll = new LinkedList<>();
		bll.add(s1);
		bll.add(s2);
		bll.add(s3);
		bll.add(d1);
		bll.add(d2);
		bll.add(d3);
		List<Blaster> bv = new Vector<>(bll);
		Set<Blaster> bhs = new HashSet<>(bll);
		Set<Blaster> bts = new TreeSet<>(bll);
		
		// end of initialization
		
		// Serialize and Un-Serialize Objects
		// Take note of transience: Magazines do not serialize their current contents.
		
		writeObject("./stryfe1.txt", s1);
		Blaster testStryfe1 = (Blaster) readObject("./stryfe1.txt");
		
		writeObject("./stryfe2.txt", s2);
		Blaster testStryfe2 = (Blaster) readObject("./stryfe2.txt");
		
		writeObject("./doubleBreach1.txt", d1);
		Blaster testDB1 = (Blaster) readObject("./doubleBreach1.txt");
		
		writeObject("./doubleBreach2.txt", d1);
		Blaster testDB2 = (Blaster) readObject("./doubleBreach2.txt");
		
		
		System.out.println("Read Blasters from Files:");
		System.out.println(testStryfe1);
		System.out.println(testStryfe2);
		System.out.println(testDB1);
		System.out.println(testDB2);
		
		// Serialize and Un-Serialize Object Collections

		writeObject("./linkedListBlasters.txt", bll);
		writeObject("./VectorBlasters.txt", bv);
		writeObject("./HashSetBlasters.txt", bhs);
		writeObject("./TreeSetBlasters.txt", bts);
		
		System.out.println("Linked List Blasters:");
		printList((List<Blaster>) readObject("./linkedListBlasters.txt"));
		System.out.println("Vector Blasters:");
		printList((List<Blaster>) readObject("./VectorBlasters.txt"));
		System.out.println("Hash Set Blasters:");
		printSet((Set<Blaster>) readObject("./HashSetBlasters.txt"));
		System.out.println("Tree Set Blasters");
		printSet((Set<Blaster>) readObject("./TreeSetBlasters.txt"));
		
	}
	
	public static void writeObject(String filename, Object b) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));) {
			oos.writeObject(b);
		} catch (FileNotFoundException e) {
			System.out.println("File couldn't be found when trying to write");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something went wrong with the I/O stream when trying to write");
			e.printStackTrace();
		}
	}
	
	public static Object readObject(String filename) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));) {
 			return ois.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the input file");
		} catch (IOException e) {
			System.out.println("Something went wrong with the I/O stream");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find class for read Object file");
		}
		return null;
	}
	
	
	public static void printList(List<Blaster> l) {
		for (Blaster b : l) {
			System.out.println(b);
		}
	}
	
	public static void printSet(Set<Blaster> s) {
		for (Blaster b : s) {
			System.out.println(b);
		}
	}
}
