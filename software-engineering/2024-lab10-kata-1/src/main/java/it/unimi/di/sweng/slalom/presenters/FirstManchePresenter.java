package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Observable;
import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.views.DisplayView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FirstManchePresenter implements Presenter, Observer<List<Skier>> {

    private final DisplayView displayView;

    public FirstManchePresenter(DisplayView displayView) {
        this.displayView = displayView;
    }

    @Override
    public void update(Observable<List<Skier>> obs, List<Skier> state, List<Skier> nextSkier) {
        for (int i = 0; i < state.size(); i++) {
            displayView.set(i, state.get(i).toString());
        }
    }

    public void action(@NotNull String text1, @NotNull String text2) {

    }

}
