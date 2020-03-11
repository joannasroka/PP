package com.company;

import java.util.concurrent.Semaphore;

public class Stick {
    private Semaphore stick;

    public Stick() {
        stick = new Semaphore(1);
    }

    public void takeStick(){
        try{
            stick.acquire();
        } catch (InterruptedException e) { e.printStackTrace();}
    }

    public void putDownStick(){
        stick.release();
    }
}
