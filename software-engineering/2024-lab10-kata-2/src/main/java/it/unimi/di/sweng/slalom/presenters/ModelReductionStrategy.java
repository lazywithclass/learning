package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Model;

public interface ModelReductionStrategy<T> {
    T reduce(Model model);
}
