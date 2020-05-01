package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {

        String file = "./myCar.txt";

        Car car = new Car(2018, "Subaru", "WRX", "Black");

        // Uses custom methods to serialize
        serialize(file, car);
        serializeShortcut(file, car);

        // Uses custom methods to deserialize
        Car recreatedCar = deserialize(file);
        Car recreatedCar1 = deserializeShortcut(file);

        // Prints deserialized, recreatedCar (notice the transient member was never saved to file)
        System.out.println("Deserialized from data and recreated Object: " + recreatedCar);
        System.out.println("Deserialized from data and recreated Object (using shortcut): " + recreatedCar1);
    }

    // Custom method to serialize an Object to a file
    private static void serialize(String file, Car car) {
        try {
            FileOutputStream fos = new FileOutputStream(file);  // Creates a FileOutputStream to write to the file.
            ObjectOutputStream oos = new ObjectOutputStream(fos);   // Creates a ObjectOutputStream that writes primitives of objects to specified OutputStream.
            oos.writeObject(car); // Writes (serializes) the object to the ObjectOutputStream.
            // Free resources
            fos.close();
            oos.close();
            System.out.println("Serialized Object.. data stored in: " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Custom method to deserialize and recreate an Object
    private static Car deserialize(String file) {
        Car c = null;   // Dummy variable to be updated with recreated object

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            c = (Car) ois.readObject(); // Reads (deserializes and recreates) object from the ObjectInputStream; note return value is cast back to Car in order to be spit out from method
            // Free resources
            fis.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Car class not found!");
            e.printStackTrace();
        }

        return c;
    }

    // try_resources shortcut to serialize, does same thing as normal try method
    private static void serializeShortcut(String file, Car car) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(car);
            oos.close();
            System.out.println("Serialized Object (using shortcut).. data stored in: " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // try_resources shortcut to deserialize, does same thing as normal try method
    private static Car deserializeShortcut(String file) {
        Car c = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            c = (Car) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Car class not found!");
            e.printStackTrace();
        }

        return c;
    }
}