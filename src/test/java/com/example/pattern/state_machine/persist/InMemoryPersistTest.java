package com.example.pattern.state_machine.persist;

import com.example.pattern.state_machine.enums.Events;
import com.example.pattern.state_machine.enums.States;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class InMemoryPersistTest {

    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;

    @Autowired
    private StateMachinePersister<States, Events, UUID> persister;

    @Test
    public void testPersist() throws Exception {
        StateMachine<States, Events> firstStateMachine = stateMachineFactory.getStateMachine();

        StateMachine<States, Events> secondStateMachine = stateMachineFactory.getStateMachine();

        firstStateMachine.sendEvent(Events.START_FEATURE);
        firstStateMachine.sendEvent(Events.DEPLOY);

        assertEquals(secondStateMachine.getState().getId(), States.BACKLOG);

        //сохраняем контекст первой стейт-машины и загружаем во вторую
        persister.persist(firstStateMachine, firstStateMachine.getUuid());
        persister.restore(secondStateMachine, firstStateMachine.getUuid());

        assertEquals(secondStateMachine.getState().getId(), States.IN_PROGRESS);
    }
}