package it.unimi.di.sweng.slalom.presenters;

import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Skier;

import java.util.List;

public class FirstMancheModelReductionStrategy implements ModelReductionStrategy<List<Skier>> {

    @Override
    public List<Skier> reduce(Model model) {
        return model.getFirstMancheSkiers();
    }
}
