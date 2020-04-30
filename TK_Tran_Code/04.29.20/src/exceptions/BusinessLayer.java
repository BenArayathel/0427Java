package exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BusinessLayer {

    // Simply a method to open a file (to test FileNotFoundException in Main.java)
    public void openFile(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
    }

    // Illustrating throw using custom-created exceptions (to test InvalidAgeException in Main.java)
    public boolean isValidAge(int age) throws InvalidAgeException {
        if (age <= 0) {
            throw new InvalidAgeException("Age cannot be negative or zero!");
        }
        if (age < 20 || age > 40) {
            throw new InvalidAgeException("Users should be between 20 to 40 only!");
        }

        return true;
    }
}