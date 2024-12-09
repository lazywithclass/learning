package it.unimi.di.sweng.slalom.presenters;

import org.jetbrains.annotations.NotNull;

public interface Presenter {
  void action(@NotNull String text1, @NotNull String text2);
}
