package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Observable;
import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.views.DisplayView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SecondManchePresenter implements Presenter, Observer<List<Skier>> {

    private final DisplayView view;

    public SecondManchePresenter(DisplayView view) {
        this.view = view;
    }

    @Override
    public void action(@NotNull String text1, @NotNull String text2) {

    }

    @Override
    public void update(@Nullable Observable<List<Skier>> obs, @Nullable List<Skier> state, @Nullable List<Skier> nextSkier) {
        if (obs instanceof Model model) {
            List<Skier> secondManche = model.getSecondMancheSkiers();
            for (int i = 0; i < secondManche.size(); i++) {
                this.view.set(i, secondManche.get(i).toString());
            }
        }
    }

}
