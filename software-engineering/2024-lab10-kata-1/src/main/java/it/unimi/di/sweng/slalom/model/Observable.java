package it.unimi.di.sweng.slalom.model;

import it.unimi.di.sweng.slalom.presenters.Observer;
import org.jetbrains.annotations.NotNull;

public interface Observable<T> {
    void notifyObservers();

    void addObserver(@NotNull Observer<T> obs);

    @NotNull T getState();
}

