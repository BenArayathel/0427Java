package eg;

public class Main {

	public static void main(String[] args) {
		new Message("hey hello ", "how are you doing today");
		new Message("threads ", "main is an app thread and GC is a daemon thread");
		new Message("how you keeping ", "up during the lockdown");//lol
		new Message("there is a lot to learn ", "yes java has lots of stuff written for developers");//lol
		new Message("stay awake ", "P0 is on your way");//lol
		// At first, the messages are all mixed up! Oh no! This is called a race condition
		// I need only 1 thread to access the printer at a time
		// Therefore, I add the synchronized keyword to Printer.printMessage()

	}

}
/*
 * Why are the wait() notify() notifyAll() methods are in the Object class, why not in the Thread class?
 * wait vs. sleep
 * wait vs. join
 * yield
 * 
 * You will have to do research outside of class to anser these questions
 */