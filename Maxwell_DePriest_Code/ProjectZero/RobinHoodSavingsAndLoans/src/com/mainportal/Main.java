package com.mainportal;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public Main() {
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String filename = "./customerRecords.txt";
		// signIn();
		Lobby l = new Lobby();
		ArrayList<Customer> customerRecords = new ArrayList<Customer>();
		l.createFile();
		l.readObject(filename, customerRecords);
	}
	
	public void createFile() {
		
	}

	
}