package com.example.pattern.state_pattern;

import java.util.Random;

public class HasQuarterState implements State {
    Random randomWinner = new Random(System.currentTimeMillis());
    GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Уже есть монетка");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Возврат монетки");
        gumballMachine.setState(gumballMachine.getNoQuarter());
    }

    @Override
    public void turnCrank() {
        System.out.println("Вы дернули за рычаг...");
        int winner = randomWinner.nextInt(10);
        if ((winner == 0) && (gumballMachine.getCount() > 1)) {
            gumballMachine.setState(gumballMachine.getWinner());
        } else {
            gumballMachine.setState(gumballMachine.getSold());
        }
    }

    @Override
    public void dispense() {
        System.out.println("Сперва нужно дернуть рычаг");
    }
}
