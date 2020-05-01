package serialization_hw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainDriver {
	
	public static void main(String[] args) {
		String filename = "./myPlayer.txt";
		
		Player p1 = new Player("Russel Wilson", 220.4f, 59, 35000000.99);
		
		writeObject(filename, p1);
		Player p2 = readObject(filename);
		p2.run();
		System.out.println(p2); 
	}	

	private static Player readObject(String filename) {
		Object obj = null;
		Player p = null;
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
			
			obj = ois.readObject();
			p = (Player)obj;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			System.out.println("in finally");
		}
		return p; 
	}

	private static void writeObject(String filename, Player p1) {

		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
			oos.writeObject(p1); 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}	