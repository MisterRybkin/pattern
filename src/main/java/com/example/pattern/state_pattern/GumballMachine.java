package com.example.pattern.state_pattern;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GumballMachine {
    State soldOut; // нет шариков
    State noQuarter; //нет монетки
    State hasQuarter;  //есть монетка
    State sold;  //шарик продан
    State winner; //приз

    State state = soldOut; // распакованный автомат пуст
    int count = 0; // кол-во шариков в автомате

    public GumballMachine(int numberGumballs) {
        this.soldOut = new SoldOutState(this);
        this.noQuarter = new NoQuarterState(this);
        this.hasQuarter = new HasQuarterState(this);
        this.sold = new SoldState(this);
        this.winner = new WinnerState(this);
        this.count = numberGumballs;
        if (numberGumballs > 0) {
            state = noQuarter;
        } else {
            state = soldOut;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    void releaseBall() {
        System.out.println("Шар покинул слот");
        if (count != 0) {
            count--;
        }
    }
}
