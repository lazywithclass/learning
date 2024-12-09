package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Model;
import org.jetbrains.annotations.NotNull;

public class SkierPresenter<S, T> implements Presenter, Observer<Model> {

    private final ModelReductionStrategy<S> reductionStrategy;

    public SkierPresenter(ModelReductionStrategy<S> reductionStrategy, T view) {
        this.reductionStrategy = reductionStrategy;
    }

    @Override
    public void action(@NotNull String text1, @NotNull String text2) {

    }

    @Override
    public void update(Model model) {
        // update View with the result of the reduction
        reductionStrategy.reduce(model);
    }
}
