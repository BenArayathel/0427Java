package com.example.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteStream {
	
	public static void main(String[] args) {
		String filename = "./ByteFile.txt";
		
		writeByStream(filename);
		readByStream(filename);
		readThisClassFile();
	}

	private static void readThisClassFile() {
		// reads the actual class ByteStream itself
		String filename = "./bin/com/example/stream/ByteStream.class";
		try (InputStream inputs =  new FileInputStream(filename)){
			byte[] first4 = new byte[4];
			inputs.read(first4);
			
			for (byte b: first4) {
				System.out.print(Integer.toHexString(b).substring(6));
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
		// traditional way
		InputStream inputs = null;
		try {
			int i;
			inputs = new FileInputStream(filename);
			
			while ((i = inputs.read()) != -1) {
				System.out.println(i+"");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		 * shorthand method
		 * try with resources automatically closes a resource when it is done
		 * being used (after the try block, this is the thing in parentheses 
		 * next to the try block)
		 * 
		 * keep in mind try with resources uses a set of parentheses after the "try"
		 * keyword
		 * the parentheses only accepts an object that is of type AutoClosable or
		 * a descendant.
		 */
		
		try (FileOutputStream outs = new FileOutputStream(filename);){
			// FileOutputStream.write will write the literal integer, not ASCII
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
