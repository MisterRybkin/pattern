package com.example.pattern.state_pattern;

public class NoQuarterState implements State {
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Вы бросили монетку");
        gumballMachine.setState(gumballMachine.getHasQuarter());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Сперва нужно бросить монетку");
    }

    @Override
    public void turnCrank() {
        System.out.println("Нет монетки");
    }

    @Override
    public void dispense() {
        System.out.println("Монетки нет - шарика нет ");
    }
}
