package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.Observable;
import it.unimi.di.sweng.slalom.Observer;
import it.unimi.di.sweng.slalom.Skier;
import it.unimi.di.sweng.slalom.model.ModelFirstManche;
import it.unimi.di.sweng.slalom.views.OutputView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FirstManchePresenter implements Presenter, Observer<List<Skier>> {

    private final OutputView view;
    public final ModelFirstManche model;

    public FirstManchePresenter(OutputView view, ModelFirstManche model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void action(String text1, String text2) {
        System.out.println("FirstManchePresenter.action");
    }

    @Override
    public void update(@Nullable Observable<List<Skier>> subject, @NotNull List<Skier> state) {
        List<Skier> ordered = new ArrayList<>(state);
        ordered.sort((o1, o2) -> {
            if (o1.time() != o2.time()) return Double.compare(o1.time(), o2.time());
            return o1.name().compareTo(o2.name()) * -1;
        });
        for (int i = 0; i < ordered.size(); i++) {
            view.set(i, ordered.get(i).toString());
        }
    }
}
