package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

public interface CountableDeck {
  Card draw();
  int size();
  boolean isEmpty();
}
