package com.example.pattern;

import com.example.pattern.state_machine.enums.Events;
import com.example.pattern.state_machine.enums.States;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class StatesFirstApplicationTests {

    private  StateMachine<States, Events> stateMachine;

    @Autowired
    private StateMachinePersister<States, Events, UUID> persister;

    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;

    @BeforeAll
    public void setUp() throws Exception {
        stateMachine = stateMachineFactory.getStateMachine();
    }

    @Test
    public void initTest() {
        assertEquals(stateMachine.getState().getId(), States.BACKLOG);
        assertNotNull(stateMachine);
    }

    @Test
    public void testGreenFlow() {
        // Действия
        stateMachine.sendEvent(Events.START_FEATURE);
        stateMachine.sendEvent(Events.FINISH_FEATURE);
        stateMachine.sendEvent(Events.QA_TEAM_APPROVE);

        assertEquals(stateMachine.getState().getId(), States.DONE);
    }

    @Test
    public void testWrongWay() {
        //Arrange
        //Act
        stateMachine.sendEvent(Events.START_FEATURE);
        stateMachine.sendEvent(Events.QA_TEAM_APPROVE);

        assertEquals(stateMachine.getState().getId(), States.IN_PROGRESS);
    }

    @Test
    public void testRockStar() {
        stateMachine.sendEvent(Events.ROCK_STAR_MAKE_ALL_IN_ONE);

        assertEquals(stateMachine.getState().getId(), States.TESTING);
    }

    @Test
    public void testGuard() {
        stateMachine.sendEvent(Events.START_FEATURE);
        stateMachine.sendEvent(Events.FINISH_FEATURE);
        stateMachine.sendEvent(Events.QA_TEAM_APPROVE);
        assertEquals(stateMachine.getState().getId(), States.IN_PROGRESS);
    }
}
