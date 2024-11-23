package it.unimi.di.sweng.scartino.common;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;

/*
 * A differenza del solito, ci sono due metodi statici che possono essere usati per creare un deck:
 * - createEmptyDeck
 * - createFullDeck
 */

public class Deck {
  private final LinkedList<Card> cards;

  private Deck() {
    cards = new LinkedList<>();
  }

  @NotNull
  public static Deck createEmptyDeck() {
    return new Deck();
  }

  @NotNull
  public static Deck createFullDeck() {
    Deck deck = new Deck();
    deck.shuffleFullDeck();
    return deck;
  }

  public void shuffleFullDeck() {
    cards.clear();
    for (Suit suit : Suit.values())
      for (Rank rank : Rank.values())
        cards.add(Card.get(rank, suit));
    Collections.shuffle(cards);
  }

  public void push(@NotNull Card card) {
    assert !cards.contains(card);
    cards.push(card);
  }

  @NotNull
  public Card draw() {
    assert !isEmpty();
    return cards.pop();
  }

  public boolean isEmpty() {
    return cards.isEmpty();
  }

  public int remainingCards() {
    return cards.size();
  }
}
