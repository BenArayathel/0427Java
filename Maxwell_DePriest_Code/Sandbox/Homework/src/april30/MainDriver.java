package april30;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream; 
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;

public class MainDriver {

	
	
	public MainDriver() {

	}
	
	public static void main(String[] args) {
		
		List<PenguinsPlayer> roster = new ArrayList();
		
		PenguinsPlayer sid = new PenguinsPlayer("Sidney Crosby", "Center", 87);
		PenguinsPlayer geno = new PenguinsPlayer("Evgeni Malkin", "Center", 71);
		PenguinsPlayer mMurray = new PenguinsPlayer("Matt Murray", "Goalie", 30);
		PenguinsPlayer pHornqvist = new PenguinsPlayer("Patric Hornqvist", "Right Wing", 72);
		PenguinsPlayer jGuentzel = new PenguinsPlayer("Jake Guentzel", "Left Wing", 69);
		PenguinsPlayer tanger = new PenguinsPlayer("Kris Letang", "Defenseman", 58);
		
		roster.add(sid);
		roster.add(geno);
		roster.add(mMurray);
		roster.add(pHornqvist);
		roster.add(jGuentzel);
		roster.add(tanger);
		
		String filename = "./pittPens.txt";	
		PittsburghPenguins p = new PittsburghPenguins();
		writeObject(filename, p);
		
		System.out.println(p.getHeadCoach());
		System.out.println(p.getCaptain());
		p.waveTerribleTowel();
		
		String filename2 = "./pensRoster.txt";
		writeObject(roster, filename2);
		PittsburghPenguins p2 = readObject(filename);
		List<PenguinsPlayer> pP = readObject2(filename2);
		
		
	}
	
	public static void writeObject(String filename, PittsburghPenguins obj) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(obj);
			oos.close();
		} catch (FileNotFoundException e) {
			System.out.println("We could not find the appropriate file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("We have encountered an issue");
			e.printStackTrace();
		}
		
	}
	
	public static void writeObject(List<PenguinsPlayer> roster, String fn) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fn));
			oos.writeObject(roster);
			oos.close();
		} catch (FileNotFoundException e) {
			System.out.println("We could not find the appropriate file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("We have encountered an issue");
			e.printStackTrace();
		}
		
	}
	
	private static PittsburghPenguins readObject(String filename) {
		PittsburghPenguins p = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
			
			Object obj = ois.readObject();
			p = (PittsburghPenguins)obj; 
			
		} catch (FileNotFoundException e) {
			System.out.println("We could not find the appropriate file");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("We have encountered an error");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("We have encountered an error");
			e.printStackTrace();
		} 
		return p;
		
	}
	
	private static List<PenguinsPlayer> readObject2(String fN) {
		ArrayList<PenguinsPlayer> starters = new ArrayList<PenguinsPlayer>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fN))) {
			List<Object> o = (ArrayList<Object>)ois.readObject(); //Downcast to store object from file
			
			for(Object obj : o) {
				starters.add((PenguinsPlayer)obj);  // For each loop to put penPlayer objects into the arrayList
			}
			
		
		} catch(IOException | ClassNotFoundException e) {
			System.out.println("We encountered an error. Please try again");
			e.printStackTrace();
			return null;
		}
		return starters;
	}
	
	

}
