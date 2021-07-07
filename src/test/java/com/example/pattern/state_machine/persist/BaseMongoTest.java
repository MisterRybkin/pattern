package com.example.pattern.state_machine.persist;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.containers.GenericContainer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BaseMongoTest {

    private static final Integer MONGO_PORT = 27017;
    private static GenericContainer mongo = new GenericContainer("mongo:latest")
            .withExposedPorts(MONGO_PORT);

    static {
        mongo.start();
        System.setProperty("spring.data.mongodb.host", mongo.getContainerIpAddress());
        System.setProperty("spring.data.mongodb.port", mongo.getMappedPort(MONGO_PORT).toString());
    }

    @Autowired
    protected MongoTemplate mongoTemplate;
}
