package com.company;

public class Account {
    private int amountOfMoney;

    public Account(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public int getAmountOfMoney () { return amountOfMoney; }
    public void setAmountOfMoney (int newAmount){ this.amountOfMoney = newAmount; }
}
