package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import it.unimi.di.sweng.rubamazzetto.MyDeck;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Timeout(2)
public class MyDeckTest {

    @Test
    public void whenDrawingACardExpectNonNull() {
        MyDeck myDeck = new MyDeck(new Deck());
        Card card = myDeck.draw();
        assertThat(card).isNotEqualTo(null);
    }

    @Test
    public void whenMyDeckIsDrawnDeckDrawShouldBeCalled() {
        Deck deck = mock();
        MyDeck myDeck = new MyDeck(deck);
        when(deck.draw()).thenReturn(Card.get(Rank.ACE, Suit.DIAMONDS));
        assertThat(myDeck.draw()).isEqualTo(Card.get(Rank.ACE, Suit.DIAMONDS));
    }

    @Test
    public void whenAllCardsAreDrawnIsEmptyShouldBeTrue() {
        // sfrutto Deck per essere sicuro che, essendo libreria, e quindi testata altrove,
        // mi possa fidare delle sue implementazioni
        Deck deck = new Deck();
        MyDeck myDeck = new MyDeck(deck);
        assertThat(myDeck.isEmpty()).isEqualTo(false);
        while (!myDeck.isEmpty()) {
            deck.draw();
        }
        assertThat(myDeck.isEmpty()).isEqualTo(true);
    }

    @Test
    public void whenCreatedADeckHasSize52() {
        Deck deck = new Deck();
        MyDeck myDeck = new MyDeck(deck);
        assertThat(myDeck.size()).isEqualTo(52);
    }

    @Test
    public void whenEmptiedADeckHasSize0() {
        Deck deck = new Deck();
        MyDeck myDeck = new MyDeck(deck);
        while (!myDeck.isEmpty()) {
            myDeck.draw();
        }
        assertThat(myDeck.size()).isEqualTo(0);
    }
}

