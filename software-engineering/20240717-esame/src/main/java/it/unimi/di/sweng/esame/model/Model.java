package it.unimi.di.sweng.esame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.unimi.di.sweng.esame.presenter.Observer;
import org.jetbrains.annotations.NotNull;

public class Model extends ModelState implements Observable<List<Excursion>> {

    private final List<Observer<List<Excursion>>> observers;

    public Model() {
        super();
        this.observers = new ArrayList<>();
    }

    @Override
    public void readFile(@NotNull Scanner scanner) {
        super.readFile(scanner);
        notifyObservers();
    }

    @Override
    public void addObserver(@NotNull Observer<List<Excursion>> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<List<Excursion>> obs : this.observers) {
            obs.update(this, getState());
        }
    }

    public @NotNull List<Excursion> getState() {
        return this.excursions.values().stream().toList();
    }

    @Override
    public void addBooking(@NotNull String person, @NotNull String excursionName) {
        super.addBooking(person, excursionName);
        notifyObservers();
    }

    @Override
    public void payDebit(@NotNull String person, double amount) {
        super.payDebit(person, amount);
        notifyObservers();
    }

}
