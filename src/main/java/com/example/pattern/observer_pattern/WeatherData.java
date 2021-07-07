package com.example.pattern.observer_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * класс регистрирует изменения и отправляет
 */

public class WeatherData implements Subject {

    List<Observer> observerList;
    float temp;
    float humidity;
    float pressure;

    public WeatherData() {
        this.observerList = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observerList.indexOf(o);
        if (i >= 0) {
            observerList.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer o: observerList
             ) {
            o.update(temp, humidity, pressure);
        }
    }


}
