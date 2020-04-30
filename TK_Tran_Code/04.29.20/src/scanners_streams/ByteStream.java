package scanners_streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteStream {

	// ByteStream prints to txt file in non-human-readable format

    public static void main(String[] args) {
        
        String fileName = "./ByteFile.txt";

        writeByteStream(fileName);
        readByteStream(fileName);

        // readClassFile();
    }

    private static void readByteStream(String fileName) {

        InputStream input = null;

        // Normal try method
        try {
            int i;
            input = new FileInputStream(fileName);

            while((i = input.read()) != -1) {
                System.out.println(i + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void writeByteStream(String fileName) {

        // try (with resources) method; easier than normal try method above
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            out.write(4);
            out.write(13);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // This method opens AND closes resource upon completion; no need to .close() at end
        // try (with resources) uses () after the "try" keyword
        // () only accepts an object that is "AutoClosable" or at least a descendant of it
	}

    
    
    // private static void readClassFile() {
	// 	String fileName = "./bin/com/scanners_streams/ByteStream.class";
		
	// 	// Using shortcut again
	// 	try (InputStream input = new FileInputStream(fileName)) {
	// 		byte[] first4 = new byte[4];
	// 		input.read(first4);

	// 		for(byte b : first4) {
	// 			System.out.println(Integer.toHexString(b).substring(6));
	// 		}
	// 	} catch (FileNotFoundException e) {
	// 		e.printStackTrace();
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
    // }
}