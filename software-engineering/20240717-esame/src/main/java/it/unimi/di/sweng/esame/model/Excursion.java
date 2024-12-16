package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public record Excursion(@NotNull String name, double cost, int availabilty) implements Comparable<Excursion> {

    public Excursion {
        if (availabilty <= 0) throw new IllegalArgumentException("Availability must be strictly positive.");
    }

    @Override
    public @NotNull String toString() {
        return String.format("%s %.2f %d", name, cost, availabilty);
    }

    @Override
    public int compareTo(@NotNull Excursion o) {
        return Comparator.comparing(Excursion::availabilty)
                .reversed()
                .thenComparing(Excursion::name)
                .compare(this, o);
    }
}
