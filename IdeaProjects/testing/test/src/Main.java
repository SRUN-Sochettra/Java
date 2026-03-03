class FineGrainedCounter {
    private int count = 0;
    // Best Practice: Use a private final object as a lock
    private final Object lock = new Object();

    public int getCount() {
        return count;
    }

    public void increment() {
        // 1. Non-critical code: Multiple threads can execute this at once
        String threadName = Thread.currentThread().getName();
        // Simulation of some heavy work or logging
        System.out.println(threadName + " is preparing to increment...");

        // 2. Critical Section: Only one thread can enter this block at a time
        synchronized(lock) {
            this.count++;
            System.out.println(threadName + " updated count to: " + this.count);
        }

        // 3. Non-critical code: Multiple threads can execute this again
        System.out.println(threadName + " has finished the operation.");
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FineGrainedCounter counter = new FineGrainedCounter();

        // Create two threads targeting the same instance
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) counter.increment();
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) counter.increment();
        }, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Total Count: " + counter.getCount());
    }
}