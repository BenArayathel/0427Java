package eg;

public class Printer {
	// Imagine this as a physical office printer that must take time to print the message on paper
	// and has multiple people sending jobs to the printer at once
	
	// synchronized says that only 1 thread acquires this block of code at a time!
	public synchronized static void printMessage(Message message) {
		System.out.println(message.getMessage1());
		// have printer wait for message page to complete
		try {
			Thread.currentThread().sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Should tell the user to check with their system administrator
			// because the OS is generally responsible for parallelization issues
		}
		// then print message2 on the next page
		System.out.println(message.getMessage2());
		
	}

}
