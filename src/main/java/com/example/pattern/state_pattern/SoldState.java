package com.example.pattern.state_pattern;

public class SoldState implements State {
    GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Идет выдача шарика");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Идет выдача шарика");
    }

    @Override
    public void turnCrank() {
        System.out.println("Идет выдача шарика");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarter());
        } else {
            System.out.println("Шарики тютю");
            gumballMachine.setState(gumballMachine.getSoldOut());
        }
    }
}
