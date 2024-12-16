package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.Main;
import it.unimi.di.sweng.esame.model.Excursion;
import it.unimi.di.sweng.esame.model.Observable;
import it.unimi.di.sweng.esame.view.DisplayView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OutputPresenter implements Observer<List<Excursion>> {

    private final DisplayView view;
    private final IShowingStrategy strategy;

    public OutputPresenter(DisplayView view, IShowingStrategy strategy) {
        this.view = view;
        this.strategy = strategy;
    }

    @Override
    public void update(Observable<List<Excursion>> subject, @NotNull List<Excursion> state) {
        List<String> strings = this.strategy.get();
        for (int i = 0; i < Main.VIEWSIZE; i++) {
            if (i < strings.size()) view.set(i, strings.get(i));
            else view.set(i, "");
        }
    }

}
