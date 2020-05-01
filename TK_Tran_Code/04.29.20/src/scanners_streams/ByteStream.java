package scanners_streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStream {

	// ByteStream prints to txt file in non-human-readable format.
	// Same method as writing/reading from character stream except use of FileOutputStream and FileInputStream.

	public static void main(String[] args) {

		String fileName = "./ByteStreamFile.txt";

		// Uses custom methods to write and read byte stream from file.
		writeByteStream(fileName);
		readByteStream(fileName);

		// Able to read from a CLASS file too!
		readClassFile();
	}

	private static void writeByteStream(String file) {

		// Creates a FileOutputStream which writes a BYTE stream to file.
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			fos.write(4);
			fos.write(13);
		} catch (FileNotFoundException e) {
			System.out.println("FileOutputStream cannot find specified file!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void readByteStream(String file) {

		// Creates a FileInputStream which reads a BYTE stream from a file.
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);

			int i;
			while((i = fis.read()) != -1) {
				System.out.println(i + " ");
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileInputStream cannot find specified file!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	// Custom method to read from a class file (if you needed to)
	private static void readClassFile() {
		String file = "./bin/scanners_streams/ByteStream.class";

		// uses try_resources shortcut!
		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] first4 = new byte[4];

			fis.read(first4);

			for(byte b : first4) {
				System.out.println(Integer.toHexString(b).substring(6));
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileInputStream cannot find specified CLASS!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Note: try_resources shortcut eliminates the need to flush() and close().
	}
}