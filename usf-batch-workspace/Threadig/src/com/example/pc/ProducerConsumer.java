package com.example.pc;

import java.util.LinkedList;

public class ProducerConsumer {
	
	//Creates a list shared by producer and cnsumer
	
	LinkedList<Integer> list = new LinkedList<>();
	int capacity = 4;
	
	//produce method 
	public void produce() throws InterruptedException {
		int value = 0;
		while(true) {
			synchronized (this) {
				//if capacity has been reached, thread will wait
				while( list.size() == capacity) {
					wait();
				}
				
				System.out.println("My producing thread creates:" + value);
				
				list.add(value++);
				
				notify(); //notify wakes up the thread 
				
				Thread.sleep(1000); //makes it easier to look at
			}
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			synchronized (this) {
//				while( list.size() == 0) {
//					wait();
//				}
				
				int value = list.removeFirst();
				System.out.println("Consuming thread destroys:" + value);
				
				notify();
				
				Thread.sleep(1000);
			}
		}
	}

}
