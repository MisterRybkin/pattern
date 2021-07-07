package com.example.pattern.state_machine.config;

import com.example.pattern.state_machine.enums.Events;
import com.example.pattern.state_machine.enums.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;

@Configuration
@EnableStateMachineFactory(name = "secondStateMachineFactory")
public class SecondStateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

}
