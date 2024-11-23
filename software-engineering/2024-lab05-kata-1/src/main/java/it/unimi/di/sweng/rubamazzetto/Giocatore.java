package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.ArrayList;
import java.util.List;

public class Giocatore {

  private final String nome;
  private final List<Card> mano = new ArrayList<>();
  private Rank mazzettoTop;
  private final Partita partita;

  private int punti;

  public Giocatore(String nome, Partita partita) {
    this.nome = nome;
    partita.addGiocatore(this);
    this.partita = partita;
  }

  public Rank getMazzettoTop() {
    return mazzettoTop;
  }

  public int getPunti() {
    return punti;
  }

  public void daiCarta(Card carta) {
    mano.add(carta);
  }


  public void turno() {
    // TODO. scegli carta in base alla catena di strategie del giocatore

    // chiede a partita di giocare secondo le regole la carta seclta con una chiamata tipo:
    // partita.giocaCarta(this, card);
    // questa funzione gli restituisce l'eventuale numero di punti ottenuti
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder(nome);
    s.append(": ");
    s.append("[").append(mano.size()).append("]");
    if (punti > 0) {
      s.append("mazzetto con ");
      s.append(punti);
      s.append(" carte, cima ");
      s.append(mazzettoTop);
      s.append("; ");
    }
    for (Card card : mano) {
      s.append(card.toString());
      s.append(", ");
    }
    return s.toString();
  }

  public int numCards() {
    return mano.size();
  }
}
