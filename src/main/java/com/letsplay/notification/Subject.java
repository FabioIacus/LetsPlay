package com.letsplay.notification;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers;
    protected Subject() {
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
