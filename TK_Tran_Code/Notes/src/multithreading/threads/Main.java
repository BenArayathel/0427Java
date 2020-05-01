package multithreading.threads;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        
        // Thread t = new Thread(); // How to create a thread in Java

        // Thread mainThread = Thread.currentThread(); // References current thread we're inside.
        // System.out.println(mainThread.getName()); // Prints default name
        // Thread.sleep(1000); // 1000 ms; forces thread to wait
        // System.out.println(mainThread.isDaemon()); // Checks if thread is a daemon thread.
        // System.out.println(mainThread.isAlive()); // Checks if thread is alive.
        // mainThread.setName("Renamed Thread"); // Renames the thread
        // System.out.println("Renaming thread: " + mainThread.getName());

        // Using Custom Threads
        MyThread mt = new MyThread();
        MyThread mt2 = new MyThread();
        MyRunnable mr = new MyRunnable();
        mt.run(); // Current thread will execute the logic.
        mt.start(); // Start method for the other thread to call on the run method.
        mt2.start();
        mr.run(); // Still using the main thread!
        Thread t = new Thread(mr); // Passing in Runnable into thread
        t.start();
        // t.start(); // ERROR, cannot restart thread once its started!
        mr.run(); // Can repeatedly use run()



    }
}

/* 
    Homework:
        Why are wait(), notify(), notifyAll() methods in Object class? Why not in Thread class?
        wait() vs sleep()
        wait() vs join()
        What is yield?
            means that the thread is not doing anything particularly important and if any 
            other threads or processes need to be run, they should run.
*/