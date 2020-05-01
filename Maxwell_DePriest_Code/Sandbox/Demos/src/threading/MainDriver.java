package threading;

public class MainDriver {

	public MainDriver() {
		/*
		 * What is a thread?
		 * A single process of execution, share the resources within the core it's in
		 * Multithreading? 
		 * Running multiple threads in parallel
		 */
		
		Thread t = new Thread(); // Created a thread in Java
		
		Thread mainThread = Thread.currentThread(); // reference the current thread we're inside
		System.out.println(mainThread.getName());
		
		/*
		 * Main Thread:
		 * 	Child threads will be spawned from it
		 * 	It will be the last thread to finish execution, once it stops, the program stops
		 * 
		 * Daemon thread is always running in the background of an application, ex Garbage Collector
		 * 
		 * User thread which can be started and closed
		 */
		
		System.out.println(mainThread.isDaemon());
		mainThread.setName("My Fabulous Thread");
		System.out.println(mainThread.getName());
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
