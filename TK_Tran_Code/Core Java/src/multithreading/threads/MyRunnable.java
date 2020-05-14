package multithreading.threads;

// Creates a custom thread by implementing Runnable interface (best practice)
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}