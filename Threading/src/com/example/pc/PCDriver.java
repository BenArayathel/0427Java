package com.example.pc;

public class PCDriver {
	
	public static void main(String[] args) throws InterruptedException {
		
		ProducerConsumer pc = new ProducerConsumer();
		
		Runnable p = () ->{
			try {
				pc.produce();
			} catch (InterruptedException e) {
				System.out.println("thread interrupted");
			}
		};
		
		Runnable c = () ->{
			try {
				pc.consume();
			} catch (InterruptedException e) {
				System.out.println("thread interrupted");
			}
		};
		
		Thread tp = new Thread(p);
		Thread tc = new Thread(c);
		
		//starts both threads
		tp.start();
		tc.start();
		
		tp.join();
		tc.join();
	}

}
