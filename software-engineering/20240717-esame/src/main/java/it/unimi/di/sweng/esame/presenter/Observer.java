package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Observable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Observer<T> {

    void update(@Nullable Observable<T> subject, @NotNull T state);

}
