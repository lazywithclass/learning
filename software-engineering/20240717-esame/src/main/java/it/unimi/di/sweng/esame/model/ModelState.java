package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ModelState implements IModel {

  protected final Map<String, Excursion> excursions;
  protected final Map<String, Double> bookings;


  public ModelState() {
    this.excursions = new HashMap<>();
    this.bookings = new HashMap<>();
  }

  public void readFile(@NotNull Scanner scanner) {
    while (scanner.hasNextLine()) {
      String linea = scanner.nextLine();
      String[] el = linea.split(",");
      excursions.put(el[0], new Excursion(el[0], Double.parseDouble(el[1]), Integer.parseInt(el[2])));
    }
  }

  @Override
  public @NotNull Map<String, Double> getBookings() {
    return Map.copyOf(this.bookings);
  }

  @Override
  public void addBooking(@NotNull String person, @NotNull String excursionName) {
    Excursion exc = excursions.get(excursionName);
    if (exc == null) throw new IllegalArgumentException("Invalid excursion name provided.");
    double cost = bookings.getOrDefault(person, 0.0);
    bookings.put(person, exc.cost() + cost);
    if (exc.availabilty() == 1) {
      excursions.remove(excursionName);
    } else {
      exc = new Excursion(exc.name(), exc.cost(), exc.availabilty() - 1);
      excursions.put(excursionName, exc);
    }
  }

  @Override
  public void payDebit(@NotNull String person, double amount) {
    Double debit = this.bookings.get(person);
    if (debit == null) throw new IllegalArgumentException("Specified person owes nothing.");

    debit -= amount;
    double epsilon = 0.0001d;

    if (debit < 0) throw new IllegalArgumentException("Can't pay more than you owe!");
    if (debit > epsilon) this.bookings.put(person, debit);
    else this.bookings.remove(person);
  }

}
