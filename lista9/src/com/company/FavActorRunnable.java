package com.company;

public class FavActorRunnable implements  Runnable {
    private String name;
    private String lastName;

    public FavActorRunnable(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public void run() {
        System.out.println(name+" "+lastName);
    }
}
