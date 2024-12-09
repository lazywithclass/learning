package it.unimi.di.sweng.slalom;

public record Skier(String name, double time) {

    public String toString() {
        return String.format("%s %.2f", name, time);
    }

}
