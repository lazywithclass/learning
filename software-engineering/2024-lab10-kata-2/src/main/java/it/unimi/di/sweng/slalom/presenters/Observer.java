package it.unimi.di.sweng.slalom.presenters;

public interface Observer<Model> {
    void update(Model model);
}
