package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        
        String fileName = "./myParrot.txt";

        Parrot p = new Parrot("Buddy", "Yellow");

        // Serializes
        writeObject(fileName, p);
        
        // Deserializes
		Parrot p1 = readObject(fileName);

        System.out.println("Deserializing data.." + p1);
    }

    // Serializes an Object and sends it to the output stream.
    private static void writeObject(String fileName, Parrot p) {

        // try method (normal)
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(p); // SERIALIZES PARROT OBJECT
            fos.close();
            oos.close();
            System.out.println("Serialized data is saved in" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // // try_resources method (shortcut)
        // try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
        //     oos.writeObject(p); // SERIALIZES PARROT OBJECT
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
    
    // Retrieves next Object out of the stream and deserializes it.
    // Returns an Object Parrot, so you must cast it to its appropriate type.
    private static Parrot readObject(String fileName) {

        // Object obj = null;  // Dummy variable for try_resources method
        Parrot p = null;

        // try method (normal)
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            p = (Parrot) ois.readObject(); // DESERIALIZES PARROT OBJECT; notice return value cast to Parrot reference
            fis.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Parrot class not found!");
            e.printStackTrace();
        }

        // // try_resources method (shortcut)
        // try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
        //     obj = ois.readObject(); // DESERIALIZES PARROT OBJECT; notice return value cast to Parrot reference
        //     p = (Parrot) obj;	// Casts object from type Object to our type Parrot
        //     return p;
        // } catch (IOException e) {
        //     e.printStackTrace();
        // } catch (ClassNotFoundException e) {    // Ensures reading a class, not a string
		// 	e.printStackTrace();
        // }

        return p;	// Best practice to have only ONE return type per block
    }

}