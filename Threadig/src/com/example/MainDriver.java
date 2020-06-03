package com.example;

import com.example.threads.MyRunnable;
import com.example.threads.MyThread;

public class MainDriver {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * What is a thread?
		 * 		A single process of executions, share the resources within the core it's in.
		 *  
		 * Multithreading?
		 * 		Running multiple threads in parallel 
		 */
		
//		Thread t = new Thread(); //Created a thread in Java
//		
//		Thread mainThread = Thread.currentThread();//reference the current thread we're inside
//		
//		System.out.println(mainThread.getName());
		
		/*
		 * Main Thread:
		 * 		Child threads will be spawned from it
		 * 		It will be the last thread to finish execution, once it stops, the program stops 
		 */
		
		/*
		 * Daemon thread is always running in the background of an application, e.g. Garbage Collector
		 * 
		 * User Thread, will can be started and closed.
		 */
		
//		Thread.sleep(4000); //4000 millisecond
//		
//		System.out.println(mainThread.isDaemon());
//		System.out.println(mainThread.isAlive());
//		
//		mainThread.setName("My Special thread");
//		System.out.println("Main thread's name has been changed to" + mainThread.getName());
//		
		/*
		 *2 ways to make our own custrom threads:
		 *		Implementing Runnable interface
		 *			You can extend another class while also implementing runnable
		 *			Less overhead, you don't get unnecessary  methods
		 *		
		 *		Extending Thread Class
		 *			Less lines of code to "start"
		 *			Easier to override the functionality of the Thread class
		 */
		
		MyThread mt = new MyThread();
		MyThread mt2 = new MyThread();
		MyRunnable mr = new MyRunnable();
		
//		mt.run(); //current thread will execute the logic
//		mt.start(); //start method for the other thread to call on the run method
//		mt2.start();
//		
		mr.run(); //Still using the main thread.
		Thread t = new Thread(mr);//passing in runnable into thread
		mr.run();
		
		
		/*
		 * STATES OF A THREAD!
		 * 
		 * New -> Runnable -> Running -> Waiting -> Dead
		 * 
		 * New:
		 * 	The thread is instantiated 
		 * 
		 * Runnable:
		 * 	The start() method has been invoked but the scheduler has not selected it to start 
		 * 		runnin the thread.
		 * 
		 * Running:
		 * 	The thread is running (scheduler has selected it)
		 * 
		 * Waiting, Time Waiting, Blocked:
		 * 	The thread is alive, but not currently running
		 * 
		 * Dead:
		 * 	Thread has been terminated
		 */
		
		/*
		 * Common Thread problems:
		 * 		Syncronization:
		 * 			When threads are utilising the same resource, e.g. modifing a data structure,
		 * 			errors will occur because changes will occur to the resource in seperate threads.
		 * 
		 * 		Deadlock:
		 * 			When multiple threads are trying to access a single resource 
		 * 
		 * 		Producer-Consumer:
		 * 			Producer produces resources for another thread, via storing it in a buffer and 
		 * 			a consumer uses the resources and removes it from the buffer. Errors occur if the 
		 * 			there is no items in the buffer or if the buffer is already filled up.  
		 */
		
		
		
		
	}

}
