package it.unimi.di.sweng.slalom.model;

public record Skier(String name, double time) {

    @Override
    public String toString() {
        return name + " " + String.format("%.2f", time);
    }

}
