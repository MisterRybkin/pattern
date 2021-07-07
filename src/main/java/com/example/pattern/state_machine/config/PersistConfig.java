package com.example.pattern.state_machine.config;

import com.example.pattern.state_machine.enums.Events;
import com.example.pattern.state_machine.enums.States;
import com.example.pattern.state_machine.persist.InMemoryPersist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.data.mongodb.MongoDbPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.mongodb.MongoDbStateMachineRepository;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

import java.util.UUID;

/**
 * Конфигурация где хранить состояния стейт машины
 */

@Configuration
public class PersistConfig {

    @Bean
    public StateMachinePersister<States, Events, UUID> persister(
            StateMachinePersist<States, Events, UUID> defaultPersist) {

        return new DefaultStateMachinePersister<>(defaultPersist);
    }

//    @Bean
//    public StateMachinePersist<States, Events, UUID> inMemoryPersist() {
//        return new InMemoryPersist();
//    }

    @Bean
//    @Profile("mongo")
    public StateMachineRuntimePersister<States, Events, UUID> mongoPersist(
            MongoDbStateMachineRepository mongoRepository) {
        return new MongoDbPersistingStateMachineInterceptor<States, Events, UUID>(mongoRepository);
    }
}
