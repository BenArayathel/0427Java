package com.example.threads;

public class MyRunnable implements Runnable{

	@Override
	public void run() {

		for(int i=0; i<4 ;i++) {
			System.out.println(Thread.currentThread().getName());
		}
		
	}

}
