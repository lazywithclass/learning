package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.view.InputView;
import org.jetbrains.annotations.NotNull;

public class PayDebitPresenter implements IPresenter {

    private final Model model;
    private final InputView view;

    public PayDebitPresenter(Model model, InputView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void action(@NotNull String text, @NotNull String text1) {
        try {
            model.payDebit(text, Double.parseDouble(text1));
            view.showSuccess();
        } catch (NumberFormatException e) {
            view.showError("Invalid amount to pay.");
        } catch (IllegalArgumentException e) {
            view.showError("You can't pay more than you owed!");
        }
    }

}
