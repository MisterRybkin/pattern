package com.example.pattern.state_pattern;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class GumballMachineTest {

    @Test
    void dispense() {
        GumballMachine gumballMachine = new GumballMachine(5);

    }
}