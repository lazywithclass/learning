package it.unimi.di.sweng.slalom.model;

import it.unimi.di.sweng.slalom.presenters.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model implements Observable<List<Skier>> {

  private final List<Skier> firstMancheSkiers;
  private final List<Skier> secondMancheSkiers;
  private List<Observer<List<Skier>>> observers;

  public Model() {
    firstMancheSkiers = new ArrayList<>();
    secondMancheSkiers = new ArrayList<>();
    observers = new ArrayList<>();
  }

  public void readFilePrimaManche(@NotNull Scanner s) {
    while (s.hasNextLine()) {
      String linea = s.nextLine();
      String[] el = linea.split(";");
      String name = el[0];
      double time = Double.parseDouble(el[1]);

      Skier skier = new Skier(name, (int) (time * 100));
      this.firstMancheSkiers.add(skier);
    }
    notifyObservers();
  }

  public void notifyObservers() {
    List<Skier> copiedSkiers = List.copyOf(firstMancheSkiers);
    for (Observer<List<Skier>> obs : observers) {
      obs.update(this, copiedSkiers, getLastSkier());
    }
  }

  public void addObserver(@NotNull Observer<List<Skier>> obs) {
    observers.add(obs);
  }

  public @NotNull List<Skier> getState() {
    return null;
  }

  public @NotNull List<Skier> getLastSkier() {
    return firstMancheSkiers.subList(firstMancheSkiers.size() - 1, firstMancheSkiers.size());
  }

  public void addSecondMancheSkier(Skier skier) {
    this.secondMancheSkiers.add(skier);
    this.notifyObservers();
  }

  public List<Skier> getSecondMancheSkiers() {
    return List.copyOf(this.secondMancheSkiers);
  }

}
