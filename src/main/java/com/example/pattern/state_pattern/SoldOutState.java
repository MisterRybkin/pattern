package com.example.pattern.state_pattern;

public class SoldOutState implements State {
    GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Шары кончились");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Шары кончились");
    }

    @Override
    public void turnCrank() {
        System.out.println("Шары кончились");
    }

    @Override
    public void dispense() {
        System.out.println("Шары кончились");
    }
}
