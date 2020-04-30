package com.example.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedStream {
	
	public static void main(String[] args) {
		
		String filename = "./src/BufferedFile.txt";
		writeCharacterStream(filename);
		readCharacterStream(filename);
		
	}

	private static void readCharacterStream(String filename) {
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
			
			String line = "";
			while((line = reader.readLine()) != null) {
				System.out.println(line);
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
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
			
			writer.write("Hey there \n");
			writer.write("Buffered Streams are faster and easuer to sure\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
