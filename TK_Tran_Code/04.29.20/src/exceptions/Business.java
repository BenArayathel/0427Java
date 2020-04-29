package exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Business {

    // Just a method to open a file
    public void openFile(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
    }

    // Illustrating throw
    public boolean isValidAge(int age) throws ArithmeticException {
        if (age <= 0) {
            throw new ArithmeticException("Age cannot be negative or zero");
        }
        if (age < 20 || age > 40) {
            throw new ArithmeticException("Users should be between 20 to 40 only");
        }

        return true;
    }
}