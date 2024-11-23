package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;

public class MyDeck implements CountableDeck {

    private final Deck deck;
    private int size = 52;

    public MyDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public Card draw() {
        size--;
        return deck.draw();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return deck.isEmpty();
    }
}
