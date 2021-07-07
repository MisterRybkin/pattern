package com.example.pattern.observer_pattern;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver(); //изменение состояния субъекта
}
