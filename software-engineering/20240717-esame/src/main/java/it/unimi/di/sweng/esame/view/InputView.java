package it.unimi.di.sweng.esame.view;


import it.unimi.di.sweng.esame.presenter.IPresenter;
import org.jetbrains.annotations.NotNull;

public interface InputView {
    void addHandlers(@NotNull IPresenter presenter);

    void showError(@NotNull String s);

    void showSuccess();
}
