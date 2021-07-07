package com.example.pattern.state_machine.persist;

import com.example.pattern.state_machine.enums.Events;
import com.example.pattern.state_machine.enums.States;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;
import java.util.UUID;

/**
 * Хранилище состояний стейт-машины
 */

public class InMemoryPersist implements StateMachinePersist<States, Events, UUID> {

    private HashMap<UUID, StateMachineContext<States, Events>> storage = new HashMap<>();

    @Override
    public void write(StateMachineContext<States, Events> context, UUID contextObj) throws Exception {
        storage.put(contextObj, context);
    }

    @Override
    public StateMachineContext<States, Events> read(UUID contextObj) throws Exception {
        return storage.get(contextObj);
    }
}
