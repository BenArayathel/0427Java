package com.example.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedStream {

	public static void main(String[] args) {

		// ./ puts it in the current package
		String filename = "./BufferedFile.txt";
		writerCharacterStream(filename);
		readCharacterStream(filename);

	}

	private static void readCharacterStream(String filename) {
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void writerCharacterStream(String filename) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			writer.write("Hey there\n");
			writer.write("Buffered Streams are faster and easier to use");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
