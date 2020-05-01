package com.example.stream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterStream {
	
	//Will print to a text file in human readable language (tedious)

	public static void main(String[] args) {
		String filename = "./charExample.txt";
		
		writeCharacterStream(filename);
		readCharacterStream(filename);
		

	}

	private static void readCharacterStream(String filename) {
		FileReader reader = null;
		
		try {
			reader = new FileReader(filename);
			int i;
			while ((i = reader.read()) != -1) {
				System.out.println((char) i); //Casting up to (char) will change to alphanumeric values.
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void writeCharacterStream(String filename) {
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(filename, false); //([filename], true/false, true will append, false will overwrite)
			
			for ( int i = 65; i<100; i++) {
				//65 -> A
				//66 -> B 
				writer.write(i);
			}
			
		} catch( FileNotFoundException e) {
			System.out.println("File not found!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		} catch(FileNotFoundException e) {
//			//unreachable code right now 
//		}
		
	}

}
