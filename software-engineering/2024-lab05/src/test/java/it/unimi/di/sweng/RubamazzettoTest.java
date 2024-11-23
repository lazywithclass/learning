package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import it.unimi.di.sweng.rubamazzetto.CountableDeck;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.unimi.di.sweng.rubamazzetto.Giocatore;
import it.unimi.di.sweng.rubamazzetto.Partita;
import it.unimi.di.sweng.rubamazzetto.Tavolo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Iterator;

@Timeout(2)
public class RubamazzettoTest {

    @Test
    void myDeckIsEmptyTest() {
        CountableDeck deck = new MyDeck();
        assertThat(deck.isEmpty()).isEqualTo(false);
    }

    @Test
    void myDeckDrawTest() {
        int count = 0;
        CountableDeck deck = new MyDeck();
        while (!deck.isEmpty()) {
            deck.draw();
            count++;
        }
        assertThat(count).isEqualTo(52);
    }

    @Test
    void myDeskSizeTest() {
        CountableDeck deck = new MyDeck();
        assertThat(deck.size()).isEqualTo(52);

        deck = new MyDeck();
        deck.draw();
        deck.draw();
        deck.draw();
        deck.draw();
        assertThat(deck.size()).isEqualTo(48);
    }

    @Test
    void giocatoriIterabiliSuPartitaTest() {
        Partita partita = new Partita();
        Giocatore g1 = new Giocatore("1", partita);
        Giocatore g2 = new Giocatore("2", partita);
        Giocatore g3 = new Giocatore("3", partita);
        partita.addGiocatore(g1);
        partita.addGiocatore(g2);
        partita.addGiocatore(g3);

        Iterator<Giocatore> giocatori = partita.iterator();
        Giocatore g = giocatori.next();
        assertThat(g).isEqualTo(g1);
        g = giocatori.next();
        assertThat(g).isEqualTo(g2);
        g = giocatori.next();
        assertThat(g).isEqualTo(g3);
    }

    private Card[] cards = {
            Card.get(Rank.ACE, Suit.CLUBS),
            Card.get(Rank.KING, Suit.CLUBS),
            Card.get(Rank.QUEEN, Suit.CLUBS),
            Card.get(Rank.JACK, Suit.CLUBS),
            Card.get(Rank.TEN, Suit.CLUBS),
    };

    @Test
    public void partitaControllaCarteTavoloTest() {
        Partita partita = new Partita(cards);
        assertThat(partita.controllaSeCartaPresenteSuTavolo(cards[0])).isEqualTo(true);
        assertThat(partita.controllaSeCartaPresenteSuTavolo(cards[1])).isEqualTo(true);
        assertThat(partita.controllaSeCartaPresenteSuTavolo(cards[2])).isEqualTo(true);
        assertThat(partita.controllaSeCartaPresenteSuTavolo(cards[3])).isEqualTo(true);
        assertThat(partita.controllaSeCartaPresenteSuTavolo(cards[4])).isEqualTo(false);
    }

    @Test
    public void partitaDistribuisciTest() {
        Partita partita = new Partita(cards);

    }
}
