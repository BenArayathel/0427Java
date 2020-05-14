package multithreading.synchronization_demo;

public class Printer {

	public synchronized static void printMessage(Message message) {
		
        System.out.println(message.getMessage1());

        try {
            Thread.currentThread();
			Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(message.getMessage2());
	}
    
}