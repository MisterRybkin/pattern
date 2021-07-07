package com.example.pattern.state_machine.persist;

import com.example.pattern.state_machine.enums.Events;
import com.example.pattern.state_machine.enums.States;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class MongoPersistTest extends BaseMongoTest {

    @Autowired
    private StateMachinePersister<States, Events, UUID> persister;

    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;

    @Test
    public void testMongoPersist() throws Exception {
        StateMachine<States, Events> firstStateMachine = stateMachineFactory.getStateMachine();
        StateMachine<States, Events> secondStateMachine = stateMachineFactory.getStateMachine();

        firstStateMachine.sendEvent(Events.START_FEATURE);
        firstStateMachine.sendEvent(Events.DEPLOY);

        persister.persist(firstStateMachine, firstStateMachine.getUuid());
        persister.persist(secondStateMachine, secondStateMachine.getUuid());
        persister.restore(secondStateMachine, firstStateMachine.getUuid());

        assertEquals(secondStateMachine.getState().getId(), States.IN_PROGRESS);

        boolean deployed = (boolean) secondStateMachine
                .getExtendedState()
                .getVariables()
                .get("deployed");

        assertTrue(deployed);

        List<Document> documents = mongoTemplate.findAll(Document.class, "MongoDbRepositoryStateMachine");
    }
}
