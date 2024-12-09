package it.unimi.di.sweng.temperature.presenter;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.Observer;
import it.unimi.di.sweng.temperature.model.TemperatureModel;
import it.unimi.di.sweng.temperature.view.MyTextView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TemperaturePresenter implements Presenter, Observer<Double> {

    private final TemperatureModel model;
    private final MyTextView view;
    private final ScaleStrategy strategy;

    public TemperaturePresenter(MyTextView view, TemperatureModel model, ScaleStrategy strategy) {
        this.model = model;
        this.view = view;
        this.strategy = strategy;
    }

    @Override
    public void action(@NotNull String text) {
        model.setTemp(strategy.convertToCelsius(Double.parseDouble(text)));
    }

    @Override
    public void update(@Nullable Observable<Double> subject, @NotNull Double state) {
        view.setValue(String.format("%.2f", strategy.convertFromCelsius(subject.getState())));
    }
}
