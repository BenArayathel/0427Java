package scanners_streams;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Streams {

    // CharacterStream prints individual characters (tedious) to txt file in human-readable format

    public static void main(String[] args) {

        String fileName = "./charExample.txt";

        writeCharacterStream(fileName);
        readCharacterStream(fileName);
    }
    
    private static void readCharacterStream(String fileName) {

        FileReader reader = null;

        try {
            reader = new FileReader(fileName);
            int i;
            while ((i = reader.read()) != -1) {
                System.out.println((char) i);   // MUST cast to convert ints into characters
            }
        } catch (FileNotFoundException e) {
            System.out.println("Reader cannot find file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void writeCharacterStream(String fileName) {

        FileWriter writer = null;

        try {
            writer = new FileWriter(fileName, false); // true will append, false will override
            
            for (int i = 65; i < 100; i++) {
                // 65 is A
                // 66 is B
                writer.write(i);
            }

        // Remember to catch exceptions from specific TO broad (specific won't be reached if vice versa)
        } catch (FileNotFoundException e) { // Specific
            System.out.println("Writer cannot find file!");
        } catch (IOException e) {   // Broad (FileNotFoundException is child of IOException)
            e.printStackTrace();
        } finally {
            try {
                writer.flush(); // Flushes
                writer.close(); // Closes Writer Object
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}