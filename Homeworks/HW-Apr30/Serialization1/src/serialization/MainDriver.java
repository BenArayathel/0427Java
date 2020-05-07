package serialization;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class MainDriver {

	public static void main(String[] args) {
		String filename = "/Users/amir/Desktop/bank.txt";
		
		bank b1 = new bank("Amir", "1000");
		
		writeObject(filename, b1);
		bank b2 = readObject(filename);

		System.out.println(b2);
	}

	private static void writeObject(String filename, bank b1) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
		oos.writeObject(b1); //serialization
		
		}catch(FileNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private static bank readObject(String filename) {
		Object obj = null;
		bank b = null;
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
			obj = ois.readObject(); //de-serialization
			b = (bank)obj; //Casts an object from type Object to our type bank
			
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
		return b;
	}


	
}


