package it.unimi.di.sweng.slalom.model;

import it.unimi.di.sweng.slalom.Skier;

public class ModelSecondManche extends AbstractModel {

  public void addSkier(Skier skier) {
    skiers.add(skier);
    notifyObservers();
  }

}
