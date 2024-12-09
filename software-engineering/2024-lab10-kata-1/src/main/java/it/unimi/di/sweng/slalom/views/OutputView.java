package it.unimi.di.sweng.slalom.views;

import org.jetbrains.annotations.NotNull;

public interface OutputView {

  void set(int i, @NotNull String s);

  int size();
}
