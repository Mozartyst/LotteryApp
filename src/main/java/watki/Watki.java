package watki;

import java.util.concurrent.atomic.AtomicInteger;

public class Watki {

    public void run() {
        AtomicInteger counter = new AtomicInteger(1);


        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (counter) {
                    while (counter.get() != 1) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("Hello ");
                    counter.set(2);
                    counter.notifyAll();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (counter) {
                    while (counter.get() != 2) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("world");
                    counter.set(3);
                    counter.notifyAll();
                }
            }
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (counter) {
                    while (counter.get() != 3) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("!\n");
                    counter.set(1);
                    counter.notifyAll();
                }
            }
        });
        t3.start();
    }
}
