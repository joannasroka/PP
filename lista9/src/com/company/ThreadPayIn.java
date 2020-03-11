package com.company;

import java.util.concurrent.Semaphore;

public class ThreadPayIn extends Thread {
    private Account account;
    private Semaphore semaphore;

    public ThreadPayIn(Account account, Semaphore semaphore) {
        this.account = account;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int temp;
        for (int i = 0; i < 200000; i++) {
            try{
                semaphore.acquire();
            } catch (InterruptedException e) {e.printStackTrace();};
            temp = account.getAmountOfMoney();
            account.setAmountOfMoney(temp + 1);
            semaphore.release();
        }
    }
}
