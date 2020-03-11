package com.company;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher extends  Thread {
    private int number;
    private Doorkeeper doorkeeper;
    private Stick leftStick;
    private Stick rightStick;

    Random random = new Random();

    public Philosopher(int number, Doorkeeper doorkeeper, Stick [] sticks) {
        this.number = number;
        this.doorkeeper = doorkeeper;
        leftStick = sticks[number];
        rightStick = sticks[(number+1)%sticks.length];
    }

    private void eat() throws InterruptedException{
        doorkeeper.letIn();
        System.out.println("Philosopher number: " + number + " is in the dining room ");

        leftStick.takeStick();
        System.out.println("Philosopher number: " + number + " has left stick ");

        rightStick.takeStick();
        System.out.println("Philosopher number: " + number + " has right stick ");
        System.out.println("Philosopher number: " + number + " is eating. ");
        sleep(random.nextInt(1000));

        leftStick.putDownStick();
        rightStick.putDownStick();
        doorkeeper.letOut();
        System.out.println("Philosopher number: " + number + " is meditating. ");
        meditate();
    }

    private void meditate() throws InterruptedException{
        sleep(random.nextInt(100));
        eat();
    }

    @Override
    public void run() {
      try{  meditate();}
      catch (InterruptedException e) { e.printStackTrace();}
    }
}
