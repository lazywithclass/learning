package it.unimi.di.sweng.slalom.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model {

  private final List<Skier> firstMancheSkiers = new ArrayList<>();
  private final List<Skier> secondMancheSkiers = new ArrayList<>();

  // TODO completare modello dati

  public void readFilePrimaManche(@NotNull Scanner s) {
    while (s.hasNextLine()) {
      String linea = s.nextLine();
      String[] el = linea.split(";");
      String name = el[0];
      double time = Double.parseDouble(el[1]);


      // TODO memorizzare quanto letto
    }
  }

  public List<Skier> getFirstMancheSkiers() {
    return List.copyOf(firstMancheSkiers);
  }

  public List<Skier> getSecondMancheSkiers() {
    return List.copyOf(secondMancheSkiers);
  }

  public Skier getNextSkier() {
    return null; // chissene frega di questo
  }
}
