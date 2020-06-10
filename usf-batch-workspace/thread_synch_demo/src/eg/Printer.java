package eg;

public class Printer {

	public synchronized static void printMessage(Message message) {
		System.out.print(message.getMessage1());
		try {
			Thread.currentThread().sleep(1500);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		System.out.println(message.getMessage2());
	}
}
