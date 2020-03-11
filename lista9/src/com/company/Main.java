package com.company;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
/*

        Account account = new Account(100);

        Semaphore semaphore = new Semaphore(1);

        ThreadPayIn payIn = new ThreadPayIn(account, semaphore); // ten 200 tysiecy razy wplaca: +1
        ThreadPayOut payOut = new ThreadPayOut(account, semaphore); // ten 200 tysiecy razy wyplaca: -1

        payIn.start();
        payOut.start();

        try {
            payIn.join();
            payOut.join();
        } // czyli czekamy aż te dwa wątki się skończą
        catch (InterruptedException e) {
        }
        System.out.println("Amount of money: " + account.getAmountOfMoney());
*/
        // zad 3
        Doorkeeper doorkeeper = new Doorkeeper();
        Stick sticks[] = new Stick[5];

        for (int i = 0; i < 5; i++) {
            sticks[i] = new Stick();
        }

        Philosopher philosophers[] = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(i, doorkeeper, sticks);
            philosophers[i].start();
        }

/*
        // zad 4

        FavActorRunnable favActorRunnable = new FavActorRunnable("Leoanardo", "DiCaprio");

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(favActorRunnable, 3L, 1L, TimeUnit.SECONDS);


        // quick sort wielowątkowo
        int [] arr = {7, 2,2,4,5,1,     0, 8,9,11,2};

        QuickSort quick1 = new QuickSort();

        quick1.setSequence(arr);
        quick1.start();

        try { quick1.join();  } // czyli czekamy aż wątki się skończą
        catch (InterruptedException e) { }


        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]+"; ");
        }

        // paradymki cwiczenia lista 11

        Count p = new Count();
        Count q = new Count();
        p.start();
        q.start();
        try {
            p.join();
            q.join();
        } catch (InterruptedException e) {
        }
        System.out.println("The value of n is " + p.n.getN() + " " + q.n.getN());

        // wyniki to np. 219 077,  268 756, 283 363, 310 328
        // poprawny: 400 000

*/
    }


}

