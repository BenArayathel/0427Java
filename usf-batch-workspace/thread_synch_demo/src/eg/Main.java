package eg;

public class Main {

	public static void main(String[] args) {
		new Message("hey hello ", "how are you doing today");
		new Message("threads ", "main is a app thread and GC is deamon thread");
		new Message("how you keeping ", "up during the lockdown");
		new Message("there is lot to learn ", "yes java has lots of stuff written for developers");
		new Message("stay awake ", "P0 is on your way");

	}

}
//Why wait() notify() notifyAll() methods are in Object class why not in Thread class?
// wait vs sleep
//wait vs join
//yield