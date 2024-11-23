package it.unimi.di.sweng.scartino.common;

import org.jetbrains.annotations.NotNull;


public final class Card {

  private static final Card[][] CARDS = new Card[Suit.values().length][];

  static {
    for (Suit suit : Suit.values()) {
      CARDS[suit.ordinal()] = new Card[Rank.values().length];
      for (Rank rank : Rank.values())
        CARDS[suit.ordinal()][rank.ordinal()] = new Card(rank, suit);
    }
  }

  @NotNull
  private final Rank rank;
  @NotNull
  private final Suit suit;

  private Card(@NotNull Rank rank, @NotNull Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  @NotNull
  public static Card get(@NotNull Rank rank, @NotNull Suit suit) {
    return CARDS[suit.ordinal()][rank.ordinal()];
  }


  @NotNull
  public Rank getRank() {
    return rank;
  }

  @NotNull
  public Suit getSuit() {
    return suit;
  }

  @Override
  @NotNull
  public String toString() {
    return rank + " di " + suit;
  }

  public static Card of(String input) {
    Suit s = parseSuit(input);
    Rank r = parseRank(input);
    return Card.get(r, s);
  }

  private static Rank parseRank(String str) {
    return switch (str.charAt(0)) {
      case '1', 'A' -> Rank.ASSO;
      case '2' -> Rank.DUE;
      case '3' -> Rank.TRE;
      case '4' -> Rank.QUATTRO;
      case '5' -> Rank.CINQUE;
      case '6' -> Rank.SEI;
      case '7' -> Rank.SETTE;
      case '8', 'F' -> Rank.FANTE;
      case '9', 'C' -> Rank.CAVALLO;
      case '0', 'R' -> Rank.RE;
      default -> throw new IllegalStateException("Unexpected rank value: " + str.charAt(0));
    };
  }

  private static Suit parseSuit(String s) {
    return switch (s.charAt(1)) {
      case 'B' -> Suit.BASTONI;
      case 'C' -> Suit.COPPE;
      case 'D' -> Suit.DENARI;
      case 'S' -> Suit.SPADE;
      default -> throw new IllegalStateException("Unexpected suit value: " + s.charAt(1));
    };
  }
}
