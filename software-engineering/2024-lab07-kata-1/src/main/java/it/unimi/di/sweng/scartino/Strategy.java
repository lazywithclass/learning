package it.unimi.di.sweng.scartino;

import it.unimi.di.sweng.scartino.common.Card;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Strategy {
  // TODO implementare almeno 2 strategie usabili per la scelta della prima carta (attacco)
  //      e almeno 2 strategie usabili per la scelta della seconda carta (risposta), strutturandole
  //      secondo il pattern CHAIN OF RESPONSIBILITY

  Strategy NULL = new Strategy() {
    @Override
    public @NotNull ScartinoCard chooseCard(@NotNull Iterable<ScartinoCard> cards, @Nullable ScartinoCard attackCard) {
      assert cards.iterator().hasNext();
      return cards.iterator().next();
    }
  };

  // ATTENZIONE: quando attackCard è null vuol dire che è una strategia di attacco,
  //             se è diversa da null è strategia di risposta
  @NotNull ScartinoCard chooseCard(@NotNull Iterable<ScartinoCard> cards,
                           @Nullable ScartinoCard attackCard);
}
