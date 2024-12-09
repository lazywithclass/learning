package it.unimi.di.sweng.slalom.model;

import it.unimi.di.sweng.slalom.Skier;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class ModelFirstManche extends AbstractModel {

  public void readFilePrimaManche(@NotNull Scanner s) {
    while (s.hasNextLine()) {
      String linea = s.nextLine();
      String[] el = linea.split(";");
      String name = el[0];
      double time = Double.parseDouble(el[1]);

      skiers.add(new Skier(name, time));
      notifyObservers();
    }
  }

}
