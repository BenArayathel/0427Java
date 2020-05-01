package multithreading.threads;

// Creates a custom thread by extending Thread class
public class MyThread extends Thread {
    
    @Override
    public void run() {

        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}