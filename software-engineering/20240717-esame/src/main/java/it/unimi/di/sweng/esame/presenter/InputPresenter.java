package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.view.InputView;
import org.jetbrains.annotations.NotNull;

public class InputPresenter implements IPresenter {

    private final Model model;
    private final InputView view;

    public InputPresenter(Model model, InputView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void action(@NotNull String text, @NotNull String text1) {
        try {
            model.addBooking(text, text1);
            view.showSuccess();
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

}
