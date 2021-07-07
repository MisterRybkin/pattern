package com.example.pattern.state_pattern;

public class WinnerState implements State {
    GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
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
        if (gumballMachine.getCount() == 0) {
            System.out.println("Шарики тютю");
            gumballMachine.setState(gumballMachine.getSoldOut());
        } else {
            System.out.println("Два шарика!");
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarter());
            } else {
                System.out.println("Шарики тютю");
                gumballMachine.setState(gumballMachine.getSoldOut());
            }
        }
    }
}
