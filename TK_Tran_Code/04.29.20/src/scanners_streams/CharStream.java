package scanners_streams;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharStream {

	// CharacterStream prints individual characters (tedious!!!) to txt file in human-readable format.
	// **This example includes notes that apply to ByteStream.java and BufferStream.java as well.

	public static void main(String[] args) {

		String fileName = "./charStreamFile.txt";

		// Uses custom methods to write to and read character stream from file.
		writeCharStream(fileName);
		readCharStream(fileName);
	}

	private static void writeCharStream(String file) {

		// Creates a FileWriter which writes a CHARACTER stream to a file.
		FileWriter fw = null;

		try {
			fw = new FileWriter(file, false); // Passes file into FileWriter; true will append, false will override

			// For this example, we're writing out a capitalized alphabet; change range to experiment.
			// Iterates through ASCII table (?) (Reference: http://www.asciitable.com/)
			for (int i = 65; i < 91; i++) {
				// 65 is A
				// 90 is Z
				fw.write(i); // write() writes a single char.
			}
		// Protip: catch exceptions from SPECIFIC TO BROAD (if vice versa, specific will never be reached).
		} catch (FileNotFoundException e) { // Specific; FileNotFoundException is a child of IOException.
			System.out.println("FileWriter cannot find specified file!");
		} catch (IOException e) { // Broad
			e.printStackTrace();
		} finally {
			try {
				fw.flush(); // Flushes (if stream has any saved chars, write them immediately to intended destination).
				fw.close(); // Closes FileWriter to free resources.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void readCharStream(String file) {

		// Creates a FileReader which reads a CHARACTER stream from a file.
		FileReader fr = null;

		try {
			fr = new FileReader(file); // Passes file into FileReader

			int i; // Simply a var for iterating
			while ((i = fr.read()) != -1) { // read() reads a single char; iteration ensures every character is read.
				System.out.println((char) i); // Cast to convert everything (like potential ints) into chars.
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileReader cannot find specified file!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close(); // Closes FileReader to free resources.
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Notice we didn't need to flush a reader, because that doesn't make any sense..
		}
	}
}