package it.unimi.di.sweng.esame.model;

import it.unimi.di.sweng.esame.presenter.Observer;

public interface Observable<T> {

    void addObserver(Observer<T> observer);

    void notifyObservers();

    T getState();

}
