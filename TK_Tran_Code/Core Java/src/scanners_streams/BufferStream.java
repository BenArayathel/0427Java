package scanners_streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferStream {

	// Much easier than CharacterStream and ByteStream
	// Same method as writing/reading from character/byte stream except use of BufferedWriter and BufferedReader.

	public static void main(String[] args) {

		String fileName = "./BufferedStreamFile.txt";

		writeBufferedStream(fileName);
		readBufferedStream(fileName);
	}

	private static void writeBufferedStream(String file) {

		// try_resources shortcut!
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write("I can type an entire String now!\n");
			bw.write("Buffered streams are faster and easier to use than character streams and byte streams!\n");
		} catch (FileNotFoundException e) {
			System.out.println("BufferedWriter cannot find specified file!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Note: try_resources shortcut eliminates the need to flush() and close().
	}

	private static void readBufferedStream(String file) {

		// try_resources shortcut!
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String lineOfText = "";

			while((lineOfText = br.readLine()) != null) { // readLine() reads entire line, contrast to FileReader's read().
				System.out.println(lineOfText);
			}
		} catch (FileNotFoundException e) {
			System.out.println("BufferedReader cannot find specified file!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Note: try_resources shortcut eliminates the need to flush() and close().
	}
}