package it.unimi.di.sweng.slalom.views;


import it.unimi.di.sweng.slalom.presenters.Presenter;
import org.jetbrains.annotations.NotNull;

public interface InputView {
  void addHandlers(@NotNull Presenter presenter);

  void showError(@NotNull String s);

  void showSuccess();
}
