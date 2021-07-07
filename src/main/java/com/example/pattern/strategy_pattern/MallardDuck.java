package com.example.pattern.strategy_pattern;

public class MallardDuck extends Duck {

    public MallardDuck() {
        this.color = "Green";
        this.sex = "Man";
        this.fly = new FlyWithWings();
    }


}
