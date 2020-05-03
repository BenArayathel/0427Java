package com.mainportal;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public Main() {
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String filename = "./customerRecords.txt";
		//String testFile = "./testFile.txt";
		// signIn();
		Lobby l = new Lobby();
		//ArrayList<Customer> tempRecords = new ArrayList<Customer>();
		l.createFile(filename);
		ArrayList<Customer> allCustomerRecords = l.readObject(filename);
	}
	
	public void createFile() {
		
	}

	
}