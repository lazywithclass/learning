package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModel implements Model, Observable<Double> {

    private double temp;
    private List<Observer<Double>> observers = new ArrayList<>();

    @Override
    public double getTemp() {
        return temp;
    }

    @Override
    public void setTemp(double temp) {
        if (this.temp != temp) {
            this.temp = temp;
            notifyObservers();
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer<Double> obs : observers) {
            obs.update(this, temp);
        }
    }

    @Override
    public void addObserver(@NotNull Observer<Double> obs) {
        observers.add(obs);
    }

    @Override
    public @NotNull Double getState() {
        return getTemp();
    }

}
