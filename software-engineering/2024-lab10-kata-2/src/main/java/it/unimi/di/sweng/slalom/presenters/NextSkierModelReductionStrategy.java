package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Skier;

public class NextSkierModelReductionStrategy implements ModelReductionStrategy<Skier> {

    @Override
    public Skier reduce(Model model) {
        return model.getNextSkier();
    }
}
