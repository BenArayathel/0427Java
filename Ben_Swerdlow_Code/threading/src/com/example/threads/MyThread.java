package com.example.threads;

public class MyThread extends Thread {
	
	@Override//overriding the Thread class's run method
	public void run() {
		for (int i=0; i<4; i++) {
			System.out.println(Thread.currentThread().getName());
		}	
	}

}
