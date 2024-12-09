package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Observable;
import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.views.NextSkierView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NextSkierPresenter implements Presenter, Observer<List<Skier>> {

    private final NextSkierView nextSkierView;
    private final Model model;

    public NextSkierPresenter(NextSkierView view, Model model) {
        this.nextSkierView = view;
        this.model = model;
    }

    @Override
    public void update(Observable<List<Skier>> obs, List<Skier> state, List<Skier> state2) {
        if (state2 == null || state2.isEmpty()) return;
        Skier s = state2.get(0);
        nextSkierView.set(0, s.name());
    }

    @Override
    public void action(@NotNull String text1, @NotNull String text2) {
        Skier skier = new Skier(text1, Double.parseDouble(text2));
        model.addSecondMancheSkier(skier);
    }
}
