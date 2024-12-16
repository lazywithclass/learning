package it.unimi.di.sweng.esame.view;

import org.jetbrains.annotations.NotNull;

public interface OutputView {
    void set(int i, @NotNull String s);

    String get(int i);

    int size();
}
