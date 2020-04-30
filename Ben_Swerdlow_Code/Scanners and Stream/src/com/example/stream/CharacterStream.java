package com.example.stream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterStream {

	// Will print to a text file in human readable language (tedious)
	
	public static void main(String[] args) {

		// do not have huge blocks of code in main!
		// Instead, compartmentalize your application and make it clear
		// what it is doing
		
		String filename = "./charExample.txt";
		
		writeCharacterStream(filename);
		readCharacterStream(filename);

	}

	private static void readCharacterStream(String filename) {
		FileReader reader = null;
		
		try {
			reader = new FileReader(filename);
			int i;
			while ((i=reader.read()) != -1) {
				System.out.println((char) i);
				// if you don't cast i to (char), you'll get the ASCII integer represetntation
				// not the ASCI character representation
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void writeCharacterStream(String filename) {

		FileWriter writer = null;
		
		try {
			writer = new FileWriter(filename, true);
			// FileWriter(filename, false) - if it finds an already existing file named filename, it will overwrite it
			// FileWriter(filename, true) - if it finds an already existing file named filename, it will append to it
			for (int i=65; i<100; i++) {
				/*
				 * will write will always write in ASCII, not actual int i
				 * 65 -> A
				 * 66 -> B
				 * etc.
				 */
				writer.write(i);
				/*
				 * If you want to write the actual number, convert to String
				 */
//				writer.write(i+"");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();// never do this in practice; stack trace is for developers only
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		} catch (FileNotFoundException e) {
//			// unreachable code right now since it's after IOException
//			// FileNotFoundException is a child of IOException
//		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
