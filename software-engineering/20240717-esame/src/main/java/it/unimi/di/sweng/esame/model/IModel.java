package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface IModel {

    Map<String, Double> getBookings();

    void addBooking(@NotNull String person, @NotNull String excursionName);

    void payDebit(@NotNull String person, double amount);

}
