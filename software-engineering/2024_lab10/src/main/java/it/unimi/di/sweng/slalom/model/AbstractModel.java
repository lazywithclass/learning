package it.unimi.di.sweng.slalom.model;

import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.Observer;
import it.unimi.di.sweng.slalom.Skier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel implements Observable<List<Skier>> {

    private final List<Observer<List<Skier>>> observers = new ArrayList<>();
    protected final List<Skier> skiers;

    protected AbstractModel() {
        skiers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for (Observer<List<Skier>> observer : observers) {
            observer.update(this, getState());
        }
    }

    @Override
    public void addObserver(@NotNull Observer<List<Skier>> observer) {
        observers.add(observer);
    }

    @Override
    public @NotNull List<Skier> getState() {
        return List.copyOf(skiers);
    }

}
