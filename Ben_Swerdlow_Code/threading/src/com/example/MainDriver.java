package com.example;

import com.example.threads.MyRunnable;
import com.example.threads.MyThread;

public class MainDriver {

	public static void main(String[] args) throws InterruptedException {

		/*
		 * What is a thread?
		 * 		A single process of execution, shares the resources within the core it's in
		 * Multi-threading?
		 * 		Running multiple threads in parallel
		 * 
		 * 
		 */
		
//		Thread t = new Thread();// We've created a thread!
		
		// As soon as you start the main, a main thread is made. Let's grab it.
//		Thread mainThread = Thread.currentThread();//references the current thread we're inside.\
//		
//		System.out.println(mainThread.getName());
		
		/*
		 * Main Thread:
		 * 		All Child threads will be spawned from the Main Thread
		 * 		It will be the last thread to finish execution
		 */
		
		/*
		 * Daemon Thread:
		 * 		Is always running in the background of an application
		 * 		It never stops executing
		 * 		E.g. the garbage collector (always deleting unused resources)
		 * 
		 * User Thread:
		 * 		Made by the user
		 * 		Can be started and closed within the application
		 */
		
//		System.out.println(mainThread.isDaemon());
//		System.out.println(mainThread.isAlive());
//		System.out.println(mainThread.isInterrupted());
		
//		mainThread.setName("My special thread");
		
//		System.out.println("Main is now called \""+mainThread.getName()+"\"");
//		
//		System.out.println("Sleeping");
//		Thread.sleep(3000);// sleeps for 1000 milliseconds (1 second); it's a static method
		// needs to handle an interrupted exception if using (we put it in throws)
		
//		System.out.println("Done!");
		
		/*
		 * Two ways to make custom thread
		 * 1) Implementing Runnable Interface
		 * 		Pros: Can extend any class and any other interface
		 * 				Less overhead, less unnecessary methods
		 * 		Cons: More lines of code to write
		 * 
		 * 2) Extending Thread Class
		 * 		Pros: Less code to write
		 * 				Easier to override the functionality of the Thread class
		 * 		Cons: Cannot extend any other class, more overhead (some unnecessary methods)
		 * 
		 * Generally, you'll implement the runnable interface because of overhead and inheritance
		 * 
		 */
		MyThread mt = new MyThread();
		MyThread mt2 = new MyThread();
		MyRunnable mr = new MyRunnable();
		
		// will use the thread it's currently in
		mt.run();
		// will create a new thread
		mt.start();
		// start is only in the thread class
		mt2.start();
		
		// how do we do the interface
		
		mr.run();// still using the main thread
		Thread t = new Thread(mr);// passing in runnable into thread will start the thread
		t.start();
//		t.start();// once a thread is started, cannot restart it (doesn't automatically close)
		mr.run();// can repeatedly use the run method when within the thread (it automatically closes when done running)
		
		/*
		 * STATES OF A THREAD!
		 * 
		 * New -> Runnable -> Running -> Waiting -> Dead
		 * 
		 * New: 
		 * 		The thread is instantiated
		 * Runnable: 
		 * 		The start() method has been invoked, but the scheduler has not selected it
		 * Running: 
		 * 		The scheduler has selected the started thread and is running
		 * Waiting(, Time Waiting, and Blocked): 
		 * 		The thread is alive, but not currently running (sleeping, blocked, etc.)
		 * Dead: 
		 * 		The thread has been terminated
		 */
		
		/*
		 * Common Thread Problems:
		 * 		Synchronization:
		 * 			When threads are utilizing the same resource (e.g. modifying a data structure: errors will occur
		 * 			because changes will occur to the resource in separate threads)
		 * 
		 * 		Deadlock:
		 * 			When multiple threads try to access the same resource at the same time (analogy: multiple people trying
		 * 			to go through a doorway at the same time, they get stuck)
		 * 
		 * 		Producer-Consumer:
		 * 			Producer produces resources for another thread via storing it in a buffer and a consumer uses the resources
		 * 			and removes it from the buffer (great)
		 * 			However, errors occur if at any time there are no items in the list and the consumer tries to grab something
		 * 			from it OR if the buffer is already full and the producer tries to add to it.
		 */

	}

}
