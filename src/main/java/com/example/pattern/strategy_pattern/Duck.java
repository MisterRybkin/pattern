package com.example.pattern.strategy_pattern;

public abstract class Duck {

    String color;
    String sex;

    Fly fly;

    public String performFly() {
        return fly.fly();
    }
}
