package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.Observer;
import it.unimi.di.sweng.slalom.Skier;
import it.unimi.di.sweng.slalom.model.ModelFirstManche;
import it.unimi.di.sweng.slalom.model.ModelSecondManche;
import it.unimi.di.sweng.slalom.views.DisplayView;
import it.unimi.di.sweng.slalom.views.InputView;
import it.unimi.di.sweng.slalom.views.NextSkierView;
import it.unimi.di.sweng.slalom.views.OutputView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SecondManchePresenter implements Presenter, Observer<List<Skier>> {

    private final NextSkierView nextSkierView;
    private final DisplayView outputView;
    private final ModelFirstManche modelFirstManche;
    private final ModelSecondManche modelSecondManche;

    public SecondManchePresenter(NextSkierView nextSkierView, DisplayView outputView, ModelFirstManche modelFirstManche, ModelSecondManche model) {
        this.nextSkierView = nextSkierView;
        this.outputView = outputView;
        this.modelFirstManche = modelFirstManche;
        this.modelSecondManche = model;
    }

    @Override
    public void action(@NotNull String text1, @NotNull String text2) {
        Skier skier = new Skier(text1, Double.parseDouble(text2));
        modelSecondManche.addSkier(skier);
    }

    @Override
    public void update(@Nullable Observable<List<Skier>> subject, @NotNull List<Skier> state) {
        modelFirstManche.getState()

        List<Skier> ordered = new ArrayList<>(state);
        ordered.sort((o1, o2) -> {
            if (o1.time() != o2.time()) return Double.compare(o1.time(), o2.time());
            return o1.name().compareTo(o2.name()) * -1;
        });
        for (int i = 0; i < ordered.size(); i++) {
            outputView.set(i, ordered.get(i).toString());
        }
    }
}
