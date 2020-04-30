package scanners_streams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferStream {

    // Much easier than CharacterStream and ByteStream

    public static void main(String[] args) {
        
        String fileName = "./BufferedFile.txt";
        
        // writeCharacterStream(fileName);
        readCharacterStream(fileName);
    }

    private static void readCharacterStream(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line = "";
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // private static void writeCharacterStream(String fileName) {

    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)) {

    //         // Can simply type out strings
            
    //     }
    // }
}