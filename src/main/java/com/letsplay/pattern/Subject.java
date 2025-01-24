package com.letsplay.pattern;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers;
    public Subject() {
        observers = new ArrayList<>();
    }
    public void attach(Observer observer) {
        observers.add(observer);
    }
    public void detach(Observer o) {
        observers.remove(o) ;
    }
    protected void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
