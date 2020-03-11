package com.company;

import java.util.concurrent.Semaphore;

public class Doorkeeper {
    private Semaphore doorkeeper;

    public Doorkeeper() {
        doorkeeper = new Semaphore(4);
    }

    public void letIn(){
        try{
            doorkeeper.acquire();
        } catch (InterruptedException e) { e.printStackTrace();}
    }

    public void letOut(){
        doorkeeper.release();
    }

}
