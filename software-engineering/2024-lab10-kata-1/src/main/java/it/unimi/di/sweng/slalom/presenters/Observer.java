package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Observable;
import org.jetbrains.annotations.Nullable;

public interface Observer<T> {

    void update(@Nullable Observable<T> obs, @Nullable T state, @Nullable T nextSkier);

}
