package com.example.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteStream {
	
	//Will write non-human readable code!!!
	
	public static void main(String[] args) {
		String filename ="./ByteFile.txt";
		
//		writeByStream(filename);
//		readByStream(filename);
		readThisClassFile();
	}

	private static void readThisClassFile() {
		String filename = "./bin/com/example/stream/ByteStream.class";
		try(InputStream inputs = new FileInputStream(filename)){
			byte[] first4 = new byte[4];
			inputs.read(first4);
			
			for(byte b: first4) {
				System.out.print(Integer.toHexString(b).substring(6));
//				System.out.println(Character.toString(b));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void readByStream(String filename) {
		InputStream inputs = null;
		try {
			int i;
			inputs = new FileInputStream(filename);
			
			while((i = inputs.read()) != -1) {
				System.out.println(i + " ");
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static void writeByStream(String filename) {
		
		/*
		 * try with resources automatically closes a resource whe it is done
		 * 	being used (after the try block).
		 * The try with resources uses a set of parenthesis after the "try" keyword 
		 * 	the parenthesis only accepts an object that is of type "AutoClosable" or
		 * 	at least a descendant.
		 */

		try (FileOutputStream outs = new FileOutputStream(filename);){
			outs.write(65);
			outs.write(13);
			outs.write(77);
			outs.write(126);
			outs.write(45);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
