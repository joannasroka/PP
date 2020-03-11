package com.company;

import java.util.concurrent.Semaphore;

public class Count extends Thread {
    public static IntCell n = new IntCell();
    private static Semaphore semaphore = new Semaphore(1);      // 2b)

    @Override
    public void run() {
        int temp;

        // 2a)
        /*
        for (int i = 0; i < 200000; i++) {
            synchronized (n) {              // 2a)
                temp = n.getN();
                n.setN(temp + 1);
            }
        }
        */
        // 2b)

        for (int i = 0; i < 200000; i++) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
            }
            ;
            temp = n.getN();
            n.setN(temp + 1);
            semaphore.release();
        }


    }
}
