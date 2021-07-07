package com.example.pattern.state_machine.config;

import com.example.pattern.state_machine.enums.Events;
import com.example.pattern.state_machine.enums.States;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

@Slf4j
@Configuration
@EnableStateMachineFactory
//все наши состояния описаны в enums
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .listener(listener())
                .autoStartup(true);
    }

    //состояния стейт-машины
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.BACKLOG)
                .state(States.IN_PROGRESS)
                .state(States.TESTING)
                .end(States.DONE);
    }


    //переходы стейт-машины
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {

        transitions.withExternal()
                .source(States.BACKLOG)
                .target(States.IN_PROGRESS)
                .event(Events.START_FEATURE)

                .and()
                .withExternal()
                .source(States.BACKLOG)
                .target(States.BACKLOG)
                .event(Events.DEPLOY)
                .action(deployAction())

                .and()
                .withExternal()
                .source(States.IN_PROGRESS)
                .target(States.IN_PROGRESS)
                .event(Events.DEPLOY)
                .action(deployAction())

                .and()
                .withExternal()
                .source(States.IN_PROGRESS)
                .target(States.TESTING)
                .event(Events.FINISH_FEATURE)
                .guard(checkDeployGuard())

                .and()
                .withExternal()
                .source(States.TESTING)
                .target(States.IN_PROGRESS)
                .event(Events.QA_TEAM_REJECT)

                .and()
                .withExternal()
                .source(States.TESTING)
                .target(States.DONE)
                .event(Events.QA_TEAM_APPROVE)

                .and()
                .withExternal()
                .source(States.BACKLOG)
                .target(States.TESTING)
                .guard(checkDeployGuard())
                .event(Events.ROCK_STAR_MAKE_ALL_IN_ONE);
    }

    //действия
    private Action<States, Events> deployAction() {
        return stateContext -> {
            log.warn("DEPLOYING: {}", stateContext.getEvent());
            stateContext.getExtendedState()
                    .getVariables()
                    .put("deployed", true);
        };
    }

    //страж, возвращает boolean
    private Guard<States, Events> checkDeployGuard() {
        return stateContext -> {
            Boolean flag = (Boolean) stateContext.getExtendedState()
                    .getVariables()
                    .get("deployed");
            return flag == null ? false : flag;
        };
    }



    //обработчик событий
    private StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {

            //медод перехвата любого перехода
            @Override
            public void transition(Transition<States, Events> transition) {
                log.warn("move from:{} to:{}",
                        ofNullableState(transition.getSource()),
                        ofNullableState(transition.getTarget()));
            }

            //метод срабатывающий при попытке осуществить недопустимый переход
            @Override
            public void eventNotAccepted(Message<Events> event) {
                log.error("event not accepted: {}", event);
            }

            private Object ofNullableState(State s) {
                return Optional.ofNullable(s)
                        .map(State::getId)
                        .orElse(null);
            }
        };
    }
}

