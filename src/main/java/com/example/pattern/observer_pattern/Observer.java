package com.example.pattern.observer_pattern;

/**
 * с этим интерфейсом взаимодействует субъект
 */

public interface Observer {

    void update(float temp, float humidity, float pressure);
}
