package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import it.unimi.di.sweng.MyDeck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Partita implements Iterable<Giocatore> {

  @NotNull private final CountableDeck mazzo = new MyDeck();
  @NotNull private final List<Giocatore> giocatori = new ArrayList<>();
  private final Tavolo tavolo = new Tavolo();

  public Partita() {
    for (int i = 0; i < 4; i++) {
      tavolo.metti(mazzo.draw());
    }
  }

  public Partita(Card[] card) {
    tavolo.metti(card[0]);
    tavolo.metti(card[1]);
    tavolo.metti(card[2]);
    tavolo.metti(card[3]);
  }

  public void addGiocatore(Giocatore giocatore) {
    giocatori.add(giocatore);
  }

  public void distribuisciCarta() {
    //TODO: implementare il metodo in modo da garantire le postcondizioni
    //draw
    giocatori.get(0).daiCarta();


    //POST CONDIZIONI
    for (Giocatore giocatore : giocatori) {
      assert giocatore.numCards() == 3 || (giocatore.numCards() < 3 && mazzo.size() < giocatori.size()) : "si possono avere meno di tre carte solo se nel mazzo non ce ne sono abbastanza per fare un altro giro";
      assert giocatori.get(0).numCards() == giocatore.numCards() : "non è stato dato stesso numero di carte a tutti";
    }
  }

  public void distribuisciCarteIniziali() {
    for (int i = 0; i < 3; i++) {
      for (Giocatore giocatore : giocatori) {
        distribuisciCarta();
      }
    }
  }



  public boolean isFinita() {
    assert giocatori.size() > 1;
    int cartegiocate = tavolo.size();
    for (Giocatore giocatore : giocatori) {
      cartegiocate += giocatore.getPunti();
    }
    return mazzo.size() < giocatori.size() && 52 - cartegiocate == mazzo.size();
  }


  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Giocatore giocatore : giocatori) {
      s.append(giocatore.toString());
      s.append("\n");
    }
    s.append("Tavolo: ");
    s.append(tavolo);
    s.append("\n");
    s.append("Finita: ");
    s.append(isFinita());
    return s.toString();
  }


  public boolean controllaSeCartaPresenteSuTavolo(Card card) {
    return tavolo.inMostra(card);
  }

  @Override
  public @NotNull Iterator<Giocatore> iterator() {
    return giocatori.iterator();
  }

  // TODO implementare metodo giocaCarta che esegue la giocata
  // secondo le regole e restituisce i punti ottenuti

}
